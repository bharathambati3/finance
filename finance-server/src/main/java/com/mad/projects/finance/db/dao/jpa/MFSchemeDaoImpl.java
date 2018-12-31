package com.mad.projects.finance.db.dao.jpa;

import com.mad.projects.finance.db.dao.MFSchemeDaoCustom;
import com.mad.projects.finance.db.entity.MFScheme;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MFSchemeDaoImpl implements MFSchemeDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<MFScheme> getAmfiCodesToBeUpdated() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<MFScheme> query = cb.createQuery(MFScheme.class);

        Root<MFScheme> root = query.from(MFScheme.class);

        LocalDateTime latestPublishedOn = getNavPublishDateTime();

        query.select(root)
                .where(cb.or(cb.isNull(root.get("latestNavDate")), cb.lessThan(root.get("latestNavDate"), latestPublishedOn)));

        return em.createQuery(query).getResultList();
    }

    /**
     * If current time is before 9 pm, then return prev day's 9pm.
     *      i.e any scheme's latestNavDate less than prev day 9pm will be eligible for nav update.
     * If current time is after 10pm, then return current day's 10pm.
     *      i.e any scheme's latestNavDate less than current day 10pm will be eligible for nav update.
     * If current time is in between 9pm & 10pm, then return current day's 9pm.
     *
     * @return Return date is considered as the latest date & time at which there was a nav published.
     *  therefore if (latest Nav date from db < the returned date) then the scheme is eligible for update.
     */
    private LocalDateTime getNavPublishDateTime() {
        LocalDateTime with;
        LocalTime time9Pm = LocalTime.of(21, 0, 0, 0); // 9PM
        LocalTime time10Pm = LocalTime.of(22, 0, 0, 0); //10PM

        LocalDateTime prevDay9pm = LocalDateTime.now().minus(1, ChronoUnit.DAYS).with(time9Pm);

        if (LocalTime.now().isBefore(time9Pm)) {
            with = prevDay9pm;
        } else {
            if (LocalTime.now().isAfter(time10Pm)) {
                with = LocalDateTime.now().with(time10Pm);
            } else {
                with = LocalDateTime.now().with(time9Pm);
            }
        }
        return with;
    }

    @Override
    @Transactional
    public List<MFScheme> batchUpdate(List<MFScheme> schemes) {
        return null;
    }
}
