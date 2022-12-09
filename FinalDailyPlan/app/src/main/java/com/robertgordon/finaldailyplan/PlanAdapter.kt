package com.robertgordon.finaldailyplan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.robertgordon.finaldailyplan.databinding.PlanListItemBinding
import com.robertgordon.finaldailyplan.models.Goal
import com.robertgordon.finaldailyplan.models.Plan

class PlanAdapter(val plans: ObservableArrayList<Plan>, val onPlanEntered: (Plan) -> Unit):RecyclerView.Adapter<PlanAdapter.ViewHolder>() {
    class ViewHolder(val binding: PlanListItemBinding): RecyclerView.ViewHolder(binding.root)

    init {
        plans.addOnListChangedCallback(object: ObservableList.OnListChangedCallback<ObservableArrayList<Goal>>(){
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
        val binding = PlanListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val planed = plans[position]
        holder.binding.hourText.text = planed.time.toString()
        holder.binding.planEditText.setText(planed.plan)
    }

    override fun getItemCount(): Int {
        return plans.size
    }


}