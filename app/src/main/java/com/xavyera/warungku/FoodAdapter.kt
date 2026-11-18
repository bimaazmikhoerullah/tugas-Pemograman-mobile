package com.xavyera.warungku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(
    private val foodList: List<FoodItem>,
    private val onClick: (FoodItem) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.imageViewFoodPhoto)
        val name: TextView = itemView.findViewById(R.id.textViewFoodName)
        val price: TextView = itemView.findViewById(R.id.textViewFoodPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.photo.setImageResource(food.imageRes)
        holder.name.text = food.name
        holder.price.text = food.price
        holder.itemView.setOnClickListener { onClick(food) }
    }

    override fun getItemCount() = foodList.size
}
