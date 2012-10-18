package br.ufla.dcc.siscob.model.domain.entity;

public class Mensagem {

	private TipoMenssagem tipoMenssagem;
	private String texto;
	
	public Mensagem(TipoMenssagem tipoMenssagem, String texto) {
		this.tipoMenssagem = tipoMenssagem;
		this.texto = texto;
	}
	
	public TipoMenssagem getTipoMenssagem() {
		return tipoMenssagem;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public boolean sucesso() {
		
		if(this.tipoMenssagem == TipoMenssagem.SUCESSO)
			return true;
		
		return false;
	}
}
