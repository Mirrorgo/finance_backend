package org.spring.springboot.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.entity.po.Algorithm;
import org.spring.springboot.entity.po.AlgorithmFactor1;
import org.spring.springboot.entity.po.AlgorithmFactor2;
import org.spring.springboot.entity.po.Factor;
import org.spring.springboot.entity.vo.AlgorithmVo;
import org.spring.springboot.service.AlgorithmFactor1Service;
import org.spring.springboot.service.AlgorithmFactor2Service;
import org.spring.springboot.service.AlgorithmService;
import org.spring.springboot.service.FactorService;
import org.spring.springboot.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lwz/algorithm")
//@CrossOrigin(origins = "http://localhost:9528")
@CrossOrigin(origins = "*")
public class AlgorithmRestController {
    @Autowired
    private AlgorithmService algorithmService;
    @Autowired
    private AlgorithmFactor1Service algorithmFactors1Service;

    @Autowired
    private AlgorithmFactor2Service algorithmFactor2Service;
    @Autowired
    private FactorService factorService;

    @GetMapping("list")
    public Result<List<Algorithm>> getAll(@Param("searchType") String searchType, @Param("searchContent") String searchContent
            , @Param("algorithmStatus") String algorithmStatus) {
        QueryWrapper<Algorithm> queryWrapper = new QueryWrapper<>();
        if (Objects.equals(searchType, "name")) {
            //根据name模糊搜索
            queryWrapper.like("name", searchContent);
        } else if (Objects.equals(searchType, "id")) {
            //根据id精确搜索
            queryWrapper.eq("id", searchContent);
        }
        if (!Objects.equals(algorithmStatus, "")) {
            queryWrapper.eq("status",algorithmStatus);
        }
        // searchType为空则返回所有内容
        List<Algorithm> list = algorithmService.list(queryWrapper);
        return Result.success(list);
    }

    @GetMapping("get")
    public Result<Object> getById(@Param("id") String id) {
        Algorithm algorithm = algorithmService.getById(id); //*
        AlgorithmVo algorithmVo = new AlgorithmVo();
        if (Objects.equals(algorithm.getType(), "1")) {
            // 如果是1条件选股,则在factor1表中继续搜索
            QueryWrapper<AlgorithmFactor1> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("algorithm_id", algorithm.getId());
            List<AlgorithmFactor1> algorithmFactor1List = algorithmFactors1Service.list(queryWrapper);
            BeanUtil.copyProperties(algorithm, algorithmVo); // 复制相同属性
            // 逐个复制特有的属性
            algorithmVo.setFactors(
                    algorithmFactor1List.stream().map(
                            cur -> {
                                AlgorithmVo.Factor factor = new AlgorithmVo.Factor();
                                factor.setDescription(cur.getLogicDescription());
                                factor.setId(cur.getFactorId());
                                factor.setName(factorService.getById(cur.getFactorId()).getName());
                                return factor;
                            }
                    ).collect(Collectors.toList())
            );
        } else {
            // 如果是2综合选股,则在factor2表中继续搜索
            QueryWrapper<AlgorithmFactor2> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("algorithm_id", algorithm.getId());
            List<AlgorithmFactor2> algorithmFactor2List = algorithmFactor2Service.list((queryWrapper));
            List<String> ids = algorithmFactor2List.stream().map(AlgorithmFactor2::getFactorId).collect(Collectors.toList());
            List<Factor> factors = new ArrayList<>(factorService.listByIds(ids));
            BeanUtil.copyProperties(algorithm, algorithmVo);
            // 利用第三方工具库hutool简化代码
            algorithmVo.setFactors(BeanUtil.copyToList(factors, AlgorithmVo.Factor.class));
        }
        return Result.success(algorithmVo);
    }

    @PostMapping("save")
    public Result<String> save(@RequestBody AlgorithmVo algorithmVO) {
        Algorithm algorithm = new Algorithm();
        BeanUtil.copyProperties(algorithmVO, algorithm);
        algorithmService.save(algorithm);
        List<AlgorithmVo.Factor> list = algorithmVO.getFactors();
        if (Objects.equals(algorithmVO.getType(), "1")) {
            for (AlgorithmVo.Factor factor : list) {
                AlgorithmFactor1 savedFactor = new AlgorithmFactor1();
                savedFactor.setAlgorithmId(algorithm.getId());
                savedFactor.setFactorId(factor.getId());
                savedFactor.setLogicDescription(factor.getDescription());
                algorithmFactors1Service.save(savedFactor);
            }
        } else {
            for (AlgorithmVo.Factor factor : list) {
                AlgorithmFactor2 savedFactor = new AlgorithmFactor2();
                savedFactor.setAlgorithmId(algorithm.getId());
                savedFactor.setFactorId(factor.getId());
                algorithmFactor2Service.save((savedFactor));
            }
        }
        return Result.success(algorithm.getId());
    }
}
