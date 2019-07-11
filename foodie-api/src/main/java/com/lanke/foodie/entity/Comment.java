package com.lanke.foodie.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Comment {
    private Integer id;
    private String content;
    private String photoUrl;
    private Integer mark;
    private Integer likeNum;

    private String createTime;

}
