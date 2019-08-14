package com.lanke.foodie.simpleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain=true)
@AllArgsConstructor
public class SimpleUser implements Serializable {

    private Integer id;
    private String phoneNum;
    private String username;
    private String nickname;
    private String address;
    private String photoUrl;

}
