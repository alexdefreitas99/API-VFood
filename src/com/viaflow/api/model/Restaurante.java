package com.viaflow.api.model;

public class Restaurante {

	private int id;
	private String nome;
	private String telefone;
	private String email;
	private String descricao;
	private int tempoEntrega;
	private boolean status;
	private String horarioAbertura;
	private String horarioFechamento;
	// private List<Produto> produtos = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getTempoEntrega() {
		return tempoEntrega;
	}

	public void setTempoEntrega(int tempoEntrega) {
		this.tempoEntrega = tempoEntrega;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getHorarioAbertura() {
		return horarioAbertura;
	}

	public void setHorarioAbertura(String horarioAbertura) {
		this.horarioAbertura = horarioAbertura;
	}

	public String getHorarioFechamento() {
		return horarioFechamento;
	}

	public void setHorarioFechamento(String horarioFechamento) {
		this.horarioFechamento = horarioFechamento;
	}

	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email
				+ ", descricao=" + descricao + ", tempoEntrega=" + tempoEntrega + ", status=" + status
				+ ", horarioAbertura=" + horarioAbertura + ", horarioFechamento=" + horarioFechamento + "]";
	}
}
