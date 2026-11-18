package com.xavyera.warungku

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.xavyera.warungku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuList = listOf(
            FoodItem("Nasi Goreng", "Rp 15.000", "Nasi goreng dengan bumbu spesial, telur, dan sayuran segar.", 15000, R.drawable.menu_nasi_goreng),
            FoodItem("Mie Goreng", "Rp 14.000", "Mie telur digoreng dengan bumbu rempah pilihan.", 14000, R.drawable.menu_mie_goreng),
            FoodItem("Sate Ayam", "Rp 20.000", "Sate ayam dengan bumbu kacang khas dan lontong.", 20000, R.drawable.menu_sate),
            FoodItem("Bakso", "Rp 18.000", "Bakso daging sapi asli dengan kuah kaldu gurih.", 18000, R.drawable.menu_bakso),
            FoodItem("Es Teh", "Rp 5.000", "Es teh manis segar, cocok untuk menemani makan.", 5000, R.drawable.menu_es_teh)
        )

        binding.recyclerViewMenu.setHasFixedSize(true)
        binding.recyclerViewMenu.adapter = FoodAdapter(menuList) { food ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(FoodItem.EXTRA_FOOD_ITEM, food)
            startActivity(intent)
        }

        binding.tabBeranda.setOnClickListener {
            Toast.makeText(this, "Anda sudah di Beranda", Toast.LENGTH_SHORT).show()
        }

        binding.tabPesanan.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra(FoodItem.EXTRA_FOOD_ITEM, menuList.first())
            startActivity(intent)
        }

        binding.tabTentang.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_about, null)
            val dialog = AlertDialog.Builder(this).setView(dialogView).create()
            dialogView.findViewById<View>(R.id.buttonDialogOk).setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }
}
