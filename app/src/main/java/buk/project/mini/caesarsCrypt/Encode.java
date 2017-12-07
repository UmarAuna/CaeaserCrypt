package buk.project.mini.caesarsCrypt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;



public class Encode extends AppCompatActivity {
    public NumberPicker keyNumber;
    Button btnencrypt, btnclearencrypt;
    TextView Txtencrypt;
    public static EditText Editencrypt;
    String Space,encrypt;
    int shiftkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Editencrypt = (EditText)findViewById(R.id.editTextEncrypt);
        btnencrypt = (Button)findViewById(R.id.buttonEncrypt);
        btnclearencrypt = (Button)findViewById(R.id.btnEncryptClear);
        Txtencrypt=(TextView)findViewById(R.id.textViewEncrypt);
        keyNumber = (NumberPicker)findViewById(R.id.numberPickerencrypt);
        keyNumber.setMaxValue(256);
        keyNumber.setMinValue(1);
        keyNumber.setValue(1);
        Encrypt();
        ClearEncode();


    }

    public void Encrypt(){
        btnencrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encode_caeser();
            }
        });
    }
    public void encode_caeser(){
        Space = "";
        shiftkey = keyNumber.getValue();
        encrypt = Editencrypt.getText().toString();
        for(int x=0;x<encrypt.length();x++){
            if(Character.isWhitespace(encrypt.charAt(x))){
                Space+=" ";
                continue;
            }
            int e= encrypt.charAt(x);
            e =(e+shiftkey)%256;
            if(e>256)
                e=e-256;
            Space+=(char)e;
        }
        Txtencrypt.setVisibility(View.VISIBLE);
        Txtencrypt.setText(Space);

    }

    public void ClearEncode(){
        btnclearencrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editencrypt.setText(" ");
                Txtencrypt.setText(" ");
                keyNumber.setValue(1);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}
