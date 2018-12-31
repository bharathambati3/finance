package com.mad.projects.finance.db.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "mf_scheme_nav_tracker",
        uniqueConstraints={@UniqueConstraint(columnNames={"scheme_id", "nav", "nav_date"})})
public class MFSchemeNavTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "scheme_id")
    private MFScheme scheme;

    @Column(name = "nav_date")
    private LocalDate navDate;

    private BigDecimal nav;

    private LocalDateTime createdOn;

    public MFSchemeNavTracker() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MFScheme getScheme() {
        return scheme;
    }

    public void setScheme(MFScheme scheme) {
        this.scheme = scheme;
    }

    public LocalDate getNavDate() {
        return navDate;
    }

    public void setNavDate(LocalDate navDate) {
        this.navDate = navDate;
    }

    public BigDecimal getNav() {
        return nav;
    }

    public void setNav(BigDecimal nav) {
        this.nav = nav;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
