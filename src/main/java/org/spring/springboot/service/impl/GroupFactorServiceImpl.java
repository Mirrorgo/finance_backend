package org.spring.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.spring.springboot.entity.po.GroupFactor;
import org.spring.springboot.mapper.GroupFactorMapper;
import org.spring.springboot.service.GroupFactorService;
import org.springframework.stereotype.Service;

@Service
public class GroupFactorServiceImpl extends ServiceImpl <GroupFactorMapper, GroupFactor> implements GroupFactorService {
}
