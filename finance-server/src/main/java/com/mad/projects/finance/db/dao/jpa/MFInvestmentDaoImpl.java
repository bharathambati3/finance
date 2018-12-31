package com.mad.projects.finance.db.dao.jpa;

import com.mad.projects.finance.db.dao.MFInvestmentDaoCustom;
import com.mad.projects.finance.db.entity.MFInvestment;
import com.mad.projects.finance.domain.request.MFFilterRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MFInvestmentDaoImpl implements MFInvestmentDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<MFInvestment> getInvestments(MFFilterRequest filter) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<MFInvestment> query = cb.createQuery(MFInvestment.class);

        Root<MFInvestment> root = query.from(MFInvestment.class);

        CriteriaQuery<MFInvestment> selectQuery = query.select(root);

        selectQuery.where(getInvestmentPredicates(filter, cb, root));

        return em.createQuery(query).getResultList();
    }

    private Predicate[] getInvestmentPredicates(MFFilterRequest filter, CriteriaBuilder cb, Root<MFInvestment> root) {

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

        if (filter.getInvestmentType() != null) {
            predicates.add(cb.equal(root.get("investmentType"), filter.getInvestmentType()));
        }

        if (filter.getDateTimeRange() != null) {
            if (filter.getDateTimeRange().getStartDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("purchasedOn"), filter.getDateTimeRange().getStartDate()));
            }

            if (filter.getDateTimeRange().getEndDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("purchasedOn"), filter.getDateTimeRange().getEndDate()));
            }
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
