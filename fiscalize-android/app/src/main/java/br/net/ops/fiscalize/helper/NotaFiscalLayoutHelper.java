package br.net.ops.fiscalize.helper;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.net.ops.fiscalize.R;
import br.net.ops.fiscalize.enumerator.Mes;
import br.net.ops.fiscalize.vo.NotaFiscal;
import br.net.ops.fiscalize.volley.VolleySingleton;

public class NotaFiscalLayoutHelper {

    private static final String TAG = "NotaFiscalLayoutHelper";

    private static final String MOEDA = "R$ ";

    private static NotaFiscalLayoutHelper instance;

    public static synchronized NotaFiscalLayoutHelper getInstance() {
        if(instance==null) {
            instance = new NotaFiscalLayoutHelper();
        }
        return instance;
    }

    public void exibirNotaFiscal(final Activity activity, ViewGroup viewGroup, NotaFiscal notaFiscal) {

        final ViewHolderNotaFiscal viewHolder = new ViewHolderNotaFiscal(viewGroup);

        // Previne exibir imagem da �ltima nota fiscal antes de carregar imagem atual da web
        limparImagem(activity, viewHolder.fotoParlamentar);
        limparImagem(activity, viewHolder.fotoPartido);

        // Ainda sem dados no BD para esses dois campos
        viewHolder.emailParlamentar.setVisibility(View.GONE);
        viewHolder.nomePartido.setVisibility(View.GONE);

        viewHolder.nomeParlamentar.setText(notaFiscal.getParlamentar().getNome());
        viewHolder.emailParlamentar.setText(notaFiscal.getParlamentar().getEmail());
        viewHolder.nomePartido.setText(notaFiscal.getParlamentar().getPartido().getNome());
        viewHolder.siglaPartido.setText(notaFiscal.getParlamentar().getPartido().getSigla());
        viewHolder.cota.setText(notaFiscal.getCota().getNome());
        viewHolder.uf.setText(notaFiscal.getUf().getSigla());
        viewHolder.descricao.setText(notaFiscal.getDescricao());
        viewHolder.dataEmissao.setText(formatarDataEmissao(notaFiscal.getDataEmissao()));
        viewHolder.fornecedor.setText(notaFiscal.getFornecedor());
        viewHolder.cpfCnpj.setText(notaFiscal.getCpfCnpj());
        viewHolder.numeroDocumento.setText(notaFiscal.getNumeroDocumento());
        viewHolder.ano.setText(formatarNumero(notaFiscal.getAno()));
        viewHolder.mes.setText(Mes.getDescricaoCurta(notaFiscal.getMes()));
        viewHolder.valor.setText(formatarValor(notaFiscal.getValor()));

        if(notaFiscal.getValorGlosa()==null || notaFiscal.getValorGlosa().intValue()==0) {
            viewHolder.linearValorGlosa.setVisibility(View.GONE);
        } else {
            viewHolder.linearValorGlosa.setVisibility(View.VISIBLE);
            viewHolder.valorGlosa.setText(formatarValor(notaFiscal.getValorGlosa()));
        }

        if(notaFiscal.getValorLiquido()==null || notaFiscal.getValorLiquido().intValue()==0) {
            viewHolder.linearValorLiquido.setVisibility(View.GONE);
        } else {
            viewHolder.linearValorLiquido.setVisibility(View.VISIBLE);
            viewHolder.valorLiquido.setText(formatarValor(notaFiscal.getValorLiquido()));
        }

        VolleySingleton.getInstance(activity).getImageLoader().get(notaFiscal.getParlamentar().getUrlImagem(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response != null && response.getBitmap() != null) {
                    viewHolder.fotoParlamentar.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                limparImagem(activity, viewHolder.fotoParlamentar);
            }
        });

        VolleySingleton.getInstance(activity).getImageLoader().get(notaFiscal.getParlamentar().getPartido().getUrlImagem(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response != null && response.getBitmap() != null) {
                    viewHolder.fotoPartido.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                limparImagem(activity, viewHolder.fotoPartido);
            }
        });

    }

    private void limparImagem(Activity activity, ImageView imageView) {
        imageView.setImageResource(activity.getResources().getColor(R.color.cinza_claro)); // TODO: carregar imagem padr�o
    }

    private String formatarDataEmissao(Date data) {
        String retorno = "";
        if(data!=null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            retorno = sdf.format(data);
        }
        return retorno;
    }

    private String formatarValor(BigDecimal valor) {
        String retorno = "";
        if(valor!=null) {
            valor = valor.setScale(2, BigDecimal.ROUND_DOWN);

            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(2);
            decimalFormat.setMinimumFractionDigits(2);
            decimalFormat.setGroupingUsed(false);

            retorno = MOEDA + decimalFormat.format(valor);
        }
        return retorno;
    }

    private String formatarNumero(Integer numero) {
        String retorno = "";
        if(numero!=null) {
            retorno = String.valueOf(numero);
        }
        return retorno;
    }

    private class ViewHolderNotaFiscal {

        public ImageView fotoParlamentar;
        public ImageView fotoPartido;

        public TextView nomeParlamentar;
        public TextView emailParlamentar;
        public TextView nomePartido;
        public TextView siglaPartido;
        public TextView cota;
        public TextView uf;
        public TextView descricao;
        public TextView dataEmissao;
        public TextView fornecedor;
        public TextView cpfCnpj;
        public TextView numeroDocumento;
        public TextView ano;
        public TextView mes;
        public TextView valor;
        public TextView valorGlosa;
        public TextView valorLiquido;

        public LinearLayout linearValorGlosa;
        public LinearLayout linearValorLiquido;

        public ViewHolderNotaFiscal(ViewGroup viewGroup) {
            fotoParlamentar = (ImageView) viewGroup.findViewById(R.id.image_foto_parlamentar);
            fotoPartido = (ImageView) viewGroup.findViewById(R.id.image_foto_partido);

            nomeParlamentar = (TextView) viewGroup.findViewById(R.id.text_nome_parlamentar);
            emailParlamentar = (TextView) viewGroup.findViewById(R.id.text_email_parlamentar);
            nomePartido = (TextView) viewGroup.findViewById(R.id.text_nome_partido);
            siglaPartido = (TextView) viewGroup.findViewById(R.id.text_sigla_partido);
            cota = (TextView) viewGroup.findViewById(R.id.text_cota);
            uf = (TextView) viewGroup.findViewById(R.id.text_uf);
            descricao = (TextView) viewGroup.findViewById(R.id.text_descricao);
            dataEmissao = (TextView) viewGroup.findViewById(R.id.text_data_emissao);
            fornecedor = (TextView) viewGroup.findViewById(R.id.text_fornecedor);
            cpfCnpj = (TextView) viewGroup.findViewById(R.id.text_cpf_cnpj);
            numeroDocumento = (TextView) viewGroup.findViewById(R.id.text_numero_documento);
            ano = (TextView) viewGroup.findViewById(R.id.text_ano);
            mes = (TextView) viewGroup.findViewById(R.id.text_mes);
            valor = (TextView) viewGroup.findViewById(R.id.text_valor);
            valorGlosa = (TextView) viewGroup.findViewById(R.id.text_valor_glosa);
            valorLiquido = (TextView) viewGroup.findViewById(R.id.text_valor_liquido);

            linearValorGlosa = (LinearLayout) viewGroup.findViewById(R.id.linear_valor_glosa);
            linearValorLiquido = (LinearLayout) viewGroup.findViewById(R.id.linear_valor_liquido);

        }

    }

}