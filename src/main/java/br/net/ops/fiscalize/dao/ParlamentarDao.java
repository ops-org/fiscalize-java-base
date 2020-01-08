package br.net.ops.fiscalize.dao;

import br.net.ops.fiscalize.domain.Parlamentar;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParlamentarDao extends HibernateGenericDao<Parlamentar, Integer> {

    public List<Parlamentar> recuperarParlamentares() {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Parlamentar.class);
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }

}
