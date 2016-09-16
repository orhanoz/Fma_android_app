package fm.com.zo;

import fm.com.zo.CircularSeekBar;
import fm.com.zo.R;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.SeekBar;

public class HedefSicaklikActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hedef_sicaklik);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final SeekBar progressBar = (SeekBar) findViewById(R.id.progressBar);
        progressBar.setProgress(10);

        ObjectAnimator animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 100); // see this max value coming back here, we animate towards that value
        animation.setDuration (5000); //in milliseconds
        animation.setInterpolator (new DecelerateInterpolator());
        animation.start ();
        CircularSeekBar seekbar = (CircularSeekBar) findViewById(R.id.seekBar);
        seekbar.getProgress();
        seekbar.setProgress(50);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
