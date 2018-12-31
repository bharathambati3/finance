package com.mad.projects.finance.processor;

import com.mad.projects.finance.db.dao.*;
import com.mad.projects.finance.db.entity.*;
import com.mad.projects.finance.domain.mapper.entity.MFSipRegInformationMapper;
import com.mad.projects.finance.domain.request.*;
import com.mad.projects.finance.domain.response.*;
import com.mad.projects.finance.domain.vo.*;
import com.mad.projects.finance.util.SipPaymentComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MFProcessor {

    @Autowired
    MFCompanyDao mfCompanyDao;

    @Autowired
    MFSchemeDao mfSchemeDao;

    @Autowired
    MFSchemeNavTrackerDao mfSchemeNavTrackerDao;

    @Autowired
    MFInvestmentDao mfInvestmentDao;

    @Autowired
    MFSipRegInformationDao mfSipRegInformationDao;

    private static final Logger log = LoggerFactory.getLogger(MFProcessor.class);

    public List<MFCompany> findMFCompanies() {
       return mfCompanyDao.findAll();
    }

    public MFCompany findMFCompany(Integer id) {
        Optional<MFCompany> optionalMfCompany = mfCompanyDao.findById(id);

        if (optionalMfCompany.isPresent()) {
            return optionalMfCompany.get();
        } else {
            throw new RuntimeException("No mf with company id " + id + " is found");
        }
    }

    public ResponseEntity<?> deleteMFCompany(Integer id) {
        MFCompany mfCompany = findMFCompany(id);
        mfCompanyDao.delete(mfCompany);
        return ResponseEntity.ok().build();
    }

    public MFCompany saveMFCompany(@Valid MFCompany mfCompany) {
        mfCompany.setCreatedOn(LocalDateTime.now());
        return mfCompanyDao.save(mfCompany);
    }

    public MFCompany updateMFCompany(Integer id, @Valid MFCompany mfCompany) {
        MFCompany company = findMFCompany(id);
        company.setName(mfCompany.getName());
        return mfCompanyDao.save(company);
    }

    public List<MFScheme> findMFSchemes() {
        return mfSchemeDao.findAll();
    }

    public MFScheme findMFScheme(Integer id) {
        Optional<MFScheme> optionalMfScheme = mfSchemeDao.findById(id);

        if (optionalMfScheme.isPresent()) {
            return optionalMfScheme.get();
        } else {
            throw new RuntimeException("No mf with company id " + id + " is found"); //todo:: handle with custom exception classes.
        }
    }

    public MFInvestment findInvestment(Integer id) {
        Optional<MFInvestment> optionalMfInvestment = mfInvestmentDao.findById(id);
        if (optionalMfInvestment.isPresent()) {
            return optionalMfInvestment.get();
        } else {
            throw new RuntimeException("No investment found for give id "+ id);
        }
    }

    public MFScheme saveMFScheme(@Valid MFScheme mfScheme) {
        mfScheme.setCreatedOn(null);
        mfScheme.setUpdatedOn(null);
        return mfSchemeDao.save(mfScheme);
    }

    public MFScheme updateMFScheme(Integer id, @Valid MFScheme mfScheme) {
        MFScheme scheme = findMFScheme(id);
        scheme.setCompany(mfScheme.getCompany());
        scheme.setFundType(mfScheme.getFundType());
        scheme.setUpdatedOn(null);
        scheme.setTaxSaving(mfScheme.getTaxSaving());
        scheme.setName(mfScheme.getName());
        return mfSchemeDao.save(scheme);
    }

    public ResponseEntity<?> deleteMFScheme(Integer id) {
        MFScheme mfScheme = findMFScheme(id);
        mfSchemeDao.delete(mfScheme);
        return ResponseEntity.ok().build();
    }

    public PurchaseAmountResponse investmentAmount(@Valid MFFilterRequest filter) {

        List<MFInvestment> investments = mfInvestmentDao. getInvestments(filter);

        PurchaseAmountResponse response = new PurchaseAmountResponse();
        response.setDescription("Invested scheme count " + investments.size());
        BigDecimal amount = investments.stream().map(MFInvestment::getAmount).reduce(new BigDecimal(0), BigDecimal::add);
        response.setAmount(amount.toEngineeringString());
        return response;
    }

    public List<IndividualInvestmentInfo> getInvestments(@Valid MFFilterRequest filter) {
        List<MFInvestment> investments = mfInvestmentDao. getInvestments(filter);
        List<IndividualInvestmentInfo> collect = investments.stream()
                .map(this::getIndividualInvestmentInfo)
                .sorted(Comparator.comparing(IndividualInvestmentInfo::getPurchasedOn))
                .collect(Collectors.toList());
        System.out.println("Found investments size "+collect.size());
        return collect;
    }

    public List<IndividualInvestmentInfo> getMatureInvestments(@Valid MFFilterRequest filter) {
        List<MFInvestment> investments = mfInvestmentDao. getInvestments(filter);
        List<IndividualInvestmentInfo> collect = investments.stream()
                .filter(e -> e.getInvestedOn().plus(1, ChronoUnit.YEARS).isBefore(LocalDateTime.now()))
                .map(this::getIndividualInvestmentInfo)
                .sorted(Comparator.comparing(IndividualInvestmentInfo::getPurchasedOn))
                .collect(Collectors.toList());
        System.out.println(MessageFormat.format("Found investments size {0} " +
                "after filtering mature investments size {1}", investments.size(), collect.size()));
        return collect;
    }



    private IndividualInvestmentInfo getIndividualInvestmentInfo(MFInvestment mfInvestment) {
        return new IndividualInvestmentInfo()
                .setAmount(mfInvestment.getAmount())
                .setCurrentValue(getCurrentValue(mfInvestment))
                .setName(mfInvestment.getScheme().getName())
                .setId(mfInvestment.getId())
                .setInvestmentType(mfInvestment.getInvestmentType())
                .setInvestedOn(mfInvestment.getInvestedOn())
                .setPurchaseNav(mfInvestment.getPurchaseNav())
                .setPurchasedOn(mfInvestment.getPurchasedOn())
                .setCurrentNav(mfInvestment.getScheme().getCurrentNav());
    }


    public GroupedPurchaseAmountResponse groupInvestmentAmount(@Valid MFFilterRequest filter, String groupName) {

        GroupedPurchaseAmountResponse response = new GroupedPurchaseAmountResponse();
        List<MFInvestment> investments = mfInvestmentDao. getInvestments(filter);
        Map<Integer, Optional<InvestmentInfo>> collect;
        List<InvestmentInfo> infos;
        switch (groupName) {
            case "company":
                collect = investments.stream()
                        .map(this::getCompanyInvestmentInfo)
                        .collect(Collectors.groupingBy(InvestmentInfo::getId, Collectors.reducing((i1, i2) -> {
                            BigDecimal amount = i1.getAmount().add(i2.getAmount());
                            BigDecimal currentValue = i1.getCurrentValue().add(i2.getCurrentValue());
                            return new InvestmentInfo(i1.getId(), i1.getName(), amount, currentValue);
                        })));

                infos = collect.entrySet().stream()
                        .filter(e -> e.getValue().isPresent())
                        .map(e -> e.getValue().get())
                        .collect(Collectors.toList());
                response.setInvestments(infos);
                break;
            case "scheme":
                collect = investments.stream()
                        .map(this::getSchemeInvestmentInfo)
                        .collect(Collectors.groupingBy(InvestmentInfo::getId, Collectors.reducing((i1, i2) -> {
                            BigDecimal amount = i1.getAmount().add(i2.getAmount());
                            BigDecimal currentValue = i1.getCurrentValue().add(i2.getCurrentValue());
                            return new InvestmentInfo(i1.getId(), i1.getName(), amount, currentValue);
                        })));

                 infos = collect.entrySet().stream()
                        .filter(e -> e.getValue().isPresent())
                        .map(e -> e.getValue().get())
                        .collect(Collectors.toList());
                response.setInvestments(infos);
                break;
            default:
                response.setError(new BaseError("given group name is not valid", "InvalidInput", 999));
                break;
        }

        return response;
    }

    private InvestmentInfo getCompanyInvestmentInfo(MFInvestment i) {
        Integer id = i.getScheme().getCompany().getId();
        String name = i.getScheme().getCompany().getName();

        BigDecimal profit = getCurrentValue(i);

        return new InvestmentInfo(id, name, i.getAmount(), profit.setScale(2, RoundingMode.HALF_UP));
    }

    private BigDecimal getCurrentValue(MFInvestment i) {
        BigDecimal units = i.getAmount().divide(i.getPurchaseNav(), 5, RoundingMode.HALF_UP);
        return units.multiply(i.getScheme().getCurrentNav());
    }

    private InvestmentInfo getSchemeInvestmentInfo(MFInvestment i) {
        Integer id = i.getScheme().getId();
        String name = i.getScheme().getName();

        BigDecimal profit = getCurrentValue(i);
        return new InvestmentInfo(id, name, i.getAmount(), profit.setScale(2, RoundingMode.HALF_UP))
                .setLatestNavUpdate(i.getScheme().getLatestNavDate());
    }

    public List<MFScheme> getAmfiCodesToBeUpdated() {
        return mfSchemeDao.getAmfiCodesToBeUpdated();
    }

    public void updateCurrentNav(Map<MFScheme, BigDecimal> navInfo) {

        navInfo.keySet().forEach(s -> {
            s.setCurrentNav(navInfo.get(s));
            s.setUpdatedOn(LocalDateTime.now());
            s.setLatestNavDate(LocalDateTime.now());
        });

        for (MFScheme scheme : navInfo.keySet()) {
            mfSchemeDao.save(scheme);
        }

        updateSchemeNavTracker(navInfo);
    }

    private void updateSchemeNavTracker(Map<MFScheme, BigDecimal> navInfo) {
        navInfo.keySet().forEach(s -> {
            MFSchemeNavTracker tracker = new MFSchemeNavTracker();
            tracker.setScheme(s);
            tracker.setCreatedOn(LocalDateTime.now());
            tracker.setNav(navInfo.get(s));
            tracker.setNavDate(LocalDate.now());
            try {

                mfSchemeNavTrackerDao.save(tracker);
            } catch (RuntimeException e) {
                log.error("Error ", e);
            }
        });
    }

    public MFSipRegInfoResponse getSipRegInformation(MFSipInfoFilterRequest filter) {
        List<MFSipRegInformation> sipInfo = mfSipRegInformationDao.getSipInfo(filter);

        MFSipRegInfoResponse response = new MFSipRegInfoResponse();

        List<MFSipRegInformationVo> vos = sipInfo.stream()
                .map(MFSipRegInformationVo::build)
                .filter(e -> (filter.getSipDate() == null || filter.getSipDate().equals(e.getSipDate())))
                .collect(Collectors.toList());
        response.setInfos(vos);
        response.setTotalAmount(vos.stream().map(MFSipRegInformationVo::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
        response.setCount(vos.size());
        return response;
    }

    public MFSipPayableInfoResponse getSipInfo(MFSipPayableFilterRequest filter) {

        MFSipInfoFilterRequest request = new MFSipInfoFilterRequest();
        request.setUserId(filter.getUserId());

        List<MFSipRegInformation> sipInfo = mfSipRegInformationDao.getSipInfo(request);


        MFSipPayableInfoResponse response = new MFSipPayableInfoResponse();

        DateTimeRange payableSipInDateRange = filter.getPayableSipInDateRange();
        if (payableSipInDateRange != null) {

            List<MFSipPayableVo> collect = sipInfo.stream()
                    .filter(e -> e.getRecurringType().equals(RecurringType.MONTHLY))
                    .map(e -> mapToPayableVo(e, payableSipInDateRange))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            response.setPayableVos(collect);
        }

        return response;
    }

    private MFSipPayableVo mapToPayableVo(MFSipRegInformation e, DateTimeRange payableSipInDateRange) {
        DateTimeRange fundActiveDateRange = new DateTimeRange(e.getStartDate(), e.getEndDate());
        DateTimeRange commonRange = DateTimeRange.getCommonRange(fundActiveDateRange, payableSipInDateRange);

        if (commonRange == null) {
            return null;
        }

        MFSipPayableVo payableVo = new MFSipPayableVo();

        List<LocalDateTime> paymentDates = getPaymentDates(commonRange, fundActiveDateRange.getStartDate().getDayOfMonth());
        if (paymentDates.isEmpty()) {
            return null;
        }
        payableVo.setPaymentDates(paymentDates);
        payableVo.setInfo(e);
        return payableVo;
    }

    private List<LocalDateTime> getPaymentDates(DateTimeRange commonRange, int paymentDay) {
        LocalDateTime current = commonRange.getStartDate();
        List<LocalDateTime> paymentDates = new ArrayList<>();

        while (commonRange.getEndDate().isAfter(current)) {
            //Find next payment date.
            LocalDateTime paymentDate = current.with(getNextPaymentDay(paymentDay, commonRange.getEndDate()));
            if (paymentDate == null) {
                current = current.with(TemporalAdjusters.firstDayOfNextMonth());
                continue;
            }
            paymentDates.add(paymentDate);
            current = current.with(TemporalAdjusters.firstDayOfNextMonth());
        }
        return paymentDates;
    }

    private TemporalAdjuster getNextPaymentDay(int paymentDay, LocalDateTime endDate) {

        return temporal -> {
            LocalDateTime from = LocalDateTime.from(temporal);
            int dayOfMonth = from.getDayOfMonth();
            if (paymentDay >= dayOfMonth) {
                LocalDateTime current = from.withDayOfMonth(paymentDay);
                if (current.isAfter(endDate)) {
                    return null;
                }
                return current;
            }
            return null;
        };
    }

    public List<MFSipPaymentVo> getUpComingSips(MFUpComingSipRequest upComingSipRequest) {

        MFSipPayableFilterRequest filter = new MFSipPayableFilterRequest();

        filter.setUserId(upComingSipRequest.getUserId());
        if (upComingSipRequest.getRange() == null) {
            upComingSipRequest.setRange(new DateTimeRange(LocalDateTime.now(), LocalDateTime.now().plusYears(1)));
        }
        filter.setPayableSipInDateRange(upComingSipRequest.getRange());
        MFSipPayableInfoResponse sipInfo = getSipInfo(filter);
        List<MFSipPayableVo> payableVos = sipInfo.getPayableVos();

        Integer limit = upComingSipRequest.getUpComingSip();
        limit = (limit == -1) ? Integer.MAX_VALUE : limit;
        List<MFSipPaymentVo> list = payableVos.stream()
                .flatMap(e -> e.getPaymentDates().stream()
                        .map(d -> new MFSipPaymentVo(MFSipRegInformationMapper.map(e.getInfo()), d)))
                .sorted(Comparator.comparing(MFSipPaymentVo::paymentDate))
                .limit(limit)
                .collect(Collectors.toList());

        return list;
    }

    public IndividualInvestmentInfo addInvestment(@Valid MFInvestmentRequest req) {
        MFInvestment investment = new MFInvestment();
        syncInvestment(req, investment);
        MFInvestment saved = mfInvestmentDao.save(investment);
        return getIndividualInvestmentInfo(saved);
    }

    private void syncInvestment(@Valid MFInvestmentRequest req, MFInvestment investment) {
        Integer schemeId = req.getSchemeId();
        MFScheme mfScheme = findMFScheme(schemeId);
        investment.setCreatedOn(LocalDateTime.now());
        investment.setUpdatedOn(LocalDateTime.now());
        investment.setUserId(req.getUserId());
        investment.setScheme(mfScheme);
        investment.setAmount(req.getAmount());
        investment.setInvestedOn(req.getInvestedOn());
        investment.setInvestmentType(req.getType());
        investment.setPurchasedOn(req.getPurchasedOn());
        investment.setPurchaseNav(req.getPurchaseNav());
    }

    public IndividualInvestmentInfo updateInvestment(@Valid MFInvestmentRequest req, Integer id) {
        MFInvestment investment = findInvestment(id);
        syncInvestment(req, investment);
        MFInvestment save = mfInvestmentDao.save(investment);
        return getIndividualInvestmentInfo(save);
    }

    public void deleteInvestment(Integer id) {
        MFInvestment investment = findInvestment(id);
        mfInvestmentDao.delete(investment);
    }

    public List<MFSipMonthlyVo> getMonthlySipsInfo(@Valid MFSipMonthlyRequest monthlyRequest) {
        //get all invested sips for this month.
        MFFilterRequest filter = new MFFilterRequest();
        filter.setUserId(monthlyRequest.getUserId());

        Year year = monthlyRequest.getYear();
        Month month = monthlyRequest.getMonth();
        LocalDate localDate = LocalDate.of(year.getValue(), month.getValue(), 1);
        LocalDateTime ldtBegin = LocalDateTime.of(localDate, LocalTime.now()).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime ldtEnd = LocalDateTime.of(localDate, LocalTime.now()).with(TemporalAdjusters.lastDayOfMonth());
        filter.setDateTimeRange(new DateTimeRange(ldtBegin, ldtEnd));
        filter.setInvestmentType(InvestmentType.SIP);

        List<MFInvestment> investments = mfInvestmentDao.getInvestments(filter);

        MFSipInfoFilterRequest request = new MFSipInfoFilterRequest();
        request.setUserId(monthlyRequest.getUserId());
        MFSipRegInfoResponse sipRegInformation = getSipRegInformation(request);

        return new SipPaymentComparator().compare(investments, sipRegInformation, year, month);
    }

    public List<MFSipRegDailyInfoResponse> getSipDailyAmountPayable(@Valid MFSipInfoFilterRequest filter) {
        MFSipRegInfoResponse sipRegInformation = getSipRegInformation(filter);
        List<MFSipRegInformationVo> infos = sipRegInformation.getInfos();
        Map<Integer, BigDecimal> collect = infos.stream()
                .collect(Collectors.toMap(MFSipRegInformationVo::getSipDate, MFSipRegInformationVo::getAmount, BigDecimal::add));
        return MFSipRegDailyInfoResponse.create(collect);
    }
}
