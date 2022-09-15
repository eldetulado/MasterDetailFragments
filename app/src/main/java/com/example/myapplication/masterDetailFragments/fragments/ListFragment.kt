package com.example.myapplication.masterDetailFragments.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.masterDetailFragments.MasterDetailActivity
import com.example.myapplication.masterDetailFragments.VMMasterDetail

class ListFragment() : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: VMMasterDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[VMMasterDetail::class.java]

        activity?.supportFragmentManager?.addOnBackStackChangedListener {
            Log.e("ListFragment", "count: ${activity?.supportFragmentManager?.backStackEntryCount}")
            if (requireActivity().supportFragmentManager.backStackEntryCount >= 1) {
                (activity as MasterDetailActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                (activity as MasterDetailActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(
            LayoutInflater.from(requireContext()), container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_list_item_1, viewModel.listNumbers
        )

        binding.lvData.adapter = adapter
        binding.lvData.setOnItemClickListener { _, _, i, _ ->
            viewModel.itemSelected.value = viewModel.listNumbers[i]
        }
    }
}