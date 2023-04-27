package org.spring.springboot.domain;
//    定义了一个类的数据结构
//    相当于entity

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class City {
    private Long id;
    private Long provinceId;
    private String cityName;
    private String description;
}

