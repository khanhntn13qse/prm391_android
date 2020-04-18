package ntnk.sample.sharedpreferenceexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private CheckBox checkBox;

    private SeekBar seekBarRed;
    private SeekBar seekBarGreen;
    private SeekBar seekBarBlue;

    int red;
    int green;
    int blue;
    View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        checkBox = findViewById(R.id.checkBox);

        seekBarRed = findViewById(R.id.seekBarRed);
        seekBarGreen = findViewById(R.id.seekBarGreen);
        seekBarBlue = findViewById(R.id.seekBarBlue);

        root = findViewById(R.id.constrainLayout);

        setSeekBarAction();
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //name
        if(checkBox.isChecked()){//remember
            String name = editText.getText().toString().trim();
            editor.putString("name", name);
        }else {
            editor.remove("name");
        }

        //background color
        int red = seekBarRed.getProgress();
        int green = seekBarGreen.getProgress();
        int blue = seekBarBlue.getProgress();

        editor.putInt("red", red);
        editor.putInt("green", green);
        editor.putInt("blue", blue);

        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //name
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        editText.setText(name);

        //background color
        red = sharedPreferences.getInt("red", 0);
        green = sharedPreferences.getInt("green", 0);
        blue = sharedPreferences.getInt("blue", 0);

        setBackgroundColor(red, green, blue);

        //set seekbar
        seekBarRed.setProgress(red);
        seekBarGreen.setProgress(green);
        seekBarBlue.setProgress(blue);
    }

    private void setBackgroundColor(int red, int green, int blue){
        root.setBackgroundColor(Color.rgb(red, green, blue));
    }

    private void setSeekBarAction(){
        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = seekBarRed.getProgress();
                setBackgroundColor(red, green, blue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = seekBarGreen.getProgress();
                setBackgroundColor(red, green, blue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = seekBarBlue.getProgress();
                setBackgroundColor(red, green, blue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
