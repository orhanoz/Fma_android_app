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
        Button kayitOl =(Button) findViewById(R.id.kayitOl);
        Button parolaHatirlama =(Button) findViewById(R.id.parolaHatirlama);

        // adding listeners
        kayitOl.setOnClickListener(this);
        parolaHatirlama.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        String email= ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String passwordComfirmation= ((EditText) findViewById(R.id.passwordComfirmation)).getText().toString();

        switch (v.getId()){

            case R.id.kayitOl:

                if(password.equals(passwordComfirmation) && email.contains("@")){

                    Toast.makeText(getApplicationContext(),"Mail Gönderildi",Toast.LENGTH_LONG).show();
                    //TODO bilgiler servera gönderilir activation link mail atilir...
                    break;

                }
                else{
                    Toast.makeText(getApplicationContext(),"Hata",Toast.LENGTH_LONG).show();
                    break;
                }

            case R.id.parolaHatirlama:

                // hatirlatma activity baslat
                startActivity( new Intent(getApplicationContext(),HatirlatmaActivity.class) );
                break;

            default:
                System.out.println("not a valid component id");
                break;

        }

    }
}
