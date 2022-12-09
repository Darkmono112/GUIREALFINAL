package com.robertgordon.finaldailyplan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.robertgordon.finaldailyplan.databinding.FragmentPlanBinding


class PlanFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlanBinding.inflate(inflater,container, false)
        val viewModel = PlanViewModel()

        viewModel.errorMessage.observe(viewLifecycleOwner){
            binding.errorMessagePlan.text = it
        }
        binding.planToGoalButton.setOnClickListener {
            findNavController().navigate(R.id.planTogoal)
        }
        binding.planToHomeButton.setOnClickListener {
            findNavController().navigate(R.id.planToHome)
        }

        binding.savePlan.setOnClickListener {
            if(binding.enterPlan.text.toString().isNotBlank() && binding.enterTime.text.toString().isNotBlank()) {
                viewModel.createPlan(
                    binding.enterPlan.text.toString(),
                    binding.enterTime.text.toString().toLong()
                )
            }
        }

        binding.clearPlan.setOnClickListener {
            viewModel.clearPlans()
        }




        binding.planRecycler.adapter = PlanAdapter(viewModel.plans){

        }

        binding.planRecycler.layoutManager = LinearLayoutManager(context)




        return binding.root
    }


}