package org.spring.springboot.dao;

import org.apache.ibatis.annotations.*;
import org.spring.springboot.domain.Factor;

@Mapper
public interface FactorDao {
    @Select("select * from factor_group")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "cityName", column = "city_name"),
//            @Result(property = "description", column = "description"),
//    })
    Factor findByGroupId(@Param("groupId") String groupId);
}
