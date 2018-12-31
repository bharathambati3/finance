package com.mad.projects.finance.db.dao;

import com.mad.projects.finance.db.entity.MFSchemeNavTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MFSchemeNavTrackerDao extends JpaRepository<MFSchemeNavTracker, Integer> {
}
