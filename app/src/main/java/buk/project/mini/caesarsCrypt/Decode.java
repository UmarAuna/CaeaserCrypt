package buk.project.mini.caesarsCrypt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;


public class Decode extends AppCompatActivity {
    public NumberPicker keyNumber;
    EditText editDecrypt;
    Button btnDecrypt,btnClearDecrypt;
    TextView TxtDecrypt;
    String Space,decrypt;
    int shiftkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editDecrypt =(EditText)findViewById(R.id.editTextDecrypt);
        btnDecrypt=(Button)findViewById(R.id.buttonDecrypt);
        btnClearDecrypt=(Button)findViewById(R.id.btnDecryptClear);
        TxtDecrypt=(TextView) findViewById(R.id.textViewDecrypt);
        keyNumber = (NumberPicker)findViewById(R.id.numberPickerDecrypt);
        keyNumber.setMaxValue(256);
        keyNumber.setMinValue(1);
        keyNumber.setValue(1);
        Decrypt();
        ClearDecode();
    }
    public void Decrypt(){
        btnDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decode_caeser();
            }
        });
    }
    public void decode_caeser(){
        Space="";
        shiftkey=keyNumber.getValue();
        decrypt=editDecrypt.getText().toString();
        for(int y=0;y<decrypt.length();y++){
            int d = decrypt.charAt(y);
            d=(d-shiftkey)%256;
            if(d<0)
                d=d+256;
            Space+=(char)d;
        }
        TxtDecrypt.setVisibility(View.VISIBLE);
        TxtDecrypt.setText(Space);
    }

    public void ClearDecode(){
        btnClearDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDecrypt.setText(" ");
                TxtDecrypt.setText(" ");
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
