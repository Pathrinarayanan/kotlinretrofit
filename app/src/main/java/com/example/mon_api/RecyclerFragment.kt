package com.example.mon_api
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerFragment : Fragment(R.layout.fragment_recycler) {

    private lateinit var mrecyclerView: RecyclerView
    private lateinit var dataadapter: WidgetAdapter
    private val Widget_viewModel: RecyclerViewModel by viewModels{RecyclerViewModelFactory(AppModule.repository)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mrecyclerView = view.findViewById(R.id.recyclerView)
        mrecyclerView.layoutManager = LinearLayoutManager(requireContext())
        dataadapter = WidgetAdapter(emptyList())
        mrecyclerView.adapter = dataadapter

        Widget_viewModel.widgetData.observe(viewLifecycleOwner, Observer { widgets ->
            dataadapter.setData(widgets)
        })

        Widget_viewModel.fetchDataFromApi()
    }
}
