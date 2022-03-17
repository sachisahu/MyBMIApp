package com.sachi.mybmiapp.CustomeListAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sachi.mybmiapp.DataClass.BMIHistoryDataClass;
import com.sachi.mybmiapp.R;

import java.util.ArrayList;

public class BMIHistoryCustomListAdapter extends ArrayAdapter<BMIHistoryDataClass> {

    Activity context;
    ArrayList<BMIHistoryDataClass> bmiHistoryDataClasses;
    public BMIHistoryCustomListAdapter(@NonNull Activity context, ArrayList<BMIHistoryDataClass> bmiHistoryDataClasses) {
        super(context, R.layout.bmi_history_custome_list,bmiHistoryDataClasses);

        this.context = context;
        this.bmiHistoryDataClasses = bmiHistoryDataClasses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.bmi_history_custome_list,null,true);

        BMIHistoryDataClass bmiHistoryDataClass = (BMIHistoryDataClass) getItem(position);

        TextView id = view.findViewById(R.id.id_text);
        TextView height = view.findViewById(R.id.height_text);
        TextView weight = view.findViewById(R.id.weight_text);
        TextView gender = view.findViewById(R.id.gender_text);
        TextView bmi = view.findViewById(R.id.bmi_text);

        id.setText(bmiHistoryDataClass.id);
        height.setText(bmiHistoryDataClass.height);
        weight.setText(bmiHistoryDataClass.weight);
        gender.setText(bmiHistoryDataClass.gender);
        bmi.setText(bmiHistoryDataClass.bmi);

        return view;
    }
}
