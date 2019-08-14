package com.lanke.foodie.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResults implements Serializable {

    private long total;;

    private String message="成功"; //返回信息
    private int code=0; //默认0为成功
    private List rows;
}