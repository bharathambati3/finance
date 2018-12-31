package com.mad.projects.finance.domain.vo;

import com.mad.projects.finance.db.entity.MFCompany;

import java.time.LocalDateTime;

public class MFCompanyVo {

    private Integer id;

    private String name;

    private LocalDateTime createdOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public static MFCompanyVo build(MFCompany company) {
        MFCompanyVo companyVo = new MFCompanyVo();
        companyVo.setCreatedOn(company.getCreatedOn());
        companyVo.setId(company.getId());
        companyVo.setName(company.getName());
        return companyVo;
    }
}
