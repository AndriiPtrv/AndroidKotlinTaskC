package com.example.androidkotlintaskc

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidkotlintaskc.adapterFacts.FactAdapter
import com.example.androidkotlintaskc.network.ApiService.getData
import com.example.androidkotlintaskc.network.model.FactOfCat
import kotlinx.android.synthetic.main.fragment_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InfoFragment : Fragment() {

    private val list = mutableListOf<FactOfCat>()
    private val factAdapter = FactAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list_of_facts.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = factAdapter
        }

//        val context: Context = view.context // брал this контекст выдавало ошибку. Надо брать с view
//        val sp = context.getSharedPreferences("facts", Context.MODE_PRIVATE)
        val sp = activity?.getPreferences(Context.MODE_PRIVATE)

        if (sp?.getString("0", null).isNullOrBlank()) {
            getData().enqueue(object : Callback<List<FactOfCat>> {
                override fun onResponse(
                    call: Call<List<FactOfCat>>,
                    response: Response<List<FactOfCat>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        list.clear()
                        list.addAll(response.body() as List<FactOfCat>)
                        factAdapter.update(list)

                        val editor = sp?.edit()
                        for (i in 0..99) {
                            editor?.putString(i.toString(), list[i].text.toString())
                        }
                        editor?.apply()
                    } else {
                        Toast.makeText(view.context, "Что то пошло не так", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(
                    call: Call<List<FactOfCat>>,
                    t: Throwable
                ) {
                    Toast.makeText(view.context, "Что то пошло не так", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            list.clear()
            for (i in 0..99){
                list.add(FactOfCat(sp?.getString(i.toString(), "ERROR")))
            }
            factAdapter.update(list)
        }
    }
}
