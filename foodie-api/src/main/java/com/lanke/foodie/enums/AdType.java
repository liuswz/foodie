package com.lanke.foodie.enums;

public enum AdType {
    Shop("店家"), Product("商品");


    private String name ;


    private AdType( String name ){
        this.name = name ;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
