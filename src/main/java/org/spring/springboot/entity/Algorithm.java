package org.spring.springboot.entity;

import lombok.Data;

/**
 * algorithm
 */
@Data
public class Algorithm {
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
     * 算法id
     */
    private String id;
    /**
     * 算法名称
     */
    private String name;
    /**
     * 条件选股/综合选股
     */
    private String type;
}


