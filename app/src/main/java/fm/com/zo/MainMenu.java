package fm.com.zo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by root on 02.09.2016.
 */
public class MainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.cihaz_kurulum){
            Intent intent=new Intent(getApplicationContext(),CihazKurulumActivity.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId()==R.id.ev_ekle){
            Intent intent=new Intent(getApplicationContext(),EvEkleActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
