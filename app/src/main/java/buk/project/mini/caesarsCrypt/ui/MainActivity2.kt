package buk.project.mini.caesarsCrypt.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import buk.project.mini.caesarsCrypt.R
import buk.project.mini.caesarsCrypt.ui.decrpytion.DecryptionFragment
import buk.project.mini.caesarsCrypt.ui.encryption.EncryptionFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_encryption -> {
                val encryptionFragment = EncryptionFragment.newInstance()
                openFragment(encryptionFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_decryption -> {
                val decryptionFragment = DecryptionFragment.newInstance()
                openFragment(decryptionFragment)
                return@OnNavigationItemSelectedListener true
            }
          /*  R.id.navigation_about -> {
                val aboutFragment = AboutCaesarFragment.newInstance()
                openFragment(aboutFragment)
                return@OnNavigationItemSelectedListener true
            }*/
        }
        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val bottomNav: BottomNavigationView = findViewById(R.id.nav_view)

        val transaction = supportFragmentManager.beginTransaction()
        val encryptionFragment = EncryptionFragment.newInstance()
        transaction.replace(R.id.container, encryptionFragment, EncryptionFragment::class.java.name)
        transaction.commit()
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            val alertBox = AlertDialog.Builder(this)
            alertBox.setTitle(getString(R.string.boye))
            alertBox.setIcon(R.drawable.app_icon)
            alertBox.setMessage(getString(R.string.boye_details))
            alertBox.setCancelable(true)
            alertBox.setNeutralButton(" ") { _, _ -> }
            alertBox.setNegativeButton("Close", null)
            alertBox.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
