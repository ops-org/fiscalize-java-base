package br.net.ops.fiscalize.dao;

import br.net.ops.fiscalize.domain.Partido;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartidoDao extends HibernateGenericDao<Partido, Integer> {
    public List<Partido> recuperarPartidos() {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Partido.class);
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }
}
