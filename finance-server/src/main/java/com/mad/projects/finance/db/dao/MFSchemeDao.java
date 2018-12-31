package com.mad.projects.finance.db.dao;

import com.mad.projects.finance.db.entity.MFScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFSchemeDao extends JpaRepository<MFScheme, Integer>, MFSchemeDaoCustom {
}
