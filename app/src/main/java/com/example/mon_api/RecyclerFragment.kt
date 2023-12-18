package com.example.mon_api
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerFragment : Fragment(R.layout.fragment_recycler) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WidgetAdapter
    private  lateinit var repository: WidgetRepository
    private val viewModel: RecyclerViewModel by viewModels{RecyclerViewModelFactory(AppModule.repository)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = WidgetAdapter(emptyList())
        recyclerView.adapter = adapter

        viewModel.widgetData.observe(viewLifecycleOwner, Observer { widgets ->
            adapter.setData(widgets)
        })

        viewModel.fetchDataFromApi()
    }
}
