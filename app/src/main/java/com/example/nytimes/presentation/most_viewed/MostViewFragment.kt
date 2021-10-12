package com.example.nytimes.presentation.most_viewed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytimes.common.Resource
import com.example.nytimes.databinding.FragmentMostViewedBinding
import com.example.nytimes.domain.model.MostViewed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MostViewFragment : Fragment() {

    private lateinit var binding: FragmentMostViewedBinding
    private val viewModel: MostViewViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMostViewedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.srfLayout.setOnRefreshListener {
            viewModel.getMostViewed()
            binding.srfLayout.isRefreshing = false
        }
        viewModel.state.observe(viewLifecycleOwner,{
            when(it) {
                is Resource.Success -> {
                    binding.rvMostViewed.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvMostViewed.adapter = MostViewAdapter(it.data!!)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    Toast.makeText(requireContext(),"Loading...",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}