package buk.project.mini.caesarsCrypt.ui.encryption

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EncryptionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Encryption"
    }
    val text: LiveData<String> = _text
}