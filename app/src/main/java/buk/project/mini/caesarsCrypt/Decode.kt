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
        title = getString(R.string.decrypt)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        editDecrypt  = findViewById(R.id.editTextDecrypt)
        btnDecrypt = findViewById(R.id.buttonDecrypt)
        btnClearDecrypt = findViewById(R.id.btnDecryptClear)
        txtDecrypt = findViewById(R.id.textViewDecrypt)
        keyNumber = findViewById(R.id.numberPickerDecrypt)
        keyNumber.maxValue = 256
        keyNumber.minValue = 1
        keyNumber.value = 1
        decrypt()
        clearDecode()
    }

    private fun decrypt() {
        btnDecrypt.setOnClickListener { decodeCaesar() }
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