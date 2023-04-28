package org.spring.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.spring.springboot.entity.po.AlgorithmDo;


//@Mapper
@Mapper
public interface AlgorithmMapper extends BaseMapper<AlgorithmDo> {
}

