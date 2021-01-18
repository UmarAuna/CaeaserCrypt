package buk.project.mini.caesarsCrypt.ui.encryption

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import buk.project.mini.caesarsCrypt.ui.MainActivity2
import buk.project.mini.caesarsCrypt.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EncryptionFragment : Fragment() {

    private lateinit var encryptionViewModel: EncryptionViewModel
    private lateinit var mainActivity: MainActivity2
    private lateinit var keyNumber: NumberPicker
    private lateinit var btnEncrypt: Button
    private lateinit var btnClearEncrypt: Button
    private lateinit var clipboardManager: ClipboardManager
    private lateinit var clipData: ClipData
    private lateinit var btnPaste: Button
    private lateinit var txtEncrypt: TextView
    private lateinit var editEncrypt: EditText
    private lateinit var btnShare: FloatingActionButton
    private lateinit var btnCopy: FloatingActionButton
    private lateinit var space: String
    private lateinit var encrypt: String
    private var shiftKey = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mainActivity = this.activity as MainActivity2
        encryptionViewModel = ViewModelProvider(this).get(EncryptionViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_encryption, container, false)
        editEncrypt = root.findViewById(R.id.editTextEncrypt)
        btnEncrypt = root.findViewById(R.id.buttonEncrypt)
        btnClearEncrypt = root.findViewById(R.id.btnEncryptClear)
        txtEncrypt = root.findViewById(R.id.textViewEncrypt)
        keyNumber = root.findViewById(R.id.numberPickerencrypt)
        btnShare = root.findViewById(R.id.shareOthers)
        btnCopy = root.findViewById(R.id.copyClipboard)
        btnPaste = root.findViewById(R.id.btnPaste)
        keyNumber.maxValue = 256
        keyNumber.minValue = 1
        keyNumber.value = 1

        encryptionViewModel.text.observe(viewLifecycleOwner, {
            //mainActivity.supportActionBar?.title = it
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        encrypt()
        clearEncode()
        clipboardManager =  activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        btnPaste.setOnClickListener {
            val paste = clipboardManager.primaryClip
            val item = paste?.getItemAt(0)
            editEncrypt.setText(item?.text.toString())
        }

        btnCopy.setOnClickListener {
            val textCopy = txtEncrypt.text.toString()

            clipData = ClipData.newPlainText("text", textCopy)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_LONG).show()

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

    companion object {
        fun newInstance(): EncryptionFragment = EncryptionFragment()
    }
}