package com.web.mall.service;

import com.web.mall.pojo.TbSpecification;
import com.web.mall.pojo.TbSpecificationOption;
import com.web.mall.pojoGroup.Specification;

import java.util.List;

public interface SpecificationService {
    public List<TbSpecification> querySpecification();

    public List<TbSpecificationOption> querySpecificationOption(Long id);

    public TbSpecification findById(Long id);

    public void add(Specification specification);

    public void update(Specification specification);

    public void delete(Long id);
}
