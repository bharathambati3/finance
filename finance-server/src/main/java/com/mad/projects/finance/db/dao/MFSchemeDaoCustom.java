package com.mad.projects.finance.db.dao;

import com.mad.projects.finance.db.entity.MFScheme;

import java.util.List;

public interface MFSchemeDaoCustom {
    List<MFScheme> getAmfiCodesToBeUpdated();

    List<MFScheme> batchUpdate(List<MFScheme> schemes);
}
