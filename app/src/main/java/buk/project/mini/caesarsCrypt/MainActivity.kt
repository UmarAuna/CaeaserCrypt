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

        val btnEncode: Button = findViewById(R.id.btnNxtEncode)
        val btnDecode: Button = findViewById(R.id.btnNxtDecode)
        val fab: FloatingActionButton = findViewById(R.id.fab)

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
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            val alertBox = AlertDialog.Builder(this)
            alertBox.setTitle("caesarsCrypt")
            alertBox.setIcon(R.drawable.caesar)
            alertBox.setMessage("Version 1.0")
            alertBox.setCancelable(true)
            alertBox.setNeutralButton(" ") { _, _ -> }
            alertBox.setNegativeButton("Close", null)
            alertBox.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}