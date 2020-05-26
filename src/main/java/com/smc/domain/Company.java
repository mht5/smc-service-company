package com.smc.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "COMPANY")
public class Company implements Serializable {

    private static final long serialVersionUID = -5842528076106429322L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "SECTOR_ID", nullable = false)
    private int sectorId;

    @ManyToOne(targetEntity=Sector.class)
    @JoinColumn(name="SECTOR_ID", insertable = false, updatable = false)
    private Sector sector;

    @Column(name = "TURNOVER", nullable = false)
    private BigDecimal turnover;

    @Column(name = "CEO", nullable = false)
    private String ceo;

    @Column(name = "BOARD_OF_DIRECTORS", nullable = false)
    private String boardOfDirectors;

    @Column(name = "LISTED_IN_STOCK_EXCHANGES", length = 1, nullable = false)
    @Type(type="yes_no")
    private Boolean listedInStockExchanges;

    @Column(name = "BRIEF_WRITEUP")
    private String briefWriteup;

    @Column(name = "DEACTIVATED", length = 1, nullable = false)
    @Type(type="yes_no")
    private Boolean deactivated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getBoardOfDirectors() {
        return boardOfDirectors;
    }

    public void setBoardOfDirectors(String boardOfDirectors) {
        this.boardOfDirectors = boardOfDirectors;
    }

    public Boolean getListedInStockExchanges() {
        return listedInStockExchanges;
    }

    public void setListedInStockExchanges(Boolean listedInStockExchanges) {
        this.listedInStockExchanges = listedInStockExchanges;
    }

    public String getBriefWriteup() {
        return briefWriteup;
    }

    public void setBriefWriteup(String briefWriteup) {
        this.briefWriteup = briefWriteup;
    }

    public Boolean getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Boolean deactivated) {
        this.deactivated = deactivated;
    }
}
