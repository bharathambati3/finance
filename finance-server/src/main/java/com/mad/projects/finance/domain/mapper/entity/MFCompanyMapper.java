package com.mad.projects.finance.domain.mapper.entity;

import com.mad.projects.finance.db.entity.MFCompany;
import com.mad.projects.finance.util.DateUtil;

public class MFCompanyMapper {

    private Integer id;
    private String name;
    private String createdOn;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public static MFCompanyMapper map(MFCompany company) {
        MFCompanyMapper mapper = new MFCompanyMapper();

        mapper.id = company.getId();
        mapper.name = company.getName();
        mapper.createdOn = DateUtil.format(company.getCreatedOn());
        return mapper;
    }
}
