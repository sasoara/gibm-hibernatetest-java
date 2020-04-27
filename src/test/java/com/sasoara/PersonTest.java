package com.sasoara;

import com.sasoara.model.Account;
import com.sasoara.model.AccountType;
import com.sasoara.model.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class PersonTest {

    static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    private static Map getProperties() {
        Map result = new HashMap();

        // <property name="javax.persistence.jdbc.password" value=""/>
        result.put("javax.persistence.jdbc.password", System.getenv("DB_PW"));

        return result;
    }

    @BeforeAll
    public static void setUpBeforeAll() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("com.sasoara.model", getProperties());
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void tearDownAfterAll() throws Exception {
        entityManagerFactory.close();
    }

    @Test
    public void persist_load_comparePerson() {
        entityManager.getTransaction().begin();

        Set<Account> debitAccounts = new HashSet<>();
        Set<Account> creditAccounts = new HashSet<>();

        Account debitAccount_one = new Account();
        debitAccount_one.setTyp(AccountType.DEBIT);
        debitAccount_one.setBetrag(230000.00);
        debitAccount_one.setZins(1.5);
        debitAccount_one.setAktiv(true);

        Account debitAccount_two = new Account();
        debitAccount_two.setTyp(AccountType.DEBIT);
        debitAccount_two.setBetrag(25600.00);
        debitAccount_two.setZins(3.0);
        debitAccount_two.setAktiv(true);


        Account creditAccount_one = new Account();
        creditAccount_one.setTyp(AccountType.CREDIT);
        creditAccount_one.setBetrag(400.00);
        creditAccount_one.setZins(15.0);
        creditAccount_one.setAktiv(false);

        debitAccounts.add(debitAccount_one);
        debitAccounts.add(debitAccount_two);

        creditAccounts.add(creditAccount_one);

        Person dana = new Person();
        dana.setVorname("Dana J.");
        dana.setName("Hake");
        dana.setStrasse("Sonnenbergstrasse");
        dana.setHausnummer(148);
        dana.setPostleitzahl("8405");
        dana.setOrt("Gotzenwil");
        dana.setLand("Schweiz");
        dana.setTelefonnummer("+41525331033");
        dana.addDebitAccount(debitAccount_one);
        dana.addDebitAccount(debitAccount_two);
        dana.addCreditAccount(creditAccount_one);

        entityManager.persist(dana);
        entityManager.getTransaction().commit();

        Person loadedPerson = entityManager.find(Person.class, dana.getIdPerson());
        System.out.println(loadedPerson);

        Assertions.assertEquals(dana, loadedPerson);
    }
}
