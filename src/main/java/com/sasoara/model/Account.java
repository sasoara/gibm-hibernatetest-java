package com.sasoara.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

@Entity
@Table(name = "Account", catalog = "modul223", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer idAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYP", unique = false, nullable = false)
    private AccountType typ;

    @Column(name = "BETRAG", unique = false, nullable = false)
    private Double betrag;

    @Column(name = "ZINS", unique = false, nullable = false)
    private Double zins;

    @Column(name = "AKTIV", unique = false, nullable = false)
    private Boolean aktiv;

    public Account() {
    }


    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
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

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", typ=" + typ +
                ", betrag=" + betrag +
                ", zins=" + zins +
                ", aktiv=" + aktiv +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return typ == account.typ &&
                betrag.equals(account.betrag) &&
                zins.equals(account.zins) &&
                aktiv.equals(account.aktiv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typ, betrag, zins, aktiv);
    }
}
