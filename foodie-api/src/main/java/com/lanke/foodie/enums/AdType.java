package com.lanke.foodie.enums;

public enum AdType {
    Shop("店家",1), Product("商品",2);


    private String name ;
    private int index ;

    private AdType( String name , int index){
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
