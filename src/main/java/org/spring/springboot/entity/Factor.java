package org.spring.springboot.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Factor {
    private String id;
    private String name;
    private String description; // hz那里来的时候是没有这个东西的,但后续条件选股有这个
}

