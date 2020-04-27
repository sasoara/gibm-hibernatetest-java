package com.sasoara.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAccount;

    @Enumerated(EnumType.STRING)
    private AccountType typ;

    private Double betrag;

    private Double zins;

    private Boolean aktiv;

    public Account() {
    }


    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public AccountType getTyp() {
        return typ;
    }

    public void setTyp(AccountType typ) {
        this.typ = typ;
    }

    public Double getBetrag() {
        return betrag;
    }

    public void setBetrag(Double betrag) {
        this.betrag = betrag;
    }

    public Double getZins() {
        return zins;
    }

    public void setZins(Double zins) {
        this.zins = zins;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

}
