package com.tareef.navcomp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tareef.navcomp.databinding.FragmentHomeBinding
import com.tareef.navcomp.ui.adapter.TaskAdapter
import com.tareef.navcomp.ui.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var adapter: TaskAdapter
    private val viewModel: HomeViewModel by viewModels{
        HomeViewModel.Factory
    }
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navigationController = NavHostFragment.findNavController(this)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupAdapter()

        viewModel.tasks.observe(viewLifecycleOwner){
            adapter.setNewItems(it)

        }

        binding.fabAdd.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeToAddTodo()
            navigationController.navigate(action)
        }

        setupAdapter()

        setFragmentResultListener("from_add_todo_fragment"){_, result ->
            val refresh = result.getBoolean("refresh")?:false
            if(refresh){
                viewModel.fetchTasks()
            }
        }
    }


    fun setupAdapter(){
        adapter  = TaskAdapter(emptyList()){}

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvTodos.adapter = adapter
        binding.rvTodos.layoutManager = layoutManager

    }
}