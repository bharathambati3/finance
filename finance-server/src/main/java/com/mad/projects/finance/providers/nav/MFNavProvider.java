package com.mad.projects.finance.providers.nav;

import com.mad.projects.finance.db.entity.MFScheme;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MFNavProvider {

    Map<MFScheme, BigDecimal> getLatestNav(List<MFScheme> schemeIds);
}
