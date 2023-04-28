package org.spring.springboot.entity.vo;

import lombok.Data;

@Data
public class AlgorithmVo {
    /**
     * 算法描述
     */
    private String algorithmDescription;
    /**
     * 仅综合选股
     */
    private String factorDescription;
    private Factor[] factors;
    /**
     * 算法名称
     */
    private String name;
    /**
     * 条件选股/综合选股
     */
    private String type;
}


 class Factor {
    /**
     * 仅条件选股
     */
    private String description;
    private String id;
    private String name;
}