package org.spring.springboot.mapper;

import org.apache.ibatis.annotations.*;
import org.spring.springboot.entity.Factor;

//@Mapper // 标志为 Mybatis 的 Mapper
public interface FactorDao {
//    @Select("SELECT * FROM factor_group where groupId = #{随便填一个可以}")
//    @Results({
//            // TODO:这个property需要去entity手动找对应的名字吗，代码提示都没有
//            @Result(property = "id", column = "factorId"),
//            @Result(property = "name", column = "factorName"),
//    })
//        //City findByName(@Param("testId") String cityName);
//        // 👇参数叫啥都没关系
//    Factor[] getFactorsByGroupId(String groupId);
}
