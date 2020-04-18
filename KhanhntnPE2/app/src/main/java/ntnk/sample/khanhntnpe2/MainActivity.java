package ntnk.sample.khanhntnpe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private RadioButton radioButtonRed;
    private RadioButton radioButtonBlue;
    private RadioButton radioButtonGreen;
    private RadioGroup radioGroup;

    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        radioButtonBlue = findViewById(R.id.radioButtonBlue);
        radioButtonGreen = findViewById(R.id.radioButtonGreen);
        radioButtonRed = findViewById(R.id.radioButtonRed);
        seekBar = findViewById(R.id.seekBar);
        radioGroup = findViewById(R.id.radioGroup);

        setProgressBarAction();
    }

    public void colorAction(View view){
        if(radioButtonRed.isChecked()){
            textView.setTextColor(Color.RED);
        }else if(radioButtonGreen.isChecked()){
            textView.setTextColor(Color.GREEN);
        }else if(radioButtonBlue.isChecked()){
            textView.setTextColor(Color.BLUE);
        }
    }

    public void setProgressBarAction(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int textSize = seekBar.getProgress();
                textView.setTextSize(textSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void saveBtnAction(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //color
        if(radioButtonBlue.isChecked()){//remember
            editor.putInt("color", Color.BLUE);
        }else if(radioButtonGreen.isChecked()){
            editor.putInt("color", Color.GREEN);
        } else if(radioButtonRed.isChecked()){
            editor.putInt("color", Color.RED);
        }

        //textsize
        editor.putInt("textSize", seekBar.getProgress());

        editor.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();

        //get value
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        int color = sharedPreferences.getInt("color", Color.BLACK);
        int textSize = sharedPreferences.getInt("textSize", 12);

        //set value
        textView.setTextColor(color);
        textView.setTextSize(textSize);

        if(color == Color.RED){
            radioButtonRed.setChecked(true);
        }else if(color == Color.GREEN){
            radioButtonGreen.setChecked(true);
        }else if(color == Color.BLUE){
            radioButtonBlue.setChecked(true);
        }

        seekBar.setProgress(textSize);
    }
}
