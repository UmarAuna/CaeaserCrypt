package buk.project.mini.caesarsCrypt.ui.decrpytion

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

class DecryptionFragment : Fragment() {

    private lateinit var decryptionViewModel: DecryptionViewModel
    private lateinit var mainActivity: MainActivity2
    private lateinit var keyNumber: NumberPicker
    private lateinit var editDecrypt: EditText
    private lateinit var btnDecrypt: Button
    private lateinit var btnClearDecrypt: Button
    private lateinit var clipboardManager: ClipboardManager
    private lateinit var clipData: ClipData
    private lateinit var btnPaste: Button
    private lateinit var btnShare: FloatingActionButton
    private lateinit var btnCopy: FloatingActionButton
    private lateinit var txtDecrypt: TextView
    private lateinit var space: String
    private lateinit var decrypt: String
    private var shiftKey = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mainActivity = this.activity as MainActivity2
        decryptionViewModel =
                ViewModelProvider(this).get(DecryptionViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_decryption, container, false)

        editDecrypt  = root.findViewById(R.id.editTextDecrypt)
        btnDecrypt = root.findViewById(R.id.buttonDecrypt)
        btnClearDecrypt = root.findViewById(R.id.btnDecryptClear)
        txtDecrypt = root.findViewById(R.id.textViewDecrypt)
        keyNumber = root.findViewById(R.id.numberPickerDecrypt)
        btnShare = root.findViewById(R.id.shareOthersDecrypt)
        btnCopy = root.findViewById(R.id.copyClipboardDecrypt)
        btnPaste = root.findViewById(R.id.btnPasteDecrypt)
        keyNumber.maxValue = 256
        keyNumber.minValue = 1
        keyNumber.value = 1

        decryptionViewModel.text.observe(viewLifecycleOwner, {
            //mainActivity.supportActionBar?.title = it
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        decrypt()
        clearDecode()
        clipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        btnPaste.setOnClickListener {
            val paste = clipboardManager.primaryClip
            val item = paste?.getItemAt(0)
            editDecrypt.setText(item?.text.toString())
        }

        btnCopy.setOnClickListener {
            val textCopy = txtDecrypt.text.toString()

            clipData = ClipData.newPlainText("text", textCopy)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_LONG).show()

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
        shiftKey = keyNumber.value
        decrypt = editDecrypt.text.toString()
        for (element in decrypt) {
            var d = element.toInt()
            d = (d - shiftKey) % 256
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


    companion object {
        fun newInstance(): DecryptionFragment = DecryptionFragment()
    }
}