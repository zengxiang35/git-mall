package com.web.mall.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.mall.pojo.PageReq;
import com.web.mall.pojo.PageResp;
import com.web.mall.pojo.RespBean;
import com.web.mall.pojo.TbBrand;
import com.web.mall.service.TbBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TbBrandController {
    @Autowired
    private TbBrandService tbBrandService;

    @GetMapping("/queryBrand")
    public List<TbBrand> queryBrand(){
        return tbBrandService.queryBrand();
    }

    @GetMapping("/findBrandById/{id}")
    public TbBrand findBrandById(@PathVariable Long id){
        return tbBrandService.findBrandById(id);
    }


    @GetMapping("/listByPage")
    public PageResp<TbBrand> listByPage(PageReq pageReq){
        PageHelper.startPage(pageReq.getPage(),pageReq.getSize());
        Page<TbBrand> page = (Page<TbBrand>) tbBrandService.queryBrand();

        PageResp pageResp = new PageResp();
        pageResp.setList(page.getResult());
        PageInfo pageInfo = new PageInfo(page);
        pageResp.setTotal(pageInfo.getTotal());
        return pageResp;
    }

    @GetMapping("/deleteBrand/{id}")
    public RespBean deleteBrand(@PathVariable Long id){
        tbBrandService.deleteBrand(id);
        return  RespBean.ok("删除成功");
    }

    @PostMapping("/saveBrand")
    public RespBean saveBrand(@RequestBody TbBrand tbBrand){
        tbBrandService.saveBrand(tbBrand);
        return RespBean.ok("添加成功");
    }

    @PostMapping("/updateBrand")
    public void updateBrand(@RequestBody TbBrand tbBrand){
        tbBrandService.updateBrand(tbBrand);
    }
}
