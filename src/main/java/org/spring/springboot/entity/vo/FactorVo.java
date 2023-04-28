package org.spring.springboot.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("factor_group")
public class FactorVo {
    @TableField("factorId")
    private String id;
    @TableField("factorName")
    private String name;
//    private String description; // hz那里来的时候是没有这个东西的,但后续条件选股有这个
}

