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
import java.util.List;
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

    @Test
    public void crudPerson() {
        // create an entitiy Person
        System.out.println("Starting Transaction");
        entityManager.getTransaction().begin();
        Person person = new Person();
        person.setVorname("Pankaj");
        person.setName("Poltra");
        person.setStrasse("Penkastrasse");
        person.setHausnummer(10);
        person.setPostleitzahl("1000");
        person.setOrt("Pentwil");
        person.setLand("Schweiz");
        person.setTelefonnummer("+41615515515");
        System.out.println("Saving Person to Database");

        entityManager.persist(person);
        entityManager.getTransaction().commit();
        System.out.println("Generated Person ID = " + person.getIdPerson());

        // get an object using primary key.
        Person loadedPerson = entityManager.find(Person.class, person.getIdPerson());
        System.out.println("got object " + loadedPerson.getName() + " " + loadedPerson.getIdPerson());


        Assertions.assertEquals(person, loadedPerson);


        // update an entity
        System.out.println("Person hausnummer = " + loadedPerson.getHausnummer());
        entityManager.getTransaction().begin();
        loadedPerson.setHausnummer(223);
        entityManager.getTransaction().commit();
        System.out.println("Person hausnummer = " + loadedPerson.getHausnummer());


        Assertions.assertEquals(person.getHausnummer(), 223);


        // get all the objects from Person table
        @SuppressWarnings("unchecked")
        List<Person> listPerson = entityManager.createQuery("SELECT p FROM Person p").getResultList();

        if (listPerson == null) {
            System.out.println("No person found . ");
        } else {
            for (Person p : listPerson) {
                System.out.println("Person name= " + p.getName() + ", Person id " + p.getIdPerson());
            }
        }

        // remove an entity
        entityManager.getTransaction().begin();
        System.out.println("Deleting Person with ID = " + loadedPerson.getIdPerson());
        entityManager.remove(loadedPerson);
        entityManager.getTransaction().commit();


        Assertions.assertEquals(null, entityManager.find(Person.class, loadedPerson.getIdPerson()));
    }
}
