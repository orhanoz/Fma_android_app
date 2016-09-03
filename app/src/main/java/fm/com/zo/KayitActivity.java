package fm.com.zo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class KayitActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        //button declaration
        Button btn4=(Button) findViewById(R.id.button4);
        Button btn5=(Button) findViewById(R.id.button5);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText edt3=(EditText) findViewById(R.id.editText3);
        EditText edt4=(EditText) findViewById(R.id.editText4);
        EditText edt5=(EditText) findViewById(R.id.editText5);
        switch (v.getId()){
            case R.id.button4:
                //kayit tamam
                if(edt4.getText().toString().equals(edt5.getText().toString())&& edt3.getText().toString().contains("@")){
                    Toast.makeText(getApplicationContext(),"Mail Gönderildi",Toast.LENGTH_LONG).show();
                    //TODO bilgiler servera gönderilir activation link mail atilir...
                    break;
                }
                else{
                    Toast.makeText(getApplicationContext(),"Hata",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.button5:
                //parola hatirlatma
                Intent intent3=new Intent(getApplicationContext(),HatirlatmaActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
