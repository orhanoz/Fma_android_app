package fm.com.zo;

import fm.com.zo.CircularSeekBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HedefSicaklikActivity extends AppCompatActivity {
    CircularSeekBar seekBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hedef_sicaklik);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSeekBar();




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }

    public void setSeekBar(){
        seekBar = (CircularSeekBar) findViewById(R.id.seek_bar);
       // textView = (TextView) findViewById(R.id.textView);
       // textView.setText("Deger: "+seekBar.getProgress()+" / "+seekBar.getMax());

        seekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            int progress_value;
            @Override
            public void onProgressChanged(CircularSeekBar SeekBar, int progress, boolean fromUser) {
                progress_value=progress;
          //      textView.setText("Deger: "+progress+" / "+seekBar.getMax());
                Toast.makeText(getApplicationContext(),"prog changed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"stop track",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"start track",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
