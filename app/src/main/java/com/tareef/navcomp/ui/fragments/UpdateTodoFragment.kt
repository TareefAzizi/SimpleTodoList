package com.tareef.navcomp.ui.fragments

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
import com.tareef.navcomp.databinding.FragmentUpdateTodoBinding
import com.tareef.navcomp.ui.viewModel.UpdateTodoViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateTodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateTodoFragment : Fragment() {
    private lateinit var binding: FragmentUpdateTodoBinding
    private lateinit var navController: NavController
    private val navArgs: UpdateTodoFragmentArgs by navArgs()

    private val viewModel: UpdateTodoViewModel by viewModels {
        UpdateTodoViewModel.Factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateTodoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.init(navArgs.taskId)

        viewModel.finished.observe(viewLifecycleOwner) {
            if (it) {
                navigateBack()
                viewModel.finished.value = false
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val snackbar = Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                )

                snackbar.setBackgroundTint(
                    ContextCompat.getColor(
                        requireContext(), R.color.red
                    )
                )
                snackbar.show()
            }
        }


    }

    private fun clearSelected() {
        binding.run {
            vRed.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            vBlue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
            vGreen.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }

    private fun navigateBack() {
        val bundle = Bundle()
        bundle.putBoolean("refresh", true)
        setFragmentResult("from_update_todo_fragment", bundle)
        navController.popBackStack()
    }
}
