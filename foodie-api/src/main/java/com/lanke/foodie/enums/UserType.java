package com.lanke.foodie.enums;

public enum UserType {
    CommonUser("普通用户",0), Shopper("店家",1);


    private String name ;
    private int index ;

    private UserType(String name , int index){
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
