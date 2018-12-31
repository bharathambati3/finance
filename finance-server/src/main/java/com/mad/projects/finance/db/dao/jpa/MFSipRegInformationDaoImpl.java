package com.mad.projects.finance.db.dao.jpa;

import com.mad.projects.finance.db.dao.MFSipRegInformationDaoCustom;
import com.mad.projects.finance.db.entity.MFSipRegInformation;
import com.mad.projects.finance.domain.request.MFSipInfoFilterRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class MFSipRegInformationDaoImpl implements MFSipRegInformationDaoCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<MFSipRegInformation> getSipInfo(MFSipInfoFilterRequest filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MFSipRegInformation> query = cb.createQuery(MFSipRegInformation.class);
        Root<MFSipRegInformation> root = query.from(MFSipRegInformation.class);
        query.select(root).where(getInvestmentPredicates(filter, cb, root));
        return em.createQuery(query).getResultList();
    }

    private Predicate[] getInvestmentPredicates(MFSipInfoFilterRequest filter, CriteriaBuilder cb, Root<MFSipRegInformation> root) {

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(root.get("userId"), filter.getUserId()));

        if (filter.getSchemeId() != null) {
            predicates.add(cb.equal(root.get("scheme").get("id"), filter.getSchemeId()));
        }

        if (filter.getTaxSaving() != null) {
            predicates.add(cb.equal(root.get("scheme").get("isTaxSaving"), filter.getTaxSaving()));
        }

        if (filter.getCompanyId() != null) {
            predicates.add(cb.equal(root.get("scheme").get("company").get("id"), filter.getCompanyId()));
        }

        if (filter.getFundType() != null) {
            predicates.add(cb.equal(root.get("scheme").get("fundType"), filter.getFundType()));
        }

        if (filter.getRegisteredDateRange() != null) {
            if (filter.getRegisteredDateRange().getStartDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("registeredOn"), filter.getRegisteredDateRange().getStartDate()));
            }

            if (filter.getRegisteredDateRange().getEndDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("registeredOn"), filter.getRegisteredDateRange().getEndDate()));
            }
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
