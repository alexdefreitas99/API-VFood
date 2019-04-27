package com.viaflow.api.model;

public class Produto {
	private int id;
	private String nome;
	private double preco;
	private int tempoPreparo;
	private Categoria categoria;
	private Restaurante Restaurante;
	private boolean status;
	
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
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getTempoPreparo() {
		return tempoPreparo;
	}
	public void setTempoPreparo(int tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Restaurante getRestaurante() {
		return Restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		Restaurante = restaurante;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", tempoPreparo=" + tempoPreparo
				+ ", categoria=" + categoria + ", Restaurante=" + Restaurante + ", status=" + status + "]";
	}
}
