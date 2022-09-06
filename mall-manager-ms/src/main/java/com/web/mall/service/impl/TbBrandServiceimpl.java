package com.web.mall.service.impl;

import com.web.mall.mapper.TbBrandMapper;
import com.web.mall.pojo.TbBrand;
import com.web.mall.service.TbBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TbBrandServiceimpl implements TbBrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;
    @Override
    public List<TbBrand> queryBrand() {
        return tbBrandMapper.selectByExample(null);
    }

    @Override
    public TbBrand findBrandById(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteBrand(Long id) {
        tbBrandMapper.deleteByPrimaryKey(id);
    }
}
