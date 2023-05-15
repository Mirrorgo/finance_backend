package org.spring.finance.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.ibatis.annotations.Param;
import org.spring.finance.entity.po.Algorithm;
import org.spring.finance.entity.po.AlgorithmFactor1;
import org.spring.finance.entity.po.AlgorithmFactor2;
import org.spring.finance.entity.po.Factor;
import org.spring.finance.entity.vo.AlgorithmVo;
import org.spring.finance.entity.vo.BasicAlgorithmVo;
import org.spring.finance.service.AlgorithmFactor1Service;
import org.spring.finance.service.AlgorithmFactor2Service;
import org.spring.finance.service.AlgorithmService;
import org.spring.finance.service.FactorService;
import org.spring.finance.utils.result.Result;
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
    public Result<List<BasicAlgorithmVo>> getAll(@Param("searchType") String searchType, @Param("searchContent") String searchContent
            , @Param("algorithmStatus") String algorithmStatus) {
        QueryWrapper<Algorithm> queryWrapper = new QueryWrapper<>();
        if (Objects.equals(searchType, "name")) {
            //根据name模糊搜索
            queryWrapper.like("name", searchContent);
        } else if (Objects.equals(searchType, "id")) {
            //根据id精确搜索
            queryWrapper.eq("id", searchContent);
        }
//       处理algorithmStatus字段
        if (!Objects.equals(algorithmStatus, "")) {
            queryWrapper.eq("status", algorithmStatus);
        }
        List<Algorithm> list = algorithmService.list(queryWrapper);
//        hutools简化代码
        return Result.success(BeanUtil.copyToList(list, BasicAlgorithmVo.class));
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
        algorithm.setCreatedAt(DateTime.now().toString()); // 插入创建时的时间
        algorithm.setAuthor("高仁");
        if (Objects.equals(algorithmVO.getType(), "1")) {
            algorithm.setStatus("done"); // 条件选股由业务人员直接配置好
        } else {
            algorithm.setStatus("todo"); // 综合选股还需要由技术人员进行后续开发
        }
        algorithmService.save(algorithm);
        // 更新AlgorithmFilePath字段
        algorithm.setAlgorithmFilePath(algorithm.getId());
        algorithmService.updateById(algorithm);
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

    @PostMapping("update")
    public Result<String> update(@RequestBody AlgorithmVo algorithmVO) {
//        目前仅2综合选股需要支持update接口, 且仅能修改算法描述字段
        Algorithm algorithm = new Algorithm();
        BeanUtil.copyProperties(algorithmVO, algorithm);
        System.out.println(algorithmVO);
        System.out.println(algorithm);
        algorithmService.updateById(algorithm);
        return Result.success("success");
    }
}
