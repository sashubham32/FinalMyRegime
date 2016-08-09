package com.example.sashu.myregime.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClothBundleClass {

    private List<ClothClass> clothes;
    private Date date;

    public ClothBundleClass(){

        clothes = new ArrayList<>();

    }

    public ClothBundleClass(List<ClothClass> clothes, Date date) {
        this.clothes = clothes;
        this.date = date;
    }

    public int getSize(){
        return clothes.size();
    }

    public List<ClothClass> getClothes() {
        return clothes;
    }

    public void setClothes(List<ClothClass> clothes) {
        this.clothes = clothes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
