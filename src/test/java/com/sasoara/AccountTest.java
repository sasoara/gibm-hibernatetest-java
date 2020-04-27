package com.sasoara;

import com.sasoara.model.Account;
import com.sasoara.model.AccountType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {

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
    public void persistAccount() {
        entityManager.getTransaction().begin();

        Account account = new Account();
        account.setTyp(AccountType.DEBIT);
        account.setBetrag(100.00);
        account.setZins(1.5);
        account.setAktiv(true);

        entityManager.persist(account);
        entityManager.getTransaction().commit();

        assertTrue(account.getIdAccount() > 0);
    }
}
