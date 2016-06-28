package br.net.ops.fiscalize.dao;

import java.util.List;
import java.util.Map;

import br.net.ops.fiscalize.util.Utilidade;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.net.ops.fiscalize.domain.NotaFiscal;
import br.net.ops.fiscalize.pojo.PedidoNota;

@Repository
public class NotaFiscalDao extends HibernateGenericDao<NotaFiscal, Integer> {

    // Retorna uma nota aleatoria de acordo com os parametros de pedidoNota.
    // Se nao achar, retorna qualquer uma.
    public NotaFiscal pegarRandomica(PedidoNota pedidoNota) {

        Session session = sessionFactory.getCurrentSession();

        // Selecionar notaFiscalIds passiveis de consulta
        String sql = "SELECT notaFiscalId FROM NotaFiscal, Parlamentar"
                + " WHERE NotaFiscal.parlamentarId = Parlamentar.parlamentarId";

        if(pedidoNota.getParlamentarId()!=0) {
            sql += " AND NotaFiscal.parlamentarId = " + pedidoNota.getParlamentarId();
        }

        if(pedidoNota.getPartidoId()!=0) {
            sql += " AND Parlamentar.partidoId = " + pedidoNota.getPartidoId();
        }

        SQLQuery query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List data = query.list();

        Integer notaFiscalId = null;
        if(data.size()>0) {
            int random = Utilidade.randomInteger(0, data.size());
            Map row = (Map) data.get(random);
            notaFiscalId = (Integer) row.get("notaFiscalId");
        }

        Criteria criteria = session.createCriteria(NotaFiscal.class);
        if(notaFiscalId!=null) {
            criteria.add(Restrictions.eq("notaFiscalId", notaFiscalId));
        } else {
            criteria.add(Restrictions.sqlRestriction("1=1 ORDER BY RAND()")); // se não pegou notaFiscalId, pega aleatório de maneira lenta
        }
        criteria.setMaxResults(1);

        return (NotaFiscal) criteria.uniqueResult();
    }
	
}
