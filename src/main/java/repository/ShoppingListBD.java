package repository;

import entity.Purchase;
import org.hibernate.*;
import org.hibernate.query.NativeQuery;
import org.junit.Assert;
import pages.ShoppingListPage;
import util.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingListBD {

    public void initializeTable() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Purchase purchase = new Purchase();
            session.save(purchase);
            session.getTransaction().commit();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void insertDataToDataTable() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                for (Purchase p : ShoppingListPage.getPurchaseList()) {
                    session.save(p);
                }
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

    public void getAllRows() {
        List<Purchase> list;
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String sql = "SELECT * FROM test.Purchases;";
                NativeQuery query = session.createSQLQuery(sql);
                query.addEntity(Purchase.class);
                list = query.list();

                System.out.println("ЧИТАЕМ ДАННЫМ ИЗ БАЗЫ");
                System.out.printf("%20s %20s %20s%n%n", "Что купить", "Количество", "Стоимость, кр");

                for (Object obj : list) {
                    Purchase purchase = (Purchase) obj;
                    System.out.printf("%20s %20d %20.2f%n", purchase.getName(), purchase.getNumber(), purchase.getAmount());
                }

                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

    public void isDataCorrect1() {
        List list = null;
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String sql = "SELECT * FROM test.Purchases;";
                NativeQuery query = session.createSQLQuery(sql);
                query.addEntity(Purchase.class);
                list = query.list();
                tx.commit();

            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
            List<Purchase> fromWebPageList = ShoppingListPage.getPurchaseList();
            List<Purchase> fromDataBase = list;

            Assert.assertEquals("Количество строк в таблице на сайте и в БД различается! Ошибка!!!", fromWebPageList.size(), fromDataBase.size());

        }
    }

    public void isDataCorrect2() {
        List list = null;
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String sql = "SELECT * FROM test.Purchases;";
                NativeQuery query = session.createSQLQuery(sql);
                query.addEntity(Purchase.class);
                list = query.list();
                tx.commit();

            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
            List<Purchase> fromWebPageList = ShoppingListPage.getPurchaseList();
            List<Purchase> fromDataBase = list;

            if (fromWebPageList.size() == fromDataBase.size()) {   // если одинаковое, то проверяем по каждой ячейке
                for (int i = 0; i < fromWebPageList.size(); i++) {
                    Assert.assertEquals("Отличия в поле 'Что купить ' в строке " + (i + 1) + " ", fromWebPageList.get(i).getName(), fromDataBase.get(i).getName());
                    Assert.assertEquals("Отличия в поле 'Количество' в строке " + (i + 1) + " ", fromWebPageList.get(i).getNumber(), fromDataBase.get(i).getNumber());
                    Assert.assertEquals("Отличия в поле 'стоимость, кр' в строке " + (i + 1) + " ", fromWebPageList.get(i).getAmount(), fromDataBase.get(i).getAmount());
                }
            }
        }
    }

    public void isDataIncorrect1() {
        List list = null;
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String sql = "SELECT * FROM test.Purchases;";
                NativeQuery query = session.createSQLQuery(sql);
                query.addEntity(Purchase.class);
                list = query.list();
                tx.commit();

            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
            List<Purchase> fromWebPageList = ShoppingListPage.getPurchaseList();
            List<Purchase> fromDataBase = list;

            Assert.assertNotEquals("Количество строк в таблице на сайте и в БД одинаковое, тест не пройден!", fromWebPageList.size(), fromDataBase.size());
        }
    }

    public void isDataIncorrect2() {
        List list = null;
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String sql = "SELECT * FROM test.Purchases;";
                NativeQuery query = session.createSQLQuery(sql);
                query.addEntity(Purchase.class);
                list = query.list();
                tx.commit();

            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
            List<Purchase> fromWebPageList = ShoppingListPage.getPurchaseList();
            List<Purchase> fromDataBase = list;

            if (fromWebPageList.size() == fromDataBase.size()) {   // если одинаковое, то проверяем по каждой ячейке
                boolean check = true;
                for (int i = 0; i < fromWebPageList.size(); i++) {
                    if (!(fromWebPageList.get(i).getName().equals(fromDataBase.get(i).getName())) || !fromWebPageList.get(i).getNumber().equals(fromDataBase.get(i).getNumber())
                            || !fromWebPageList.get(i).getAmount().equals(fromDataBase.get(i).getAmount())) {
                        check = false;
                        break;
                    }
                }
                Assert.assertNotEquals("Ячейки в таблице на сайте и в БД одинаковые, тест не пройден!", check, true);
            }
        }
    }

    public void deleteTable() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String sql = "DROP TABLE test.Purchases;";
                NativeQuery query = session.createSQLQuery(sql);
                query.executeUpdate();
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }

    public void update() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                String sql = "UPDATE test.Purchases SET name='blablabla' WHERE id=0;";
                NativeQuery query = session.createSQLQuery(sql);
                query.executeUpdate();
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
    }
}