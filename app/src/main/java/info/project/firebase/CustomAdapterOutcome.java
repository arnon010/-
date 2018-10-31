package info.project.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterOutcome extends BaseAdapter {

    private Context context; //context
    private ArrayList<Outcome> items; //data source of the list adapter

    //public constructor
    public CustomAdapterOutcome(Context context, ArrayList<Outcome> items) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_row2, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Outcome currentItem = (Outcome) getItem(position);
        viewHolder.itemName.setText(currentItem.getDetail_outcome());
        viewHolder.itemDescription.setText(currentItem.getValue_outcome());

        return convertView;
    }

    private class ViewHolder {
        TextView itemName;
        TextView itemDescription;

        public ViewHolder(View view) {
            itemName = view.findViewById(R.id.txtDetailOut);
            itemDescription = view.findViewById(R.id.txtValueOut);
        }

    }
}
