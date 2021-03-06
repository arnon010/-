package info.project.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterIncome extends BaseAdapter {

    private Context context; //context
    private ArrayList<Income> items; //data source of the list adapter

    //public constructor
    public CustomAdapterIncome(Context context, ArrayList<Income> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_row, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Income currentItem = (Income) getItem(position);
        viewHolder.itemName.setText(currentItem.getDetail_income());
        viewHolder.itemDescription.setText(currentItem.getValue_income());

        return convertView;
    }

    private class ViewHolder {
        TextView itemName;
        TextView itemDescription;

        public ViewHolder(View view) {
            itemName = view.findViewById(R.id.txtValue);
            itemDescription = view.findViewById(R.id.txtValue2);
        }

    }
}
