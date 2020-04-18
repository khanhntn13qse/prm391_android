package ntnk.sample.assignmentprm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    private static final int RESULT_CODE_REGISTER = 101;

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private RadioButton radioButtonMale;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        assginComponent();

        addActionBtnSave();

    }

    private void assginComponent(){
        this.editTextName = findViewById(R.id.register_ipName);
        this.editTextEmail = findViewById(R.id.register_ipEmail);
        this.editTextAddress = findViewById(R.id.register_ipAddress);
        this.editTextPhone = findViewById(R.id.register_ipPhone);

        this.radioButtonMale = findViewById(R.id.register_rbtnMale);
        this.buttonSave = findViewById(R.id.register_btnSave);
    }

    private void addActionBtnSave(){
        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get input data
                UserInfo userInfo = new UserInfo();
                userInfo.setName(editTextName.getText().toString().trim());
                userInfo.setAddress(editTextAddress.getText().toString().trim());
                userInfo.setPhone(editTextPhone.getText().toString().trim());
                userInfo.setEmail(editTextEmail.getText().toString().trim());
                userInfo.setGender(radioButtonMale.isChecked());

                //save into database....
                Intent intent = new Intent();
                intent.putExtra("newUserInfo", userInfo);
                setResult(RESULT_CODE_REGISTER, intent);

//                finish();
                //show mess save successfully
                new AlertDialog.Builder(RegisterActivity.this)
                        .setMessage("User is registered.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            }
        });
    }

}
