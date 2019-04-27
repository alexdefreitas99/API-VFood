package com.viaflow.api.model;

public class Usuario {

	private int id;
	private String nome;
	private boolean status;
	private Perfil perfil;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario(String nome) {
		super();
		this.nome = nome;
	}

	public Usuario() {
		super();
	}

	@Override
	public String toString() {
		return "ID: " + id + " Nome: " + nome;
	}
}
