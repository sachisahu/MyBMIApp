package com.sachi.mybmiapp.BMIFragements;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sachi.mybmiapp.DatabaseBMI;
import com.sachi.mybmiapp.R;
public class BMIFragment extends Fragment {


    EditText txtHeight,txtWeight;
    Spinner spnGender;
    Button btnCalc;
    TextView bmiShow;
    BMIFragment activity;
    DatabaseBMI db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseBMI(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_b_m_i, container, false);

        activity = this;
        txtHeight = view.findViewById(R.id.txtHeight);
        txtWeight = view.findViewById(R.id.txtWeight);
        spnGender = view.findViewById(R.id.Spinner_Gender);
        btnCalc = view.findViewById(R.id.btnSubmit);
        bmiShow = view.findViewById(R.id.BmiCalc_TextView);

        //Static Data
        final String[] gender = new String[1];



        //

        spnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender[0] = spnGender.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float height,weight,bmiCalc;
                if(txtHeight.getText().toString().length()>0 && txtWeight.getText().toString().length()>0){
                    height = Float.parseFloat(txtHeight.getText().toString());
                    weight = Float.parseFloat(txtWeight.getText().toString());
                    bmiCalc = (float) (weight/(Math.pow(height,2)));

                    bmiShow.setText("Your BMI : "+bmiCalc);
                    boolean flag = db.insertData(""+height,""+weight,""+gender[0],""+bmiCalc);
                    //Log.d("Insert Status: ",flag+"");
                }
                else {
                    Toast.makeText(getContext(), "Enter Valid Data", Toast.LENGTH_SHORT).show();
                }

                db.fetchData();

                //Toast.makeText(getContext(), "Gender = "+gender[0]+"Height = "+ height[0] +"Weight = "+ weight[0], Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }


}