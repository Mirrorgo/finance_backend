package org.spring.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.spring.springboot.entity.Algorithm;
import org.spring.springboot.entity.vo.algorithmVO;


//@Mapper
@Mapper
public interface AlgorithmMapper extends BaseMapper<algorithmVO> {
//    // TODO:完善搜索算法+连续select操作
//    @Select("SELECT * FROM algorithm")
//    @Results({
//            // TODO:这个property需要去entity手动找对应的名字吗，代码提示都没有
//            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "type", column = "type"),
////            @Result(property = "algorithmDescription", column = "description"),
//    })
//    Algorithm[] searchAlgorithms(String searchType, String searchContent); //
//
//    @Select("SELECT * FROM algorithm where id = #{id}")
//    @Results({
//            // TODO:这个property需要去entity手动找对应的名字吗，代码提示都没有
//            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "type", column = "type"),
//            @Result(property = "algorithmDescription", column = "description"),
//    })
//    Algorithm getAlgorithmById(String id); //
//
//    @Insert("insert into algorithm(name,description,type,createAt) values(#{name},#{description},#{type},#{createAt}) ")
////    @Insert("INSERT INTO algorithm(name,description,`createdAt`,`type`) VALUES('测试java插入','111','2023-04-27 17:35:08',1)")
////    INSERT INTO algorithm(name,description,`createdAt`,`type`) VALUES('测试java插入','111','2023-04-27 17:35:08',1);
//    Algorithm createNewAlgorithm(
//            @Param("name") String name,
//            @Param("description") String description,
//            @Param("type") String type,
//            @Param("createAt") String date
//    ); //
}

