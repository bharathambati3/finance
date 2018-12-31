package com.mad.projects.finance.db.dao;

import com.mad.projects.finance.db.entity.MFInvestment;
import com.mad.projects.finance.domain.request.MFFilterRequest;

import java.util.List;

public interface MFInvestmentDaoCustom {

    List<MFInvestment> getInvestments(MFFilterRequest filter);
}
