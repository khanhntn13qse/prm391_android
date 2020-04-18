package ntnk.sample.assignmentprm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserDetailActivity extends AppCompatActivity {

    Button buttonCall;
    Button buttonBack;
    TextView textViewName;
    TextView textViewEmail;
    TextView textViewPhone;
    TextView textViewAddress;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        assignComponent();
        loadDetailInformation();
        addAction();
    }

    private void assignComponent(){
        this.textViewName = findViewById(R.id.userDetail_tvName);
        this.textViewEmail = findViewById(R.id.userDetail_tvEmail);
        this.textViewAddress = findViewById(R.id.userDetail_tvAddress);
        this.textViewPhone = findViewById(R.id.userDetail_tvPhone);

        this.radioButtonFemale = findViewById(R.id.userDetail_rbtnFemale);
        this.radioButtonMale = findViewById(R.id.userDetail_rbtnMale);

        this.buttonCall = findViewById(R.id.userDetail_btnCall);
        this.buttonBack = findViewById(R.id.userDetail_btnBack);
    }

    private void loadDetailInformation(){
        Intent intent = getIntent();
        userInfo = (UserInfo) intent.getSerializableExtra("userInfo");
        if(intent == null){
            return;
        }

        this.textViewName.setText(userInfo.getName());
        this.textViewAddress.setText(userInfo.getAddress());
        this.textViewEmail.setText(userInfo.getEmail());
        this.textViewPhone.setText(userInfo.getPhone());
        if(userInfo.isGender()){
            this.radioButtonMale.setChecked(true);
        }else{
            this.radioButtonFemale.setChecked(true);
        }
    }

    private void addAction(){
        this.buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               makePhoneCall();
            }
        });

        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void makePhoneCall(){
        if(userInfo == null){
            return;
        }

        String phone = userInfo.getPhone().trim();

        //code for call
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));//Intent(action, uri)

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(UserDetailActivity.this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Please permission call at setting", Toast.LENGTH_LONG).show();

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(UserDetailActivity.this,
                    Manifest.permission.CALL_PHONE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(UserDetailActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        100);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            startActivity(intent);
        }


    }

}
