package info.androidhive.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context mContext;
    String[] strDetail;
    String[] strValue;

    public CustomAdapter(Context mContext, String[] strDetail, String[] strValue) {
        this.mContext = mContext;
        this.strDetail = strDetail;
        this.strValue = strValue;
    }

    @Override
    public int getCount() {
        return strDetail.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
            convertView = mInflater.inflate(R.layout.listview_row, parent, false);

//        TextView txtDetail = convertView.findViewById(R.id.txtDetail);
//        txtDetail.setText(strDetail[position]);

        TextView txtValue = convertView.findViewById(R.id.txtValue);
        txtValue.setText(strValue[position]);



        return convertView;
    }
}
