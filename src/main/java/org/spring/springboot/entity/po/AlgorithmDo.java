package org.spring.springboot.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * algorithm
 */
@TableName("algorithm")
@Data
public class AlgorithmDo {
    /**
     * 算法描述
     */
    private String description;
    /**
     * 仅综合选股
     */
    private String createAt;
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
