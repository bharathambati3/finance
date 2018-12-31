package com.mad.projects.finance.domain.response;

import java.math.BigDecimal;
import java.util.*;

public class MFSipRegDailyInfoResponse {

    private Integer day;
    private BigDecimal dayAmount;
    private BigDecimal cumulativeAmount;

    private MFSipRegDailyInfoResponse() {
    }

    private MFSipRegDailyInfoResponse setDay(Integer day) {
        this.day = day;
        return this;
    }

    private MFSipRegDailyInfoResponse setDayAmount(BigDecimal dayAmount) {
        this.dayAmount = dayAmount;
        return this;
    }

    private MFSipRegDailyInfoResponse setCumulativeAmount(BigDecimal cumulativeAmount) {
        this.cumulativeAmount = cumulativeAmount;
        return this;
    }

    public Integer getDay() {
        return day;
    }

    public BigDecimal getDayAmount() {
        return dayAmount;
    }

    public BigDecimal getCumulativeAmount() {
        return cumulativeAmount;
    }

    public static List<MFSipRegDailyInfoResponse> create(Map<Integer, BigDecimal> main) {

        Set<Integer> integers = main.keySet();
        List<Integer> ints = new ArrayList<>(integers);
        Collections.sort(ints);

        Map<Integer, BigDecimal> d2c = mapToCummulative(main, ints);

        List<MFSipRegDailyInfoResponse> resp = new ArrayList<>();
        for (Integer day : ints) {
            BigDecimal dailyAmount = main.get(day);
            BigDecimal cumulativeAmount = d2c.get(day);
            resp.add(new MFSipRegDailyInfoResponse().setDay(day).setDayAmount(dailyAmount).setCumulativeAmount(cumulativeAmount));
        }
        return resp;
    }

    private static Map<Integer, BigDecimal> mapToCummulative(Map<Integer, BigDecimal> collect, List<Integer> ints) {

        BigDecimal total = new BigDecimal(0);

        Map<Integer, BigDecimal> d2c = new HashMap<>();
        for (Integer anInt : ints) {
            BigDecimal bigDecimal = collect.get(anInt);
            total = total.add(bigDecimal);
            d2c.put(anInt, total);
        }
        return d2c;
    }
}
