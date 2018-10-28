package com.iav.kade_2.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iav.kade_2.Adapter.LastMatchAdapter
import com.iav.kade_2.Model.Item
import com.iav.kade_2.R
import com.iav.kade_2.Rest.ApiService
import com.iav.kade_2.Rest.RetroConfig
import kotlinx.android.synthetic.main.fragment_last_match.*
import org.jetbrains.anko.support.v4.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment() {

    private var items: ArrayList<Item> = arrayListOf()
    lateinit var mAdapter:LastMatchAdapter
    lateinit var rv:RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_last_match, container, false)
        rv = view.findViewById(R.id.rv)

        getDataLastMatch()
        return view
    }

    /*
    override fun onResume() {
        super.onResume()
        getDataLastMatch()

    }
    */

    private fun getDataLastMatch() {
        val service: ApiService = RetroConfig.provideApi()
        service.getLastMatch("4328")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    items.clear()
                    items = it.events as ArrayList<Item>
                    rv.layoutManager = LinearLayoutManager(activity)
                    mAdapter = LastMatchAdapter(activity, items)
                    rv.adapter = mAdapter
                }, {
                    error("errror"+it.localizedMessage)
                })
    }


}