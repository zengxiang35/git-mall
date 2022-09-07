package com.web.mall.service;

import com.web.mall.pojo.TbBrand;

import java.util.List;

public interface TbBrandService {

    public List<TbBrand> queryBrand();

    public TbBrand findBrandById(Long id);

    public void deleteBrand(Long id);

    public void saveBrand(TbBrand tbBrand);

    public void updateBrand(TbBrand tbBrand);
}
