package br.net.ops.fiscalize.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import br.net.ops.fiscalize.domain.Despesa;
import br.net.ops.fiscalize.domain.NotaFiscal;
import br.net.ops.fiscalize.domain.Parlamentar;

@Repository
public class ParlamentarDao extends HibernateGenericDao<Parlamentar, Integer> {

    public List<Parlamentar> recuperarParlamentares() {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Parlamentar.class);
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }

}
