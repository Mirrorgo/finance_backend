package org.spring.springboot.domain;

//TODO: 是不是可以用@Data
//    定义了一个类的数据结构
//    相当于entity

import lombok.Data;

@Data
public class City {

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;
}

