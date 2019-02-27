package com.lanke.foodie.enums;

public enum PayStatus {

    NotPay("NOTPAY", 0),HadPayShop("HADPAYSHOP", 1),HadPayFoodie("HADPAYFOODIE", 2);


    private String name ;
    private int index ;

    private PayStatus( String name , int index ){
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
