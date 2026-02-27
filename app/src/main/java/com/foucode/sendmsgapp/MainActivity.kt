package com.foucode.sendmsgapp

import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    data class Contact(val name: String, val number: String, val imageResId: Int)

    private val message = "Hola! Soy Oscar. Llamame por favor"

    private val contacts = listOf(
        Contact("Melania", "+5492954336989", R.drawable.melania),
        Contact("Marina", "+5492954443482", R.drawable.marina),
        Contact("Azucena", "+5492954392353", R.drawable.azucena),
        Contact("Marta", "+5492954557850", R.drawable.marta),
        Contact("Lorena", "+5492954557850", R.drawable.lorena),
        Contact("Yudi", "+5492954535156", R.drawable.yudi)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pantalla completa sin barra
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val gridView = findViewById<GridView>(R.id.gridView)
        val adapter = ImageAdapter(this, contacts.map { it.imageResId })
        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            val contact = contacts[position]
            val uri = Uri.parse("https://wa.me/${contact.number}?text=${Uri.encode(message)}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "No se pudo abrir WhatsApp", Toast.LENGTH_SHORT).show()
            }
        }
    }
}