package buk.project.mini.caesarsCrypt.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutCaesarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "About Caesar"
    }
    val text: LiveData<String> = _text
}