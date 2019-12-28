package com.example.project1;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class JsonModel {
    @SerializedName("Tel")
    //private List<Tel> getItems;
    private List<Tel> items;
    public List<Tel> getItems() {
        return items;
    }
}
