package com.mad.projects.finance.controller;

import com.mad.projects.finance.db.entity.MFCompany;
import com.mad.projects.finance.db.entity.MFScheme;
import com.mad.projects.finance.domain.request.*;
import com.mad.projects.finance.domain.response.*;
import com.mad.projects.finance.domain.vo.IndividualInvestmentInfo;
import com.mad.projects.finance.domain.vo.MFSipMonthlyVo;
import com.mad.projects.finance.domain.vo.MFSipPaymentVo;
import com.mad.projects.finance.processor.MFProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/mf")
public class MFRestController {

    @Autowired
    MFProcessor mfProcessor;

    @GetMapping(value = "/companies", produces = "application/json")
    public List<MFCompany> findMFCompanies() {
        return mfProcessor.findMFCompanies();
    }

    @GetMapping(value = "/company/{id}", produces = "application/json")
    public MFCompany findMFCompany(@PathVariable Integer id) {
        return mfProcessor.findMFCompany(id);
    }

    @PostMapping(value = "/company", produces = "application/json")
    public MFCompany saveMFCompany(@Valid @RequestBody MFCompany mfCompany) {
        return mfProcessor.saveMFCompany(mfCompany);
    }

    @PutMapping("/company/{id}")
    public MFCompany updateMFCompany(@PathVariable Integer id, @Valid @RequestBody MFCompany mfCompany) {
        return mfProcessor.updateMFCompany(id, mfCompany);
    }

    @DeleteMapping(value = "/company/{id}")
    public ResponseEntity<?> deleteMFCompany(@PathVariable Integer id) {
        return mfProcessor.deleteMFCompany(id);
    }

    @GetMapping(value = "/schemes", produces = "application/json")
    public List<MFScheme> findMFSchemes() {
        return mfProcessor.findMFSchemes();
    }

    @GetMapping(value = "/scheme/{id}", produces = "application/json")
    public MFScheme findMFScheme(@PathVariable Integer id) {
        return mfProcessor.findMFScheme(id);
    }

    @PostMapping(value = "/scheme", produces = "application/json")
    public MFScheme saveMFScheme(@Valid @RequestBody MFScheme mfScheme) {
        return mfProcessor.saveMFScheme(mfScheme);
    }

    @PutMapping("/scheme/{id}")
    public MFScheme updateMFScheme(@PathVariable Integer id, @Valid @RequestBody MFScheme mfScheme) {
        return mfProcessor.updateMFScheme(id, mfScheme);
    }

    @DeleteMapping(value = "/scheme/{id}")
    public ResponseEntity<?> deleteMFScheme(@PathVariable Integer id) {
        return mfProcessor.deleteMFScheme(id);
    }

    @PostMapping("/investment/purchaseAmount")
    public PurchaseAmountResponse investmentAmount(@Valid @RequestBody MFFilterRequest filter) {
        return mfProcessor.investmentAmount(filter);
    }

    @PostMapping("/investments")
    public List<IndividualInvestmentInfo> getInvestments(@Valid @RequestBody MFFilterRequest filter) {
        return mfProcessor.getInvestments(filter);
    }

    @PostMapping("/investments/mature")
    public List<IndividualInvestmentInfo> getMatureInvestments(@Valid @RequestBody MFFilterRequest filter) {
        return mfProcessor.getMatureInvestments(filter);
    }

    @PostMapping("/investment")
    public IndividualInvestmentInfo addInvestment(@Valid @RequestBody MFInvestmentRequest req) {
        return mfProcessor.addInvestment(req);
    }

    @PutMapping("/investment/{id}")
    public IndividualInvestmentInfo updateInvestment(@PathVariable Integer id, @Valid @RequestBody MFInvestmentRequest req) {
        return mfProcessor.updateInvestment(req, id);
    }

    @DeleteMapping("/investment/{id}")
    public void deleteInvestment(@PathVariable Integer id) {
        mfProcessor.deleteInvestment(id);
    }

    @PostMapping("/investment/purchaseAmount/group/{groupName}")
    public GroupedPurchaseAmountResponse groupInvestmentAmount(@PathVariable String groupName, @Valid @RequestBody MFFilterRequest filter) {
        return mfProcessor.groupInvestmentAmount(filter, groupName);
    }

    @PostMapping(value = "/sips", produces = "application/json")
    public MFSipRegInfoResponse getSipRegInformation(@Valid @RequestBody MFSipInfoFilterRequest filter) {
        return mfProcessor.getSipRegInformation(filter);
    }

    @PostMapping(value = "/daily/amount/payable", produces = "application/json")
    public List<MFSipRegDailyInfoResponse> getSipDailyAmountPayable(@Valid @RequestBody MFSipInfoFilterRequest filter) {
        return mfProcessor.getSipDailyAmountPayable(filter);
    }

    @PostMapping(value = "/sip/payable", produces = "application/json")
    public MFSipPayableInfoResponse getSipInfo(@Valid @RequestBody MFSipPayableFilterRequest filter) {
        return mfProcessor.getSipInfo(filter);

    }

    @PostMapping(value = "/upcoming/sips", produces = "application/json")
    public List<MFSipPaymentVo> getUpcomingSips(@Valid @RequestBody MFUpComingSipRequest upComingSipRequest) {
        return mfProcessor.getUpComingSips(upComingSipRequest);

    }

    @PostMapping(value="/sips/monthly")
    public List<MFSipMonthlyVo> getMonthlySipsInfo(@Valid @RequestBody MFSipMonthlyRequest monthlyRequest) {
        return mfProcessor.getMonthlySipsInfo(monthlyRequest);
    }
}
