package ru.stqa.giv.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.giv.addressbook.model.ContractData;
import ru.stqa.giv.addressbook.model.Contracts;
import ru.stqa.giv.addressbook.model.GroupData;
import ru.stqa.giv.addressbook.model.Groups;

import java.util.List;

public class DBHelper {

    private final SessionFactory sessionFactory;

    public DBHelper(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }


    public Contracts contracts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContractData> result = session.createQuery("from ContractData").list();
        session.getTransaction().commit();
        session.close();
        return new Contracts(result);
    }
}
