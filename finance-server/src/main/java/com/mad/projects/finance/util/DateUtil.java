package com.mad.projects.finance.util;

import com.mad.projects.finance.config.ApplicationConfig;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateUtil {

    public static String format(TemporalAccessor temporalAccessor) {
        if (temporalAccessor == null) {
            return null;
        }
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern(ApplicationConfig.getDateFormat());
        return dTF.format(temporalAccessor);
        }
        }
