package br.net.ops.fiscalize.pojo;


public class PedidoNota {

	private String tokenId;
	private int partidoId;
	private int parlamentarId;

	private int quantidade;
	
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public int getPartidoId() {
		return partidoId;
	}
	public void setPartidoId(int partidoId) {
		this.partidoId = partidoId;
	}
	public int getParlamentarId() {
		return parlamentarId;
	}
	public void setParlamentarId(int parlamentarId) {
		this.parlamentarId = parlamentarId;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
