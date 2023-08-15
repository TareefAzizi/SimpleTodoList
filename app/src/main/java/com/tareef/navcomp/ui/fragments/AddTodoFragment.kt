package com.tareef.navcomp.ui.fragments

import android.content.Context
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
import com.google.android.material.snackbar.Snackbar
import com.tareef.navcomp.R
import com.tareef.navcomp.data.model.Color
import com.tareef.navcomp.databinding.FragmentAddTodoBinding
import com.tareef.navcomp.ui.viewModel.AddTodoViewModel

class AddTodoFragment : Fragment() {
    private lateinit var binding: FragmentAddTodoBinding
    private lateinit var navController: NavController
    private val viewModel: AddTodoViewModel by viewModels{
        AddTodoViewModel.Factory
    }
     override fun onCreateView(
         inflater: LayoutInflater, container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         // Inflate the layout for this fragment
         binding = FragmentAddTodoBinding.inflate(layoutInflater, container, false)
         return binding.root
     }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = NavHostFragment.findNavController(this)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.finished.observe(viewLifecycleOwner){
            if(it){
                navigateBack()
                viewModel.finished.value = false
            }

            viewModel.error.observe(viewLifecycleOwner){
                if(it.isNotEmpty()){
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

            viewModel.color.observe(viewLifecycleOwner){
                clearSelected()
                val bgDrawable = GradientDrawable().apply{
                    shape = GradientDrawable.RECTANGLE
                    setColor(ContextCompat.getColor(requireContext(), it.rgb))
                    setStroke(8, ContextCompat.getColor(requireContext(), R.color.black))
                }

                when(it){
                    Color.BLUE -> binding.vBlue
                    Color.GREEN -> binding.vGreen
                    else -> binding.vRed
                }.background = bgDrawable
            }
        }
    }

    private fun clearSelected(){
        binding.run{
            vRed.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.red))
            vBlue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
            vGreen.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }


    fun navigateBack(){
        val bundle =  Bundle()
        bundle.putBoolean("refresh", true)
        setFragmentResult("from_add_todo_fragment", bundle)
        navController.popBackStack()
    }
 }