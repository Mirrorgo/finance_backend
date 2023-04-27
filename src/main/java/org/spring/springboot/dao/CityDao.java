package org.spring.springboot.dao;

import org.apache.ibatis.annotations.*;
import org.spring.springboot.domain.City;

@Mapper // 标志为 Mybatis 的 Mapper
public interface CityDao {

//    springboot 和 数据库部分对接的功能，虽然是个接口但是注解帮他实现了这些
    @Select("SELECT * FROM city where city_name = #{随便填一个可以}")
    // 返回 Map 结果集
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "description", column = "description"),
    })
//    City findByName(@Param("testId") String cityName);
    City findByName( String cityName);
}
