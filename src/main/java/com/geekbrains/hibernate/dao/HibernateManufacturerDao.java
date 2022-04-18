package com.geekbrains.hibernate.dao;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.geekbrains.hibernate.entity.Product ;
import java.util.Collections;
import java.util.List;


@Component
@RequiredArgsConstructor
public class HibernateManufacturerDao implements ProductDao {

    private final SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return Collections.unmodifiableList(sessionFactory.getCurrentSession().createQuery("from Product m").list());
    }

    @Override
    @Transactional(readOnly = true)
    public String findNameById(Long id) {
        return (String) sessionFactory.getCurrentSession().getNamedQuery("Product.findNameById").setParameter("id", id).uniqueResult();
    }
    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return (Product) sessionFactory.getCurrentSession().getNamedQuery("Product.findById").setParameter("id", id).uniqueResult();
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteById(Long id) {
        return sessionFactory.getCurrentSession().createQuery("delete from Product m where m.id = :id").setParameter("id", id).executeUpdate();
    }


    @Override
    public void insert(Product product) {

    }
    @Override
    public void update(Product product) {
    }

}
