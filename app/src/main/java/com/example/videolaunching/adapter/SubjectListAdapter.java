package com.example.videolaunching.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.videolaunching.R;
import com.example.videolaunching.model.Subject;

import java.util.ArrayList;

public class SubjectListAdapter extends BaseAdapter  {

    private Context context;
    private ArrayList<String> subjectList;

    public SubjectListAdapter(Context context, ArrayList<String> subjectNames){
        this.context = context;
        this.subjectList = subjectNames;
    }

    @Override
    public int getCount() {
        return subjectList.size();
    }

    @Override
    public String getItem(int position) {
        return subjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.subjectlist_item, parent, false);
        TextView subject = (TextView)convertView.findViewById(R.id.txtViewSubjectName);
        subject.setText(subjectList.get(position));
        String subject1 = subjectList.get(position);
        return convertView;
    }
}
