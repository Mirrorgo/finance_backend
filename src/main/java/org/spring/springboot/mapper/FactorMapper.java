package org.spring.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.spring.springboot.entity.vo.FactorVo;

//@Mapper // 标志为 Mybatis 的 Mapper
@Mapper
public interface FactorMapper extends BaseMapper<FactorVo> {
}