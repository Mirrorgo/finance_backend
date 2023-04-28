package org.spring.springboot.controller;

import org.spring.springboot.entity.po.AlgorithmDo;
import org.spring.springboot.service.AlgorithmFactors1Service;
import org.spring.springboot.service.AlgorithmService;
import org.spring.springboot.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lwz/algorithm")
//@CrossOrigin(origins = "http://localhost:9528")
@CrossOrigin(origins = "*")
public class AlgorithmRestController {
    @Autowired
    private AlgorithmService algorithmService;
    @Autowired
    private AlgorithmFactors1Service algorithmFactors1Service;

    @GetMapping("list")
    public Result<List<AlgorithmDo>> getAll() {
        List<AlgorithmDo> list = algorithmService.list(null);
//        for(AlgorithmVO al :list){
//            System.out.println(list);
//        }
        return Result.success(list);
    }

    //TODO：这个暂时没用
    @GetMapping("find/{id}")
    public Result<AlgorithmDo> getById(@PathVariable String id) {
//        QueryWrapper<algorithmVO> queryWrapper =new QueryWrapper<>();
//        queryWrapper.eq("id" ,id);
//        queryWrapper.eq("name",name);
//        queryWrapper.gt("create_at",time);
        AlgorithmDo byId = algorithmService.getById(id);
        return Result.success(byId);
    }

    @PostMapping("save")
    public boolean save(@RequestBody AlgorithmDo algorithmVO) {
//        TODO:先从VO转成实际要存的几种对象，然后保存
        // 每个表对象对应一个mapper,一个service,一个 ServiceImpl
        return algorithmService.save(algorithmVO);
    }
}
