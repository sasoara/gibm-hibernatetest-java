package com.sasoara;

import com.sasoara.model.Person;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {

    static EntityManagerFactory entityManagerFactory;

    private static Map getProperties() {
        Map result = new HashMap();

        // <property name="javax.persistence.jdbc.password" value=""/>
        result.put( "javax.persistence.jdbc.password", System.getenv("DB_PW") );

        return result;
    }

    @BeforeAll
    public static void setUpBeforeAll() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("com.sasoara.model", getProperties());
    }

    @AfterAll
    public static void tearDownAfterAll() throws  Exception {
        entityManagerFactory.close();
    }

    @Test
    public void savePersonObject() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Person dana = new Person();
        dana.setVorname("Dana J.");
        dana.setName("Hake");
        dana.setStrasse("Sonnenbergstrasse");
        dana.setHausnummer(148);
        dana.setPostleitzahl("8405");
        dana.setOrt("Gotzenwil");
        dana.setLand("Schweiz");
        dana.setTelefonnummer("+41525331033");

        entityManager.persist(dana);

        entityManager.getTransaction().commit();
        entityManager.close();

        assertTrue(dana.getIdPerson() > 0);
    }
}
