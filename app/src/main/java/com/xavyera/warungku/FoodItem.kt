package com.xavyera.warungku

import android.content.Intent
import android.os.Build
import java.io.Serializable

data class FoodItem(
    val name: String,
    val price: String,
    val description: String,
    val priceValue: Int,
    val imageRes: Int
) : Serializable {

    companion object {
        const val EXTRA_FOOD_ITEM = "FOOD_ITEM"
        private const val serialVersionUID = 3L

        /**
         * Mengambil [FoodItem] dari Intent dengan aman.
         *
         * Mengembalikan null (bukan melempar exception) saat extra tidak ada,
         * tipenya tidak sesuai, atau proses aplikasi dimatikan sistem dan
         * dibuka kembali. Dengan begitu activity tidak force close.
         */
        fun fromIntent(intent: Intent?): FoodItem? {
            if (intent == null || !intent.hasExtra(EXTRA_FOOD_ITEM)) return null

            return try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getSerializableExtra(EXTRA_FOOD_ITEM, FoodItem::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getSerializableExtra(EXTRA_FOOD_ITEM) as? FoodItem
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}
