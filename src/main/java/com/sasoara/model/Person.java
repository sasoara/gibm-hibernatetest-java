package com.sasoara.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Person", catalog = "modul223", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "TELEFONNUMMER")})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer idPerson;

    @Column(name = "NAME", unique = false, nullable = false)
    private String name;

    @Column(name = "VORNAME", unique = false, nullable = false)
    private String vorname;

    @Column(name = "STRASSE", unique = false, nullable = false)
    private String strasse;

    @Column(name = "HAUSNUMMER", unique = false, nullable = false, length = 10)
    private Integer hausnummer;

    @Column(name = "POSTLEITZAHL", unique = false, nullable = false, length = 10)
    private String postleitzahl;

    @Column(name = "ORT", unique = false, nullable = false)
    private String ort;

    @Column(name = "TELEFONNUMMER", unique = true, nullable = false, length = 15)
    private String telefonnummer;

    @Column(name = "LAND", unique = false, nullable = false, length = 50)
    private String land;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    private final Set<Account> debitAccounts = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    private final Set<Account> creditAccounts = new HashSet<>();

    public Person() {
    }


    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public Integer getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(Integer hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public Set<Account> getDebitAccounts() {
        return debitAccounts;
    }

    public void addDebitAccount(Account account) {
        debitAccounts.add(account);
    }

    public Set<Account> getCreditAccounts() {
        return creditAccounts;
    }

    public void addCreditAccount(Account account) {
        creditAccounts.add(account);
    }

    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idPerson +
                ", name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                ", strasse='" + strasse + '\'' +
                ", hausnummer=" + hausnummer +
                ", postleitzahl='" + postleitzahl + '\'' +
                ", ort='" + ort + '\'' +
                ", telefonnummer='" + telefonnummer + '\'' +
                ", land='" + land + '\'' +
                ", debitAccounts=" + debitAccounts +
                ", creditAccounts=" + creditAccounts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) &&
                vorname.equals(person.vorname) &&
                strasse.equals(person.strasse) &&
                hausnummer.equals(person.hausnummer) &&
                postleitzahl.equals(person.postleitzahl) &&
                ort.equals(person.ort) &&
                telefonnummer.equals(person.telefonnummer) &&
                land.equals(person.land) &&
                debitAccounts.equals(person.debitAccounts) &&
                creditAccounts.equals(person.creditAccounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, vorname, strasse, hausnummer, postleitzahl, ort, telefonnummer, land, debitAccounts, creditAccounts);
    }
}
