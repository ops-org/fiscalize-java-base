package br.net.ops.fiscalize.dao;

import br.net.ops.fiscalize.domain.Usuario;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UsuarioDao extends HibernateGenericDao<Usuario, Integer> {

    public Usuario criar() {
        Usuario usuario = new Usuario();
        usuario.setTokenId(UUID.randomUUID().toString());
        usuario.setUsuarioId(this.save(usuario));
        return usuario;
    }

}
