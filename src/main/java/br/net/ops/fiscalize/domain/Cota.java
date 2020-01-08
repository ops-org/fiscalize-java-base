package br.net.ops.fiscalize.domain;

import com.google.gson.annotations.Expose;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "cota")
public class Cota {

    public static final String SEM_COTA = "NENHUMA COTA";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Integer cotaId;
    @Expose
    private String nome;

    @OneToMany(mappedBy = "cota", fetch = FetchType.LAZY)
    private List<NotaFiscal> notasFiscais = null;

    public static String retornarCotaNotNull(String cota) {
        if (cota == null || cota.equals("")) {
            cota = Cota.SEM_COTA;
        }
        return cota;
    }

    public String toString() {
        return String.valueOf(nome);
    }

    public Integer getCotaId() {
        return cotaId;
    }

    public void setCotaId(Integer cotaId) {
        this.cotaId = cotaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
