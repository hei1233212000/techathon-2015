package com.techathon.healthtec.app;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NichChau on 4/7/15.
 */
public class ListAdapter extends BaseAdapter {

    public Activity activity;
    ArrayList<String> data = new ArrayList<String>();
    private static LayoutInflater inflater = null;

    public ListAdapter(Activity a, ArrayList<String> d) {
        activity = a;
        data = d;
        inflater = LayoutInflater.from(activity);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.my_list_item, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Touch on view handle
                Log.d("", "Touched row "+position);
                Intent nextScreen = new Intent(activity.getApplicationContext(), ExerciseStartActivity.class);
                activity.startActivity(nextScreen);
            }

        });

        //customizing view
        holder.textView = (TextView)convertView.findViewById(R.id.my_textview);
        holder.textView.setText(data.get(position));

        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}
