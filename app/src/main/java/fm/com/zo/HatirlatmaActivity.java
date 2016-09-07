package fm.com.zo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HatirlatmaActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hatirlatma);
        //button declaration
        Button login=(Button) findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText edt6=(EditText) findViewById(R.id.editText6);
        if(v.getId()==R.id.login){
            //Todo recovery maili atilacak
            Toast.makeText(getApplicationContext(),"Mail gönderildi",Toast.LENGTH_LONG).show();
        }
    }
}
