package com.viaflow.dao;

import com.viaflow.api.model.*;

public class DaoSupplier {
	public static DaoBase<Usuario> getDaoUsuario() {
		return new DaoUsuario();
	}

	public static DaoBase<Perfil> getDaoPerfil() {
		return new DaoPerfil();
	}

	public static DaoBase<Produto> getDaoProduto() {
		return new DaoProduto();
	}

	public static DaoBase<Restaurante> getDaoRestaurante() {
		return new DaoRestaurante();
	}

	public static DaoBase<Categoria> getDaoCategoria() {
		return new DaoCategoria();
	}

	public static DaoCompra getDaoCompra() {
		return new DaoCompra();
	}
}
