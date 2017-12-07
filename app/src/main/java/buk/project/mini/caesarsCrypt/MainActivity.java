package buk.project.mini.caesarsCrypt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
r
        Button btnencode = (Button) findViewById(R.id.btnNxtEncode);
        Button btndecode = (Button) findViewById(R.id.btnNxtDecode);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,About_Ceasars.class);
                startActivity(i);
            }
        });

        btnencode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextencode = new Intent(MainActivity.this,Encode.class);
                startActivity(nextencode);
            }
        });
        btndecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextdecode = new Intent(MainActivity.this,Decode.class);
                startActivity(nextdecode);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
            alertbox.setTitle("caesarsCrypt");
            alertbox.setIcon(R.drawable.caesar);
            alertbox.setMessage("My Name is Umar Saidu Auna and I Love to Program");
            alertbox.setCancelable(false);
            alertbox.setNeutralButton(" ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertbox.setNegativeButton("Close", null);
            alertbox.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
