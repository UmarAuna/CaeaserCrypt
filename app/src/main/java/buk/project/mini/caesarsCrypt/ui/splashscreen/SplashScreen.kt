package buk.project.mini.caesarsCrypt.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import buk.project.mini.caesarsCrypt.R
import buk.project.mini.caesarsCrypt.ui.MainActivity2
import kotlinx.coroutines.*
import java.util.*

class SplashScreen : AppCompatActivity() {
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        activityScope.launch {
            delay(SPLASH_TIME_OUT)
            val intent = Intent(this@SplashScreen, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

    companion object {
        const val SPLASH_TIME_OUT: Long = 1000
    }
}
