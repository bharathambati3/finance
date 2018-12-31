package com.mad.projects.finance.db.dao;

import com.mad.projects.finance.db.entity.MFSipRegInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MFSipRegInformationDao extends JpaRepository<MFSipRegInformation, Integer>, MFSipRegInformationDaoCustom {
}
