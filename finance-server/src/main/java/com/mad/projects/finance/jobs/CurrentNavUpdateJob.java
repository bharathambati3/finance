package com.mad.projects.finance.jobs;

import com.mad.projects.finance.db.entity.MFScheme;
import com.mad.projects.finance.processor.MFProcessor;
import com.mad.projects.finance.providers.nav.MFNavProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class CurrentNavUpdateJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(CurrentNavUpdateJob.class);

    @Autowired
    MFProcessor processor;

    @Autowired
    MFNavProvider provider;

    @Override
    @Scheduled(fixedDelay= 10*60*1000)
    public void execute() {
        List<MFScheme> schemes = processor.getAmfiCodesToBeUpdated();

        log.debug("Running current nav job");

        if (schemes.size() > 0) {

            log.info("Schemes to be updated "+ schemes);

            Map<MFScheme, BigDecimal> navInfo = provider.getLatestNav(schemes);
            System.out.println(navInfo);

            processor.updateCurrentNav(navInfo);

            log.info("Schemes updated "+ schemes);
        }
    }
}
