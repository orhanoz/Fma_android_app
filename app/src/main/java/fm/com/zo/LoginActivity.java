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
        setContentView(R.layout.activity_login);

        //button declaration
        Button btn=(Button) findViewById(R.id.button);
        Button btn2=(Button) findViewById(R.id.button2);
        Button btn3=(Button) findViewById(R.id.button3);
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //edit text declaration
        EditText edt=(EditText) findViewById(R.id.editText);
        EditText edt2=(EditText) findViewById(R.id.editText2);
        switch(v.getId()){
            case R.id.button:
                //login button
                //TODO server ile connection ve kullanıcı adı parola kontrolü
                if(edt.getText().toString().equals("admin")&& edt2.getText().toString().equals("admin")){
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
            case R.id.button2:
                //kayit button
                Intent intent2=new Intent(getApplicationContext(),KayitActivity.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                //parola hatirlat
                Intent intent3=new Intent(getApplicationContext(),HatirlatmaActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
