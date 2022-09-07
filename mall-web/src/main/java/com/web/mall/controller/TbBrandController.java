/*package com.web.mall.controller;

import com.web.mall.pojo.TbBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TbBrandController {
    @Autowired
    private RestTemplate restTemplate;

    public static final String QUERY_URL="http://localhost:8899/mall-query/";

    @GetMapping("/queryBrand")
    public ModelAndView queryBrand(){
        List<TbBrand> brandList = restTemplate.getForObject(QUERY_URL+"queryBrand", List.class);

        ModelAndView mv = new ModelAndView();
        mv.addObject("brandList",brandList);
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/findBrandById")
    public ModelAndView findBrandById(@PathVariable Long id){
        TbBrand tbBrand = restTemplate.getForObject(QUERY_URL+"findBrandById",TbBrand.class);

        ModelAndView mv = new ModelAndView();
        mv.addObject("tbBrand",tbBrand);
        mv.addObject("editMall");
        return mv;
    }

    @PostMapping("/updateBrand")
    public String updateNews(TbBrand tbBrand){
        System.out.println(tbBrand.toString());
        restTemplate.postForObject(QUERY_URL+"updateNews",tbBrand,String.class);
        return "redirect:/queryBrand";
    }
}*/
