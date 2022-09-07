package com.web.mall.service.impl;

import com.web.mall.mapper.TbSpecificationMapper;
import com.web.mall.mapper.TbSpecificationOptionMapper;
import com.web.mall.pojo.TbSpecification;
import com.web.mall.pojo.TbSpecificationOption;
import com.web.mall.pojo.TbSpecificationOptionExample;
import com.web.mall.pojoGroup.Specification;
import com.web.mall.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper tbSpecificationMapper;
    @Autowired
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;

    @Override
    public List<TbSpecification> querySpecification() {
        return tbSpecificationMapper.selectByExample(null);
    }

    @Override
    public List<TbSpecificationOption> querySpecificationOption(Long id) {
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> tbSpecificationOptions = tbSpecificationOptionMapper.selectByExample(example);
        return tbSpecificationOptions;
    }

    @Override
    public TbSpecification findById(Long id) {
        return tbSpecificationMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Specification specification) {
        if(ObjectUtils.isEmpty(specification.getSpecification().getId())) {
            tbSpecificationMapper.insert(specification.getSpecification());
            for (TbSpecificationOption tbSpecificationOption : specification.getSpecificationOptionList()) {
                tbSpecificationOption.setSpecId(specification.getSpecification().getId());
                tbSpecificationOptionMapper.insert(tbSpecificationOption);
            }
        }else{
            tbSpecificationMapper.updateByPrimaryKey(specification.getSpecification());

            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(specification.getSpecification().getId());

            tbSpecificationOptionMapper.deleteByExample(example);

            //保存规格选项
            for(TbSpecificationOption tbSpecificationOption:specification.getSpecificationOptionList()) {
                //设置规格id
                tbSpecificationOption.setSpecId(specification.getSpecification().getId());

                tbSpecificationOptionMapper.insert(tbSpecificationOption);
            }
        }
    }

    @Override
    public void update(Specification specification) {
        tbSpecificationMapper.updateByPrimaryKey(specification.getSpecification());

        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());

        tbSpecificationOptionMapper.deleteByExample(example);

        //保存规格选项
        for(TbSpecificationOption tbSpecificationOption:specification.getSpecificationOptionList()) {
            //设置规格id
            tbSpecificationOption.setSpecId(specification.getSpecification().getId());

            tbSpecificationOptionMapper.insert(tbSpecificationOption);
        }
    }


    @Override
    public void delete(Long id) {
        tbSpecificationMapper.deleteByPrimaryKey(id);
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);

        tbSpecificationOptionMapper.deleteByExample(example);
    }
}
