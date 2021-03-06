package br.net.ops.fiscalize.domain;

import com.google.gson.annotations.Expose;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "uf")
public class Uf {

    public static final String SEM_SIGLA_UF = "XX";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private Integer ufId;
    @Expose
    private String sigla;
    @Expose
    private String nome;

    @OneToMany(mappedBy = "cota", fetch = FetchType.LAZY)
    private List<NotaFiscal> notasFiscais = null;

    public static String retornarUfNotNull(String uf) {
        if (uf == null || uf.equals("")) {
            uf = Uf.SEM_SIGLA_UF;
        }
        return uf;
    }

    public String toString() {
        return String.valueOf(nome) + " - " + String.valueOf(sigla);
    }

    public Integer getUfId() {
        return ufId;
    }

    public void setUfId(Integer ufId) {
        this.ufId = ufId;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<NotaFiscal> getNotasFiscais() {
        return notasFiscais;
    }

    public void setNotasFiscais(List<NotaFiscal> notasFiscais) {
        this.notasFiscais = notasFiscais;
    }

}
