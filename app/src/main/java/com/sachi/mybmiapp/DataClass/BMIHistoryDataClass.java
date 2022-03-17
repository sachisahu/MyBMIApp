package com.sachi.mybmiapp.DataClass;

public class BMIHistoryDataClass {
    public String id;
    public String height;
    public String weight;
    public String gender;
    public String bmi;

    public BMIHistoryDataClass(String id, String height, String weight, String gender, String bmi) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.bmi = bmi;
    }
}
