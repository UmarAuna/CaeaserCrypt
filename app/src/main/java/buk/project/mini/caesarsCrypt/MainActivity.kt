package buk.project.mini.caesarsCrypt

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import buk.project.mini.caesarsCrypt.Encode
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnEncode = findViewById<View>(R.id.btnNxtEncode) as Button
        val btnDecode = findViewById<View>(R.id.btnNxtDecode) as Button
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val i = Intent(this@MainActivity, AboutCaesar::class.java)
            startActivity(i)
        }
        btnEncode.setOnClickListener {
            val nextEncode = Intent(this@MainActivity, Encode::class.java)
            startActivity(nextEncode)
        }
        btnDecode.setOnClickListener {
            val nextDecode = Intent(this@MainActivity, Decode::class.java)
            startActivity(nextDecode)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_settings) {
            val alertbox = AlertDialog.Builder(this)
            alertbox.setTitle("caesarsCrypt")
            alertbox.setIcon(R.drawable.caesar)
            alertbox.setMessage("My Name is Umar Saidu Auna and I Love to Program")
            alertbox.setCancelable(false)
            alertbox.setNeutralButton(" ") { _, _ -> }
            alertbox.setNegativeButton("Close", null)
            alertbox.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}