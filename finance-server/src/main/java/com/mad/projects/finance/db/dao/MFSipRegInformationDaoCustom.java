package com.mad.projects.finance.db.dao;

import com.mad.projects.finance.db.entity.MFSipRegInformation;
import com.mad.projects.finance.domain.request.MFSipInfoFilterRequest;

import java.util.List;

public interface MFSipRegInformationDaoCustom {
    List<MFSipRegInformation> getSipInfo(MFSipInfoFilterRequest filter);
}
