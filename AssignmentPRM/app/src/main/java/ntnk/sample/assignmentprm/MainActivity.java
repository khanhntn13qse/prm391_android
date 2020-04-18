package ntnk.sample.assignmentprm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<UserInfo> userList = new ArrayList<>();
    private static final int MAIN_ACT_REQUEST_CODE = 100;
    private static final int RESULT_CODE_REGISTER = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createUserList();
    }

    private void createUserList(){
        this.userList.add(new UserInfo("Nguyen Van A", true, "anv@gmail.com", "0983673111", "Cau Giay - Ha Noi"));
        this.userList.add(new UserInfo("Nguyen Van B", false, "bnv@gmail.com", "0983673222", "Cau Giay - Ha Noi"));
        this.userList.add(new UserInfo("Nguyen Van C", true, "cnv@gmail.com", "0983673333", "Cau Giay - Ha Noi"));
        this.userList.add(new UserInfo("Nguyen Van D", true, "dnv@gmail.com", "0983673444", "Cau Giay - Ha Noi"));
        this.userList.add(new UserInfo("Nguyen Van E", false, "env@gmail.com", "0983673555", "Cau Giay - Ha Noi"));
    }

    public void btnRegisterAction(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, MAIN_ACT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MAIN_ACT_REQUEST_CODE && resultCode == RESULT_CODE_REGISTER){

            UserInfo userInfo = (UserInfo) data.getSerializableExtra("newUserInfo");
            if(userInfo != null){
                userList.add(userInfo);
            }
        }
    }

    public void btnShowAction(View view){
       Intent intent = new Intent(this, UserListActivity.class);
       startActivity(intent);
    }

    public static ArrayList<UserInfo> getUserList() {
        return userList;
    }

}
