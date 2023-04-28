package org.spring.springboot.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.entity.po.Algorithm;
import org.spring.springboot.entity.po.AlgorithmFactor1;
import org.spring.springboot.entity.vo.AlgorithmVo;
import org.spring.springboot.service.AlgorithmFactor1Service;
import org.spring.springboot.service.AlgorithmFactor2Service;
import org.spring.springboot.service.AlgorithmService;
import org.spring.springboot.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @GetMapping("list")
    public Result<List<Algorithm>> getAll(@Param("searchType") String searchType, @Param("searchContent") String searchContent) {
        QueryWrapper<Algorithm> queryWrapper = new QueryWrapper<>();
        if (Objects.equals(searchType, "name")) {
            //根据name模糊搜索
            queryWrapper.like("name", searchContent);
        } else if (Objects.equals(searchType, "id")){
            //根据id精确搜索
            queryWrapper.eq("id",searchContent);
        }
        // searchType为空则返回所有内容
        List<Algorithm> list = algorithmService.list(queryWrapper);
        return Result.success(list);
//        for(AlgorithmVO al :list){
//            System.out.println(list);
//        }
    }
    @GetMapping("get")
    public Result<Object> getById(@Param("id") String id) {
        Algorithm algorithmDo = algorithmService.getById(id);
        AlgorithmVo algorithmVo  = new AlgorithmVo();
        if(Objects.equals(algorithmDo.getType(), "1")){
            // 如果是1条件选股,则在factor1表中继续搜索
            QueryWrapper<AlgorithmFactor1> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("algorithm_id",algorithmDo.getId());
            List<AlgorithmFactor1> algorithmFactor1List = algorithmFactors1Service.list(queryWrapper);
            //
            BeanUtil.copyProperties(algorithmDo,algorithmVo);
            algorithmVo.setFactors(algorithmFactor1List);
//            System.out.println(algorithmVo+"VO");
            return Result.success(algorithmVo);
        }
        return Result.success(algorithmDo);
    }

//    @PostMapping("save")
//    public boolean save(@RequestBody AlgorithmDo algorithmVO) {
////        TODO:先从VO转成实际要存的几种对象，然后保存
//        // 每个表对象对应一个mapper,一个service,一个 ServiceImpl
//        return algorithmService.save(algorithmVO);
//    }
}
