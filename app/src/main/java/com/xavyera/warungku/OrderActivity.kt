package com.xavyera.warungku

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xavyera.warungku.databinding.ActivityOrderBinding
import java.text.NumberFormat
import java.util.Locale

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener { finish() }

        val food = FoodItem.fromIntent(intent)
        if (food == null) {
            Toast.makeText(this, "Gagal memuat data makanan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.textViewOrderTitle.text = "Pesan: ${food.name}"

        val formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"))

        fun updateTotal(value: String?) {
            val quantity = parseQuantity(value)
            binding.textViewTotalPrice.text = when {
                quantity > MAX_QUANTITY -> "Maks. $MAX_QUANTITY"
                else -> {
                    // Pakai Long agar perkalian tidak overflow saat jumlah besar.
                    val total = quantity * food.priceValue.toLong()
                    formatter.format(total)
                }
            }
        }

        binding.editTextQuantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateTotal(s?.toString())
            }

            override fun afterTextChanged(s: Editable?) = Unit
        })

        binding.buttonConfirmOrder.setOnClickListener {
            val customerName = binding.editTextCustomerName.text?.toString()?.trim().orEmpty()
            val quantity = parseQuantity(binding.editTextQuantity.text?.toString())

            when {
                customerName.isEmpty() ->
                    Toast.makeText(this, "Lengkapi nama pembeli!", Toast.LENGTH_SHORT).show()

                quantity < 1 ->
                    Toast.makeText(this, "Jumlah pesanan harus lebih dari 0!", Toast.LENGTH_SHORT).show()

                quantity > MAX_QUANTITY ->
                    Toast.makeText(this, "Jumlah pesanan maksimal $MAX_QUANTITY!", Toast.LENGTH_SHORT).show()

                else -> {
                    Toast.makeText(this, "Pesanan Dikonfirmasi!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    private fun parseQuantity(value: String?): Long = value?.trim()?.toLongOrNull() ?: 0L

    private companion object {
        const val MAX_QUANTITY = 999L
    }
}
