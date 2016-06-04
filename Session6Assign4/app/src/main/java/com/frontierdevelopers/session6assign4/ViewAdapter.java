package com.frontierdevelopers.session6assign4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Elson on 5/11/2016.
 */public class ViewAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] names;
    private final String[] number;

    public ViewAdapter(Context c,String[] names,String[] number ) {
        mContext = c;
        this.names = names;
        this.number = number;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.item_view, null);
            TextView textView = (TextView) grid.findViewById(R.id.textView);
            TextView textView2 = (TextView) grid.findViewById(R.id.textView2);
            textView.setText(names[position]);
            textView2.setText(number[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
