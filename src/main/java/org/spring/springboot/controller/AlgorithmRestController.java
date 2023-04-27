package org.spring.springboot.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.spring.springboot.entity.Algorithm;
import org.spring.springboot.entity.vo.algorithmVO;
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
//    @GetMapping("list")
//    public Result<Algorithm[]> search(String searchType, String searchContent) {
//        return Result.success(algorithmService.searchAlgorithms(searchType, searchContent));
//    }
//
//    @GetMapping("get")
//    public Result<Algorithm> getById(String id) {
//        return Result.success(algorithmService.getAlgorithmById(id));
//    }
//    @PostMapping("add")
//    public Result<String> createNewAlgorithm(@RequestBody Algorithm algorithm) {
//        System.out.println(algorithm.toString()+"wow");
//        algorithmService.createNewAlgorithm(algorithm);
//        return Result.success("完成");
//    }
    @GetMapping("getAll")
    public void getAll(){
        QueryWrapper<algorithmVO> queryWrapper;
        queryWrapper = null;
        List<algorithmVO> list = algorithmService.list(queryWrapper);
        for(algorithmVO al :list){
            System.out.println(list);
        }

    }
    @GetMapping("find/{id}")
    public algorithmVO getById(@PathVariable String id){
//        QueryWrapper<algorithmVO> queryWrapper =new QueryWrapper<>();
//        queryWrapper.eq("id" ,id);
//        queryWrapper.eq("name",name);
//        queryWrapper.gt("create_at",time);
        algorithmVO byId = algorithmService.getById(id);
        return byId;
    }
    @PostMapping("insert")
    public boolean insert(@RequestBody algorithmVO algorithmVO){
        boolean save = algorithmService.save(algorithmVO);
        return save;
    }
}
