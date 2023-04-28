package org.spring.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.spring.springboot.entity.vo.FactorVo;
import org.spring.springboot.mapper.FactorMapper;
import org.spring.springboot.service.FactorService;
import org.springframework.stereotype.Service;

@Service
public class FactorServiceImpl extends ServiceImpl <FactorMapper, FactorVo> implements FactorService {
}
