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
import com.techathon.healthtec.model.Exercise;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by NichChau on 4/7/15.
 */
public class ListAdapter extends BaseAdapter {

    public Activity activity;
    List<Exercise> exercises;
    private static LayoutInflater inflater = null;

    public ListAdapter(Activity a, List<Exercise> exercises) {
        activity = a;
        this.exercises = exercises;
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
        final Exercise currentExercise = exercises.get(position);
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Touch on view handle
                Log.d("", "Touched row "+position);
                Intent nextScreen = new Intent(activity.getApplicationContext(), ExerciseStartActivity.class);
                nextScreen.putExtra(ExerciseStartActivity.EXTRA_CURRENT_EXERCISE, currentExercise);
                activity.startActivity(nextScreen);
            }

        });

        //customizing view
        holder.textView = (TextView)convertView.findViewById(R.id.my_textview);
        String text = String.format("%s %s", new SimpleDateFormat("MMM dd").format(
                currentExercise.getDate()), "Running");
        holder.textView.setText(text);

        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }
    @Override
    public int getCount() {
        return exercises.size();
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
