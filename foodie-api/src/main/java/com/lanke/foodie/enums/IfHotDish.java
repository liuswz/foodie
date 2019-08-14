package com.lanke.foodie.enums;

public enum IfHotDish {

    NotHotDish("NOTOTDISH", 0),IsHotDish("ISHOTDISH", 1);


    private String name ;
    private int index ;

    private IfHotDish( String name , int index ){
        this.name = name ;
        this.index = index ;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
