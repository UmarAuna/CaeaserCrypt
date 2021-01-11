package buk.project.mini.caesarsCrypt

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Encode : AppCompatActivity() {
    lateinit var keyNumber: NumberPicker
    private lateinit var btnEncrypt: Button
    private lateinit var btnClearEncrypt: Button
    private lateinit var txtEncrypt: TextView
    lateinit var editEncrypt: EditText
    private lateinit var space: String
    lateinit var encrypt: String
    private var shiftKey = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encode)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        editEncrypt = findViewById(R.id.editTextEncrypt)
        btnEncrypt = findViewById(R.id.buttonEncrypt)
        btnClearEncrypt = findViewById(R.id.btnEncryptClear)
        txtEncrypt = findViewById(R.id.textViewEncrypt)
        keyNumber = findViewById(R.id.numberPickerencrypt)
        keyNumber.maxValue = 256
        keyNumber.minValue = 1
        keyNumber.value = 1
        encrypt()
        clearEncode()
    }

    private fun encrypt() {
        btnEncrypt.setOnClickListener { encodeCaesar() }
    }

    private fun encodeCaesar() {
        space = ""
        shiftKey = keyNumber.value
        encrypt = editEncrypt.text.toString()
        for (x in encrypt.indices) {
            if (Character.isWhitespace(encrypt[x])) {
                space += " "
                continue
            }
            var e = encrypt[x].toInt()
            e = (e + shiftKey) % 256
            if (e > 256) e -= 256
            space += e.toChar()
        }
        txtEncrypt.visibility = View.VISIBLE
        txtEncrypt.text = space
    }

    private fun clearEncode() {
        btnClearEncrypt.setOnClickListener {
            editEncrypt.setText(" ")
            txtEncrypt.text = " "
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