package br.net.ops.fiscalize.domain;

import br.net.ops.fiscalize.exception.DespesaReflectionException;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@NamedQueries({
        @NamedQuery(
                name = "findAllDespesas",
                query = "FROM despesa despesa ORDER BY despesa.despesaId"
        )
})

@Entity(name = "despesa")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"idDeputado", "numeroCarteiraParlamentar", "legislatura",
        "ano", "mes", "lote", "cnpjCPF", "codigoLegislatura", "dataEmissao", "parcela", "fornecedor",
        "numero", "passageiro", "valorLiquido", "ressarcimento", "valorDocumento"}))
public class Despesa {

    private static final String TABELA_DESPESA = "Despesa";
    public String nomeParlamentar;
    public String idDeputado;
    public String numeroCarteiraParlamentar;
    public String legislatura;
    public String siglaUF;
    public String siglaPartido;
    public String codigoLegislatura;
    public String numeroSubCota;
    public String descricao;
    public String numeroEspecificacaoSubCota;
    public String descricaoEspecificacao;
    public String fornecedor;
    public String cnpjCPF;
    public String numero;
    public String tipoDocumento;
    public String dataEmissao;
    public String valorDocumento;
    public String valorGlosa;
    public String valorLiquido;
    public String mes;
    public String ano;
    public String parcela;
    public String passageiro;
    public String trecho;
    public String lote;
    public String ressarcimento;
    @Temporal(TemporalType.TIMESTAMP)
    public Date dataInclusao;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer despesaId;

    public String retornarCreateSQL() throws DespesaReflectionException {
        ArrayList<String> listaCampos = retornarCampos();

        StringBuilder sb = new StringBuilder();
        for (String campo : listaCampos) {
            sb.append(campo + " VARCHAR(500), ");
        }

        String campos = sb.toString();
        if (campos.length() > 2)
            campos = campos.substring(0, campos.length() - 2); // retirando  ultima  virgula e espaco

        return "CREATE TABLE " + TABELA_DESPESA + " (despesaId INT PRIMARY KEY, " + campos + ");";
    }

    public String retornarInsertSQL() throws DespesaReflectionException {
        return "INSERT INTO " + TABELA_DESPESA + " (" + retornarCamposInsert() + ") VALUES (" + retornarValoresInsert() + ");";
    }

    // Retorna NOME dos campos para o insert
    private String retornarCamposInsert() {
        ArrayList<String> listaCampos = retornarCampos();

        StringBuilder sb = new StringBuilder();
        for (String campo : listaCampos) {
            sb.append(campo + ", ");
        }

        String retorno = sb.toString();
        if (retorno.length() > 2) retorno = retorno.substring(0, retorno.length() - 2); // retirando último ", "

        return retorno;
    }

    // Retorna VALOR dos campos para o insert
    private String retornarValoresInsert() throws DespesaReflectionException {
        ArrayList<String> listaCampos = retornarCampos();

        StringBuilder sb = new StringBuilder();
        for (String campo : listaCampos) {

            try {
                String valor = BeanUtils.getProperty(this, campo);
                if (valor == null) valor = "";
                sb.append("'").append(valor.replaceAll("'", "''").replaceAll("\"", "\"\"")).append("', "); // Replace dos caracteres ' e "
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NullPointerException e) {
                throw new DespesaReflectionException(e);
            }
        }

        String retorno = sb.toString();
        if (retorno.length() > 0) retorno = retorno.substring(0, retorno.length() - 2);

        return retorno;
    }

    // Usa reflection para retornar os campos da classe
    private ArrayList<String> retornarCampos() {
        Class<?> classe = Despesa.class;

        Field[] campos = classe.getFields();
        ArrayList<String> listaCampos = new ArrayList<String>();
        for (Field campo : campos) {
            listaCampos.add(campo.getName());
        }
        Collections.sort(listaCampos);

        return listaCampos;
    }

    public String toString() {
        return nomeParlamentar + "(" + siglaPartido + "/" + siglaUF + ")";
    }

    public String getNomeParlamentar() {
        return nomeParlamentar;
    }

    public void setNomeParlamentar(String nomeParlamentar) {
        this.nomeParlamentar = nomeParlamentar;
    }

    public String getIdDeputado() {
        return idDeputado;
    }

    public void setIdDeputado(String idDeputado) {
        this.idDeputado = idDeputado;
    }

    public String getNumeroCarteiraParlamentar() {
        return numeroCarteiraParlamentar;
    }

    public void setNumeroCarteiraParlamentar(String numeroCarteiraParlamentar) {
        this.numeroCarteiraParlamentar = numeroCarteiraParlamentar;
    }

    public String getLegislatura() {
        return legislatura;
    }

    public void setLegislatura(String legislatura) {
        this.legislatura = legislatura;
    }

    public String getSiglaUF() {
        return siglaUF;
    }

    public void setSiglaUF(String siglaUF) {
        this.siglaUF = siglaUF;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public void setSiglaPartido(String siglaPartido) {
        this.siglaPartido = siglaPartido;
    }

    public String getCodigoLegislatura() {
        return codigoLegislatura;
    }

    public void setCodigoLegislatura(String codigoLegislatura) {
        this.codigoLegislatura = codigoLegislatura;
    }

    public String getNumeroSubCota() {
        return numeroSubCota;
    }

    public void setNumeroSubCota(String numeroSubCota) {
        this.numeroSubCota = numeroSubCota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroEspecificacaoSubCota() {
        return numeroEspecificacaoSubCota;
    }

    public void setNumeroEspecificacaoSubCota(String numeroEspecificacaoSubCota) {
        this.numeroEspecificacaoSubCota = numeroEspecificacaoSubCota;
    }

    public String getDescricaoEspecificacao() {
        return descricaoEspecificacao;
    }

    public void setDescricaoEspecificacao(String descricaoEspecificacao) {
        this.descricaoEspecificacao = descricaoEspecificacao;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCnpjCPF() {
        return cnpjCPF;
    }

    public void setCnpjCPF(String cnpjCPF) {
        this.cnpjCPF = cnpjCPF;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(String valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public String getValorGlosa() {
        return valorGlosa;
    }

    public void setValorGlosa(String valorGlosa) {
        this.valorGlosa = valorGlosa;
    }

    public String getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(String valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(String passageiro) {
        this.passageiro = passageiro;
    }

    public String getTrecho() {
        return trecho;
    }

    public void setTrecho(String trecho) {
        this.trecho = trecho;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getRessarcimento() {
        return ressarcimento;
    }

    public void setRessarcimento(String ressarcimento) {
        this.ressarcimento = ressarcimento;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

}
