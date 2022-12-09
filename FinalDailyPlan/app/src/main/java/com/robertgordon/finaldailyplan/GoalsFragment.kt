package com.robertgordon.finaldailyplan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.robertgordon.finaldailyplan.databinding.FragmentGoalsBinding


class GoalsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGoalsBinding.inflate(inflater,container,false)
        val viewModel = GoalViewModel()

        binding.goalToHomeButton.setOnClickListener {
            findNavController().navigate(R.id.goalToHome)
        }

        binding.goalToPlanButton.setOnClickListener {
                findNavController().navigate(R.id.goalToPlan)
        }

        binding.goalSave.setOnClickListener {
            viewModel.saveAGoal(binding.goalEditText.text.toString(), binding.editTextDate.text.toString())
        }

        viewModel.errorMessage.observe(viewLifecycleOwner){errorMessage->
            binding.errorBox.text = errorMessage
        }

        binding.goalRecycler.adapter = GoalsAdapter(viewModel.goals){
            viewModel.toggleGoalCompletion(it)

        }

        binding.goalRecycler.layoutManager = LinearLayoutManager(context)

        return binding.root
    }


}