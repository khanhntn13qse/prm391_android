package ntnk.sample.customerlisview;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class PersonAdapter extends BaseAdapter {

    private List<Person> list;
    private Activity activity;
    private int layout; //id of layout

    public PersonAdapter(List<Person> list, Activity activity, int layout) {
        this.list = list;
        this.activity = activity;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        TextView tvName = null;
        TextView tvEmail = null;
        Button btnCall = null;

        if(view == null){
            view = activity.getLayoutInflater().inflate(R.layout.person_layout, null);
            tvName = view.findViewById(R.id.tvName);
            tvEmail = view.findViewById(R.id.tvEmail);
            btnCall = view.findViewById(R.id.btnCall);
            view.setTag(R.id.tvName);
            view.setTag(R.id.tvEmail);
            view.setTag(R.id.btnCall);

        }else {
            tvName = (TextView) view.getTag(R.id.tvName);
            tvEmail = (TextView) view.getTag(R.id.tvEmail);
            btnCall = (Button) view.getTag(R.id.btnCall);
        }

        final Person person = list.get(position);
        tvName.setText(person.getName());
        tvEmail.setText(person.getEmail());
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = person.getPhone();

                //code for call
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));//Intent(action, uri)

                // Here, thisActivity is the current activity
                if (ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                            Manifest.permission.CALL_PHONE)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(activity,
                                new String[]{Manifest.permission.CALL_PHONE},
                                100);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                } else {
                    // Permission has already been granted
                }

                activity.startActivity(intent);

            }
        });
        return view;
    }
}
