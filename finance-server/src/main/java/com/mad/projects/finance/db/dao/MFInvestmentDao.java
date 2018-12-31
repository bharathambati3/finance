package com.mad.projects.finance.db.dao;

import com.mad.projects.finance.db.entity.MFInvestment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MFInvestmentDao extends JpaRepository<MFInvestment, Integer>, MFInvestmentDaoCustom {

}
