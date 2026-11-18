package com.xavyera.warungku

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xavyera.warungku.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener { finish() }

        val food = FoodItem.fromIntent(intent)
        if (food == null) {
            Toast.makeText(this, "Gagal memuat data makanan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.imageViewDetailFood.setImageResource(food.imageRes)
        binding.textViewDetailName.text = food.name
        binding.textViewDetailPrice.text = food.price
        binding.textViewDetailDescription.text = food.description

        binding.buttonOrder.setOnClickListener {
            val orderIntent = Intent(this, OrderActivity::class.java)
            orderIntent.putExtra(FoodItem.EXTRA_FOOD_ITEM, food)
            startActivity(orderIntent)
        }
    }
}
