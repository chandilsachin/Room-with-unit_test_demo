package com.ace.diettracker.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.ace.diettracker.database.entities.Food
import com.sachinchandil.roomwithunittestinkotlin.R
import java.util.*

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

class FoodListAdapter(context: Context) :
        RecyclerView.Adapter<FoodListAdapter.ViewHolder>(), Filterable {

    var foodList: List<Food> = emptyList()
    private var displayFoodList: ArrayList<Food> = arrayListOf()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(inflater.inflate(R.layout.layout_food_list_item, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(displayFoodList[position])

    override fun getItemCount(): Int {
        return displayFoodList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewFoodName = itemView.findViewById<TextView>(R.id.textViewFoodName)
        var textViewFoodDesc = itemView.findViewById<TextView>(R.id.textViewFoodDesc)

        fun bind(item: Food) {
            textViewFoodName.text = item.foodName
            textViewFoodDesc.text = item.foodDesc
        }
    }

    override fun getFilter(): Filter {

        return dietFilter;
    }

    val dietFilter = object : Filter() {
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

            displayFoodList = results?.values as ArrayList<Food>
            notifyDataSetChanged()
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            displayFoodList.clear()
            if (constraint == null || constraint.isEmpty()) {
                displayFoodList.addAll(foodList)
            } else {
                for (item in foodList) {
                    if (item.foodName.toLowerCase().contains(constraint.toString().toLowerCase()))
                        displayFoodList.add(item)
                }
            }
            var results = FilterResults()
            results.values = displayFoodList
            results.count = displayFoodList.size
            return results
        }
    }
}
