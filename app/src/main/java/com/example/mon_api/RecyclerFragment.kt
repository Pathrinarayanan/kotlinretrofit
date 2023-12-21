package com.example.mon_api
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerFragment : Fragment(R.layout.fragment_recycler) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mDataAdapter: WidgetAdapter
    private val mViewModel: RecyclerViewModel by viewModels{RecyclerViewModelFactory(AppModule.repository)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerView = view.findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        mDataAdapter = WidgetAdapter(emptyList())
//        mRecyclerView.adapter = mDataAdapter

        mViewModel.widgetData.observe(viewLifecycleOwner, Observer { widgets ->
//            mDataAdapter.setData(widgets)
            mRecyclerView.adapter = WidgetAdapter(widgets)
        })


        mViewModel.fetchDataFromApi()
    }
}
