package com.tareef.navcomp.ui.fragments

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.tareef.navcomp.R
import com.tareef.navcomp.databinding.FragmentShowTaskBinding
import com.tareef.navcomp.ui.viewModel.ShowTaskViewModel


class ShowTaskFragment : Fragment() {
    private lateinit var binding: FragmentShowTaskBinding
    private lateinit var navController: NavController
    private val navArgs: ShowTaskFragmentArgs by navArgs()

    private val viewModel: ShowTaskViewModel by viewModels {
        ShowTaskViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.init(navArgs.taskId)


        viewModel.task.observe(viewLifecycleOwner) {
            binding.llDetailsBackground.setBackgroundColor(
                ContextCompat.getColor(requireContext(), it.color.rgb)
            )
        }

        binding.btnEdit.setOnClickListener {
            val action = ShowTaskFragmentDirections.actionShowTaskFragmentToUpdateTodoFragment(
                navArgs.taskId
            )
            navController.navigate(action)
        }

    }


//    private fun navigateBack() {
//        val bundle = Bundle()
//        bundle.putBoolean("refresh", true)
//        setFragmentResult("from_update_todo_fragment", bundle)
//        navController.popBackStack()
//    }


}
