package com.mad.projects.finance.db.dao;

import com.mad.projects.finance.db.entity.MFCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFCompanyDao extends JpaRepository<MFCompany, Integer> {
}
