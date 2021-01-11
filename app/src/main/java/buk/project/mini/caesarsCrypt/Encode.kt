package buk.project.mini.caesarsCrypt

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Encode : AppCompatActivity() {
    var keyNumber: NumberPicker? = null
    private var btnEncrypt: Button? = null
    private var btnClearEncrypt: Button? = null
    private var txtEncrypt: TextView? = null
    private var space: String? = null
    var encrypt: String? = null
    private var shiftKey = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encode)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Editencrypt = findViewById<View>(R.id.editTextEncrypt) as EditText
        btnEncrypt = findViewById<View>(R.id.buttonEncrypt) as Button
        btnClearEncrypt = findViewById<View>(R.id.btnEncryptClear) as Button
        txtEncrypt = findViewById<View>(R.id.textViewEncrypt) as TextView
        keyNumber = findViewById<View>(R.id.numberPickerencrypt) as NumberPicker
        keyNumber!!.maxValue = 256
        keyNumber!!.minValue = 1
        keyNumber!!.value = 1
        encrypt()
        clearEncode()
    }

    private fun encrypt() {
        btnEncrypt!!.setOnClickListener { encodeCaesar() }
    }

    private fun encodeCaesar() {
        space = ""
        shiftKey = keyNumber!!.value
        encrypt = Editencrypt!!.text.toString()
        for (x in encrypt!!.indices) {
            if (Character.isWhitespace(encrypt!![x])) {
                space += " "
                continue
            }
            var e = encrypt!![x].toInt()
            e = (e + shiftKey) % 256
            if (e > 256) e -= 256
            space += e.toChar()
        }
        txtEncrypt!!.visibility = View.VISIBLE
        txtEncrypt!!.text = space
    }

    private fun clearEncode() {
        btnClearEncrypt!!.setOnClickListener {
            Editencrypt!!.setText(" ")
            txtEncrypt!!.text = " "
            keyNumber!!.value = 1
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        var Editencrypt: EditText? = null
    }
}