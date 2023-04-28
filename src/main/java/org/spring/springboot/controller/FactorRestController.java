package org.spring.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.spring.springboot.entity.vo.FactorVo;
import org.spring.springboot.service.FactorService;
import org.spring.springboot.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//
//import org.spring.springboot.entity.vo.Factor;
//import org.spring.springboot.service.FactorService;
//import org.spring.springboot.utils.result.Result;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
////@RestController 这个注解等于 @Controller + @ResponseBody 访问结果以 JSON 格式返回
//@RequestMapping("/lwz/factors")
//public class FactorRestController {
//    @Autowired
//    private FactorService factorService;
//    @GetMapping("group")
//    @CrossOrigin(origins = "http://localhost:9528")
//    public Result<Factor[]> findAll(@RequestParam(value = "groupId", required = true) String groupId) {
//        return Result.success(factorService.getFactorsByGroupId(groupId)) ;
//    }
//
//}
@RestController
@RequestMapping("/lwz/factors")
//@CrossOrigin(origins = "http://localhost:9528")
@CrossOrigin(origins = "*")
public class FactorRestController {
    @Autowired
    private FactorService factorService;

    @GetMapping("group")
    public Result<List<FactorVo> > getFactorsByGroupId(@RequestParam String groupId){
        QueryWrapper<FactorVo> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("groupId",groupId);
        List<FactorVo> factors = factorService.list(queryWrapper);
        return Result.success((factors));
    }

}