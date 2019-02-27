package com.lanke.foodie.enums;

public enum Authority {

    Admin("ADMIN"), Common("COMMON");


    private String name ;


    private Authority( String name ){
        this.name = name ;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
