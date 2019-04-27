package com.viaflow.api.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class Compra {

	private int id;
	private Usuario usuario;
	private List<Produto> produtos = new ArrayList<>();

	@JsonSerialize(include = Inclusion.NON_NULL)
	private Restaurante restaurante;
	private boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getValorTotal() {
		double vl = 0;
		for (Produto produto : produtos) {
			vl += produto.getPreco();
		}
		return vl;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", usuario=" + usuario + ", produtos=" + produtos + ", restaurante=" + restaurante
				+ ", status=" + status + "]";
	}
}
