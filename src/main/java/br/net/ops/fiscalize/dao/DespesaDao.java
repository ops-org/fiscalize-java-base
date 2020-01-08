package br.net.ops.fiscalize.dao;

import br.net.ops.fiscalize.domain.Despesa;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DespesaDao extends HibernateGenericDao<Despesa, Integer> {

    @Override
    @SuppressWarnings("unchecked")
    public List<Despesa> list() {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("findAllDespesas");
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<String> selecionarPartidos() {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT siglaPartido as sigla FROM despesa");
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> selecionarParlamentares() {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT nomeParlamentar, siglaPartido as sigla, idDeputado FROM despesa");
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<String> selecionarCotas() {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT descricao as cota FROM despesa");
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<String> selecionarUfs() {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT siglaUF as uf FROM despesa");
        return query.list();
    }

}
