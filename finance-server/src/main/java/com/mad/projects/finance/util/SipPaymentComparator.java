package com.mad.projects.finance.util;

import com.mad.projects.finance.db.entity.MFInvestment;
import com.mad.projects.finance.domain.response.MFSipRegInfoResponse;
import com.mad.projects.finance.domain.vo.MFSipMonthlyVo;
import com.mad.projects.finance.domain.vo.MFSipRegInformationVo;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class SipPaymentComparator {
    public List<MFSipMonthlyVo> compare(List<MFInvestment> investments, MFSipRegInfoResponse sipRegInformation, Year year, Month month) {


        investments = groupInvestments(investments);

        List<MFSipRegInformationVo> infos = sipRegInformation.getInfos();

        infos = groupRegInfo(infos);

        List<MFSipMonthlyVo> mfSipMonthlyVos = new ArrayList<>();
        for (MFSipRegInformationVo info : infos) {
            Integer id = info.getSchemeVo().getId();
            LocalDateTime startDate = info.getLocalStartDate();
            BigDecimal amount = info.getAmount();

            MFSipMonthlyVo setMonthlyVo = new MFSipMonthlyVo();
            setMonthlyVo.setScheme(info.getSchemeVo());
            LocalDate sipDate = LocalDate.now().
                    withDayOfMonth(startDate.getDayOfMonth())
                    .withMonth(month.getValue())
                    .withYear(year.getValue());

            setMonthlyVo.setSipDate(sipDate);
            setMonthlyVo.setAmount(amount);

            for (MFInvestment investment : investments) {
                Integer schemeId = investment.getScheme().getId();
                LocalDateTime purchasedOn = investment.getPurchasedOn();
                BigDecimal invAmount = investment.getAmount();

                if (schemeId.equals(id)
                        && (startDate.getDayOfMonth() == (purchasedOn.getDayOfMonth()))
                        && amount.equals(invAmount)) {
                    setMonthlyVo.setPurchaseNav(investment.getPurchaseNav());
                    setMonthlyVo.setInvestedOn(investment.getInvestedOn());
                    break;
                }
            }
            mfSipMonthlyVos.add(setMonthlyVo);
        }
        return mfSipMonthlyVos.stream().sorted(Comparator.comparing(MFSipMonthlyVo::getSipDate)).collect(Collectors.toList());
    }

    private List<MFSipRegInformationVo> groupRegInfo(List<MFSipRegInformationVo> infos) {
        Map<Duel, MFSipRegInformationVo> map = new HashMap<>();

        List<MFSipRegInformationVo> list = new ArrayList<>();

        for (MFSipRegInformationVo info : infos) {
            Duel duel = new Duel(info.getSchemeVo().getId(), info.getSipDate());
            if(map.containsKey(duel)) {
                MFSipRegInformationVo prevInfo = map.get(duel);
                BigDecimal prevAmount = prevInfo.getAmount();
                BigDecimal amount = info.getAmount();

                BigDecimal sum = prevAmount.add(amount);

                System.out.println(MessageFormat.format("Adding sip info {0} and {1} amount {3}",
                        info.getId(), prevInfo.getId(), sum));
                prevInfo.setAmount(sum);
            } else {
                map.put(duel, info);
                list.add(info);
            }
        }
        System.out.println(map);
        return list;
    }

    /**
     * Group amount investments based on scheme id, date
     */
    private List<MFInvestment> groupInvestments(List<MFInvestment> investments) {
        Map<Duel, MFInvestment> map = new HashMap<>();

        List<MFInvestment> invests = new ArrayList<>();
        for (MFInvestment investment : investments) {
            Duel duel = new Duel(investment.getScheme().getId(), investment.getPurchasedOn().getDayOfMonth());
            if (map.containsKey(duel)) {
                MFInvestment prevInvestment = map.get(duel);
                BigDecimal prevAmount = prevInvestment.getAmount();
                BigDecimal currAmount = investment.getAmount();

                BigDecimal sum = prevAmount.add(currAmount);

                System.out.println(MessageFormat.format("Adding investment {0} and {1} amount {3}",
                        investment.getId(), prevInvestment.getId(), sum));
                prevInvestment.setAmount(sum);
            } else {
                map.put(duel, investment);
                invests.add(investment);
            }
        }

        System.out.println(map);
        return invests;
    }

    class Duel {
        private int schemeId;
        private int day;
        private MFInvestment investment;

        Duel (int schemeId, int day) {

            this.schemeId = schemeId;
            this.day = day;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Duel duel = (Duel) o;
            return schemeId == duel.schemeId &&
                    day == duel.day;
        }

        @Override
        public int hashCode() {

            return Objects.hash(schemeId, day);
        }
    }
}
