package ntnk.sample.assignmentprm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private ArrayList<UserInfo> userList;
    private static final int THIS_REQUEST_CODE = 200;
    private static final int RESULT_CODE_REGISTER = 101;
    ListView listView;
    UserAdapter userAdapter;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        this.listView = findViewById(R.id.userList_listView);
        this.buttonBack = findViewById(R.id.userList_btnBack);

        initializationListView();
        registerForContextMenu(listView);
        initializationButtonBack();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_userlist, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final UserInfo seletedUser = (UserInfo) this.listView.getItemAtPosition(info.position);
        switch (item.getItemId()){
            case R.id.menu_register:{
                Intent intent  = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent, THIS_REQUEST_CODE);
                break;
            }

            case R.id.menu_delete: {
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure to delete?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                userList.remove(seletedUser);
                                userAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                break;
            }
            default:
                return false;

        }
        return true;

    }

    private void initializationButtonBack(){
        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initializationListView(){
        this.userList = MainActivity.getUserList();
        userAdapter = new UserAdapter(this, userList);
        listView.setAdapter(userAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == THIS_REQUEST_CODE && resultCode == RESULT_CODE_REGISTER){
            UserInfo userInfo = (UserInfo) data.getSerializableExtra("newUserInfo");
            if(userInfo != null){
                userList.add(userInfo);
                userAdapter.notifyDataSetChanged();
            }
        }
    }

}
