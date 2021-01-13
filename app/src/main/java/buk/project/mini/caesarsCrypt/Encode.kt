package buk.project.mini.caesarsCrypt

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Encode : AppCompatActivity() {
    lateinit var keyNumber: NumberPicker
    private lateinit var btnEncrypt: Button
    private lateinit var btnClearEncrypt: Button
    private lateinit var clipboardManager: ClipboardManager
    private lateinit var clipData:ClipData
    lateinit var btnPaste: Button
    private lateinit var txtEncrypt: TextView
    lateinit var editEncrypt: EditText
    lateinit var btnShare: FloatingActionButton
    lateinit var btnCopy: FloatingActionButton
    private lateinit var space: String
    lateinit var encrypt: String
    private var shiftKey = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encode)
        title = getString(R.string.encrypt)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        editEncrypt = findViewById(R.id.editTextEncrypt)
        btnEncrypt = findViewById(R.id.buttonEncrypt)
        btnClearEncrypt = findViewById(R.id.btnEncryptClear)
        txtEncrypt = findViewById(R.id.textViewEncrypt)
        keyNumber = findViewById(R.id.numberPickerencrypt)
        btnShare = findViewById(R.id.shareOthers)
        btnCopy = findViewById(R.id.copyClipboard)
        btnPaste = findViewById(R.id.btnPaste)
        keyNumber.maxValue = 256
        keyNumber.minValue = 1
        keyNumber.value = 1
        encrypt()
        clearEncode()
        clipboardManager =  getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        btnPaste.setOnClickListener {
            val paste = clipboardManager.primaryClip
            val item = paste?.getItemAt(0)
            editEncrypt.setText(item?.text.toString())
        }

        btnCopy.setOnClickListener {
            val textCopy = txtEncrypt.text.toString()

            clipData = ClipData.newPlainText("text", textCopy)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()

        }

        btnShare.setOnClickListener {
            val shareText = txtEncrypt.text.toString()

            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(shareIntent, "Share via"))

        }
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