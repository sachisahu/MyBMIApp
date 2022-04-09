package com.sachi.mybmiapp.BMIFragements;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.sachi.mybmiapp.CustomeListAdapters.BMIHistoryCustomListAdapter;
import com.sachi.mybmiapp.DataClass.BMIHistoryDataClass;
import com.sachi.mybmiapp.DatabaseBMI;
import com.sachi.mybmiapp.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    Activity activity;

    BMIHistoryCustomListAdapter bmiHistoryCustomListAdapter;

    ArrayList<BMIHistoryDataClass> bmiHistoryDataClasses = new ArrayList<>();

    DatabaseBMI db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseBMI(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ListView listView = view.findViewById(R.id.bmi_History_ListView);
        Button button = view.findViewById(R.id.btnScreenSort);

        button.setOnClickListener(i->{
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,50,outputStream);
            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),bitmap,"Titlee","");
        });

        db.fetchData();


        bmiHistoryCustomListAdapter = new BMIHistoryCustomListAdapter(getActivity(),db.bmiHistoryDataClasses);

        listView.setAdapter(bmiHistoryCustomListAdapter);



        return view;
    }
}