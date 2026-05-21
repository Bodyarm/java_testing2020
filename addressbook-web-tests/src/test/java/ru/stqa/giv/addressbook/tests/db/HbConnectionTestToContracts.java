package ru.stqa.giv.addressbook.tests.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.GroupData;

import java.util.List;

public class HbConnectionTestToContracts {

    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception{
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try{
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e){
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }


    @Test
    public void testHbConnection(){

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContractData> result = session.createQuery("from ContractData where LENGTH(phoneHome)!=0").list();
        System.out.println("Founded " + result.size()+ " contracts with phoneHome:");
        for( ContractData contract : result){
            System.out.print(contract);
            System.out.println(contract.getPhoneHome());
            System.out.println(contract.getGroups());

        }
        session.getTransaction().commit();
        session.close();

    }




}
