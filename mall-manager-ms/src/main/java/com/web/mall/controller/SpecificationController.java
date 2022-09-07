package com.web.mall.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.mall.pojo.*;
import com.web.mall.pojoGroup.Specification;
import com.web.mall.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;

    @GetMapping("/querySpecification")
    public List<TbSpecification> querySpecification() {
        return specificationService.querySpecification();
    }

    @GetMapping("/specificationByPage")
    public PageResp<TbSpecification> listByPage(PageReq pageReq) {
        PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        Page<TbSpecification> page = (Page<TbSpecification>) specificationService.querySpecification();

        PageResp pageResp = new PageResp();
        pageResp.setList(page.getResult());
        PageInfo pageInfo = new PageInfo(page);
        pageResp.setTotal(pageInfo.getTotal());
        return pageResp;
    }

    @GetMapping("/querySpecificationOption/{id}")
    public List<TbSpecificationOption> querySpecificationOption(@PathVariable Long id){
        return specificationService.querySpecificationOption(id);
    }

    @GetMapping("findById/{id}")
    public TbSpecification findById(@PathVariable Long id){
        return specificationService.findById(id);
    }

    @GetMapping("/delete/{id}")
    public RespBean delete(@PathVariable Long id){
        specificationService.delete(id);
        return  RespBean.ok("删除成功");
    }

    @PostMapping("/add")
    public RespBean add(@RequestBody Specification specification){
        specificationService.add(specification);
        return RespBean.ok("200");
    }

    @PostMapping("/update")
    public void update(@RequestBody Specification specification){
        specificationService.update(specification);
    }
}
