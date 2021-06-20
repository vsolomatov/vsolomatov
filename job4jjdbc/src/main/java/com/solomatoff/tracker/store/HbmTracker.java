package com.solomatoff.tracker.store;

import com.solomatoff.tracker.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public String add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item.getDescription();
    }

    @Override
    public void commentsInsert(String id, String[] commentsArray) {
        // Не стал реализовывать, потому что комментарии пока закомментированы в объекте Item
    }

    @Override
    public void replace(String id, Item item) {
        System.out.println("Started HbmTracker.replace");
        System.out.println("    id = " + id);
        item.setId(Integer.parseInt(id));
        System.out.println("    item = " + item);
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
        System.out.println("Finished HbmTracker.replace");
    }

    @Override
    public void delete(String id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item item = new Item(null);
        item.setId(Integer.parseInt(id));
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Item");
        int deletedRows  = query.executeUpdate();
        System.out.println("    --- deletedRows = " + deletedRows);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item order by id").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item where name = :name order by id");
        query.setParameter("name", key);
        List<Item> result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Item findById(String id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, Integer.parseInt(id));
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
