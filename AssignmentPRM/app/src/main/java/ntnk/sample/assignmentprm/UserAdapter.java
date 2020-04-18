package ntnk.sample.assignmentprm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private List<UserInfo> list;

    public UserAdapter(Context context, List<UserInfo> list) {
        this.context = context;
        this.list = list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView textViewName;
        final Button buttonDetail;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_listview_item, parent, false);
        }

        textViewName = (TextView) convertView.findViewById(R.id.layoutlistview_textviewName);
            buttonDetail = (Button) convertView.findViewById(R.id.layoutlistview_btnDetail);
        final UserInfo userInfo = (UserInfo) getItem(position);

        textViewName.setText(userInfo.getName());
        buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("userInfo", userInfo);
                context.startActivity(intent);
            }
        });
        return convertView;
    }


}
