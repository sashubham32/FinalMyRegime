package com.example.sashu.myregime.data;

public class ClothClass {

    private String name;
    private Boolean isReturned;

    public ClothClass(){

    }

    public ClothClass(String name, Boolean isReturned) {
        this.name = name;
        this.isReturned = isReturned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getReturned() {
        return isReturned;
    }

    public void setReturned(Boolean returned) {
        isReturned = returned;
    }

}
