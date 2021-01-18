package buk.project.mini.caesarsCrypt.old.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import buk.project.mini.caesarsCrypt.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Decode : AppCompatActivity() {
    lateinit var keyNumber: NumberPicker
    lateinit var editDecrypt: EditText
    lateinit var btnDecrypt: Button
    lateinit var btnClearDecrypt: Button
    private lateinit var clipboardManager: ClipboardManager
    private lateinit var clipData: ClipData
    private lateinit var btnPaste: Button
    private lateinit var btnShare: FloatingActionButton
    private lateinit var btnCopy: FloatingActionButton
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
        btnShare = findViewById(R.id.shareOthersDecrypt)
        btnCopy = findViewById(R.id.copyClipboardDecrypt)
        btnPaste = findViewById(R.id.btnPasteDecrypt)
        keyNumber.maxValue = 256
        keyNumber.minValue = 1
        keyNumber.value = 1
        decrypt()
        clearDecode()
        clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        btnPaste.setOnClickListener {
            val paste = clipboardManager.primaryClip
            val item = paste?.getItemAt(0)
            editDecrypt.setText(item?.text.toString())
        }

        btnCopy.setOnClickListener {
            val textCopy = txtDecrypt.text.toString()

            clipData = ClipData.newPlainText("text", textCopy)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()

        }

        btnShare.setOnClickListener {
            val shareText = txtDecrypt.text.toString()

            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(shareIntent, "Share via"))

        }
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