package com.robertgordon.finaldailyplan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.robertgordon.finaldailyplan.databinding.GoalListItemBinding
import com.robertgordon.finaldailyplan.models.Goal


class GoalsAdapter(val goals: ObservableArrayList<Goal>, val onGoalCLicked: (Goal) -> Unit): RecyclerView.Adapter<GoalsAdapter.ViewHolder>() {
    class ViewHolder(val binding: GoalListItemBinding):RecyclerView.ViewHolder(binding.root)


    init {
    goals.addOnListChangedCallback(object: ObservableList.OnListChangedCallback<ObservableArrayList<Goal>>(){
        override fun onChanged(sender: ObservableArrayList<Goal>?) {
            notifyDataSetChanged()
        }

        override fun onItemRangeChanged(
            sender: ObservableArrayList<Goal>?,
            positionStart: Int,
            itemCount: Int
        ) {
            notifyItemChanged(positionStart)
        }

        override fun onItemRangeInserted(
            sender: ObservableArrayList<Goal>?,
            positionStart: Int,
            itemCount: Int
        ) {
            notifyItemInserted(positionStart)
        }

        override fun onItemRangeMoved(
            sender: ObservableArrayList<Goal>?,
            fromPosition: Int,
            toPosition: Int,
            itemCount: Int
        ) {

        }

        override fun onItemRangeRemoved(
            sender: ObservableArrayList<Goal>?,
            positionStart: Int,
            itemCount: Int
        ) {
            notifyItemRemoved(positionStart)
        }
    })

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GoalListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goal = goals[position]
        holder.binding.goalDescription.text = goal.goalDes
        holder.binding.goalDeadline.text = goal.goalDate
        holder.binding.completion.isChecked = goal.complete
        holder.binding.completion.setOnClickListener{
            onGoalCLicked(goal)
        }
    }

    override fun getItemCount(): Int {
        return goals.size
    }


}