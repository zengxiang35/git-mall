package com.web.mall.pojoGroup;

import com.web.mall.pojo.TbSpecification;
import com.web.mall.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

public class Specification implements Serializable {
    private TbSpecification specification;   //tb_specification表

    private List<TbSpecificationOption> specificationOptionList; //TbSpecificationOption  ${tSpecificationOption}

    public TbSpecification getSpecification() {
        return specification;
    }

    public void setSpecification(TbSpecification specification) {
        this.specification = specification;
    }

    public List<TbSpecificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }

    public void setSpecificationOptionList(List<TbSpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }
}
