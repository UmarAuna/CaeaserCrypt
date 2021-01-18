package buk.project.mini.caesarsCrypt.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import buk.project.mini.caesarsCrypt.ui.MainActivity2
import buk.project.mini.caesarsCrypt.R
import kotlinx.coroutines.*
import java.util.*

class SplashScreen : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 1000
    val activityScope = CoroutineScope(Dispatchers.Main)
    //var timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        activityScope.launch {
            delay(SPLASH_TIME_OUT)
            val intent =Intent(this@SplashScreen, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

        /*timer.schedule(3000) {
            var intent = Intent(this@TimerSplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }*/
    }

    override fun onPause() {
        activityScope.cancel()
        //timer.cancel()
        super.onPause()
    }
}