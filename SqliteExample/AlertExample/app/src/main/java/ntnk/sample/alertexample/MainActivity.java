package ntnk.sample.alertexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showAlert(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Announce");
        //content of message
        builder.setMessage("Hello, you will go to school again at March 2nd");

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showConfirmAlert(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Announce");
        builder.setMessage("Hello, Do you wanna go to school again at March 2nd?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You are brave!", Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You are careful!", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    int index = -1;
    boolean[] isSelectedItems;
    public void selectCourse(View view){
        final TextView textView = findViewById(R.id.textView);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose course");
        //content of dialog
        final String[] courses = {"Android Programming", "Java core", "C# programing", "Networking"};

        //set message : most priority access-----

        //single choice: way 1 --------------
//        builder.setItems(courses, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {//which is index of item
//                textView.setText(courses[which]);
//            }
//        });

        //single choice: way 2---------------
//        builder.setSingleChoiceItems(courses, 0, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                index = which;
//            }
//        });
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if(index > 0){
//                    textView.setText(courses[index]);
//                }
//            }
//        });

        //multiple choice--------------------------
        isSelectedItems = new boolean[courses.length];
        Arrays.fill(isSelectedItems, false);
        builder.setMultiChoiceItems(courses, isSelectedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                isSelectedItems[which] = isChecked;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String result = "";
                for(int i = 0 ; i < isSelectedItems.length; i++){
                    if(isSelectedItems[i]){
                        result += courses[i] + ", ";
                    }
                }
                textView.setText(result);
            }
        });

        //---------------------------------
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void selectDate(View view){
        final TextView textView = findViewById(R.id.textView2);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Pint: month : 0-11
                String s = dayOfMonth + "/" + (month + 1) + "/" + year;
                textView.setText(s);
            }
        });
        datePickerDialog.show();
    }

    public void selectTime(View view){
//        final TextView textView = findViewById(R.id.textView2);
//        TimePickerDialog timePickerDialog = new TimePickerDialog(this);
//        timePickerDialog.se
//                setSetListener(new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                //Pint: month : 0-11
//                String s = dayOfMonth + "/" + (month + 1) + "/" + year;
//                textView.setText(s);
//            }
//        });
//        datePickerDialog.show();
    }

    //customer DateDialog: create a class extend AlertDilog: note show() method.

}
