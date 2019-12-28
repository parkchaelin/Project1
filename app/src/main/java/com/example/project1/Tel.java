package com.example.project1;

import com.google.gson.annotations.SerializedName;

public class Tel {
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }




    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String tel) {
        this.phone = phone;
    }

}
