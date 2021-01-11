package buk.project.mini.caesarsCrypt

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Decode : AppCompatActivity() {
    lateinit var keyNumber: NumberPicker
    lateinit var editDecrypt: EditText
    lateinit var btnDecrypt: Button
    lateinit var btnClearDecrypt: Button
    lateinit var txtDecrypt: TextView
    private lateinit var space: String
    lateinit var decrypt: String
    var shiftkey = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decode)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        editDecrypt = findViewById<View>(R.id.editTextDecrypt) as EditText
        btnDecrypt = findViewById<View>(R.id.buttonDecrypt) as Button
        btnClearDecrypt = findViewById<View>(R.id.btnDecryptClear) as Button
        txtDecrypt = findViewById<View>(R.id.textViewDecrypt) as TextView
        keyNumber = findViewById<View>(R.id.numberPickerDecrypt) as NumberPicker
        keyNumber.maxValue = 256
        keyNumber.minValue = 1
        keyNumber.value = 1
        decrypt()
        clearDecode()
    }

    private fun decrypt() {
        btnDecrypt!!.setOnClickListener { decodeCaesar() }
    }

    private fun decodeCaesar() {
        space = ""
        shiftkey = keyNumber.value
        decrypt = editDecrypt.text.toString()
        for (element in decrypt) {
            var d = element.toInt()
            d = (d - shiftkey) % 256
            if (d < 0) d += 256
            space += d.toChar()
        }
        txtDecrypt.visibility = View.VISIBLE
        txtDecrypt.text = space
    }

    private fun clearDecode() {
        btnClearDecrypt.setOnClickListener {
            editDecrypt.setText(" ")
            txtDecrypt.text = " "
            keyNumber.value = 1
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}