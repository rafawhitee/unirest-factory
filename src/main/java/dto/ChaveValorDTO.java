package dto;

import java.io.Serializable;
import java.util.Objects;

public class ChaveValorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String chave;
	private Object valor;

	public ChaveValorDTO(String chave, Object valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chave, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChaveValorDTO other = (ChaveValorDTO) obj;
		return Objects.equals(chave, other.getChave()) && Objects.equals(valor, other.getValor());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Chave: " + chave);
		sb.append(" - ");
		sb.append("Valor: " + valor);
		return sb.toString();
	}

}