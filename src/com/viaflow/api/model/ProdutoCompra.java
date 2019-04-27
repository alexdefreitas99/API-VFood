package com.viaflow.api.model;

import java.util.ArrayList;
import java.util.List;

public class ProdutoCompra {
	private Compra compra;
	private List<Produto> produtos = new ArrayList<>();
	
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}