package fm.com.zo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class KayitActivity extends AppCompatActivity implements View.OnClickListener{

    private Button kayitOl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        //button declaration
        kayitOl =(Button) findViewById(R.id.kayitOl);
        Button parolaHatirlama =(Button) findViewById(R.id.parolaHatirlama);

        // adding listeners
        kayitOl.setOnClickListener(this);
        parolaHatirlama.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String email= ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String passwordConfirmation= ((EditText) findViewById(R.id.passwordConfirmation)).getText().toString();

        switch (v.getId()){
            case R.id.kayitOl:
                kayitOl.setEnabled(false);
                final ProgressDialog progressDialog = new ProgressDialog(KayitActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Bilgileriniz Gönderiliyor...");
                progressDialog.show();

                if(password.equals(passwordConfirmation) && email.contains("@")){
                    Toast.makeText(getApplicationContext(),"Mail Gönderildi",Toast.LENGTH_LONG).show();
                    //TODO bilgiler servera gönderilir activation link mail atilir...
                    break;
                }
                else{
                    Toast.makeText(getApplicationContext(),"Hata",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    break;
                }

            case R.id.parolaHatirlama:

                // hatirlatma activity baslat
                startActivity( new Intent(getApplicationContext(),HatirlatmaActivity.class) );
                break;

            default:
                System.out.println("not a valid ID ");
                break;

        }
    }
}
