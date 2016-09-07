package fm.com.zo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        //button declaration
        Button girisYap=(Button) findViewById(R.id.girisYap);
        Button kayitOl=(Button) findViewById(R.id.kayitOl);
        Button passwordConf=(Button) findViewById(R.id.parolaHatirlatma);

        // listener Initialization
        girisYap.setOnClickListener(this);
        kayitOl.setOnClickListener(this);
        passwordConf.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        //edit text declaration
        String email= ((EditText) findViewById(R.id.email)).getText().toString();
        String password=((EditText) findViewById(R.id.password)).getText().toString();

        switch(v.getId()){
            case R.id.girisYap:
                //login button
                //TODO server ile connection ve kullanıcı adı parola kontrolü
                if(email.equals("admin")&& password.equals("admin")){
                    //verified go to 2 activity
                    Toast.makeText(getApplicationContext(),"Hoşgeldiniz",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),MainMenu.class);
                    startActivity(intent);
                    break;
                }
                else{
                    Toast.makeText(getApplicationContext(),"Hatalı kullanıcı adı ve parola",Toast.LENGTH_LONG).show();
                    break;
                }
            case R.id.kayitOl:
                //kayit button
                Intent intent2=new Intent(getApplicationContext(),KayitActivity.class);
                startActivity(intent2);
                break;
            case R.id.parolaHatirlatma:
                //parola hatirlat
                Intent intent3=new Intent(getApplicationContext(),HatirlatmaActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
