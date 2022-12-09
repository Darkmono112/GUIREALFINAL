package com.robertgordon.finaldailyplan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.robertgordon.finaldailyplan.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val viewModel = HomeViewModel()

        viewModel.hasAGoal.observe(viewLifecycleOwner){hasAGoal ->
            binding.homeError.text = hasAGoal
        }

        binding.homeToGoalButton.setOnClickListener {
            findNavController().navigate(R.id.homeToGoal)
        }
        binding.homeToPlansButton.setOnClickListener {
                findNavController().navigate(R.id.homeToPlan)
        }



        return binding.root
    }

}