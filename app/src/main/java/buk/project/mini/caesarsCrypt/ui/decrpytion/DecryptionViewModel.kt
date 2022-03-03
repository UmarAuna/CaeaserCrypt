package buk.project.mini.caesarsCrypt.ui.decrpytion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DecryptionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Decryption"
    }
    val text: LiveData<String> = _text
}
