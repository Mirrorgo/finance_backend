package org.spring.springboot.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * algorithm
 */
@TableName("super_algorithm")
@Data
public class Algorithm {
    /**
     * 算法id
     */
    private String id;
    /**
     * 算法名称
     */
    private String name;
    /**
     * 算法描述：对于条件选股由业务人员直接编写，对于综合选股由技术人员编写
     */
    private String algorithmDescription;
    /**
     * 1条件选股/2综合选股
     * 条件选股： 多因子，每个因子一个选股逻辑描述，在选股因子列表中可以看到对应字段
     * 综合选股： 多因子，但整个算法只有一个选股逻辑描述，写在👇logic_description中
     *
     */
    private String type;
    /**
     * 选股逻辑描述,仅2综合选股有值
     */
   private String logicDescription;
    /**
     * 选股算法文件对应的路径
     */
   private String algorithmFilePath;
}
