package cr.ac.itcr.puravidabirds.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cr.ac.itcr.puravidabirds.R;
import cr.ac.itcr.puravidabirds.entitys.Bird;

/**
 * Created by Laurens on 12/04/2016.
 */
public class AdapterBird  extends BaseAdapter{
    // Declare Variables
    Context context;
    String[] titulos;
    int[] imagenes;
    LayoutInflater inflater;

    protected Activity activity;
    protected ArrayList<Bird> items;
    public AdapterBird (Context context, ArrayList<Bird> items) {
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }
    public void clear() {
        items.clear();
    }
    public void addAll(ArrayList<Bird> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }
    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView nameB;
        TextView nameSc;

        ImageView imgImg;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview, parent, false);

        // Locate the TextViews in listview_item.xml
        nameB = (TextView) itemView.findViewById(R.id.name);
        nameSc = (TextView) itemView.findViewById(R.id.ScientificName);
        TextView ameSc = (TextView) itemView.findViewById(R.id._id);


        // Capture position and set to the TextViews
        nameB.setText(items.get(position).getBirdName());
        nameSc.setText(items.get(position).getBirdScientificName());
        ameSc.setText(String.valueOf(items.get(position).getId()));

        return itemView;
    }
}
