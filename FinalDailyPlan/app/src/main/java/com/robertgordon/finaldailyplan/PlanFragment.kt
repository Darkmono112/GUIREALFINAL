package com.robertgordon.finaldailyplan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.robertgordon.finaldailyplan.databinding.FragmentPlanBinding


class PlanFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlanBinding.inflate(inflater,container, false)

        binding.planToGoalButton.setOnClickListener {
            findNavController().navigate(R.id.planTogoal)
        }
        binding.planToHomeButton.setOnClickListener {
            findNavController().navigate(R.id.planToHome)
        }



        return binding.root
    }


}