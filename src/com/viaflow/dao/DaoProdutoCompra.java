package com.viaflow.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.viaflow.api.model.Produto;
import com.viaflow.api.model.ProdutoCompra;
import com.viaflow.connection.ConnectionMySQL;

public class DaoProdutoCompra implements DaoBase<ProdutoCompra> {

	@Override
	public ProdutoCompra insert(ProdutoCompra object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdutoCompra update(ProdutoCompra object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdutoCompra findById(int id) {

		return null;
	}

	public List<Produto> findProdutosByID(int idCompra) {
		String query = " select * from produtocompra where idCompra = ?";
		List<Produto> produtos = new ArrayList<>();

		PreparedStatement statment = null;
		try {
			statment = ConnectionMySQL.getConexao().prepareStatement(query);
			statment.setInt(1, idCompra);
			ResultSet rs = statment.executeQuery();

			while (rs.next()) {
				Produto prod = DaoSupplier.getDaoProduto().findById(rs.getInt("idProduto"));

				produtos.add(prod);
			}

			statment.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return produtos;
	}

	@Override
	public List<ProdutoCompra> findAll() {
		return null;
	}

	@Override
	public ProdutoCompra disable(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdutoCompra delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
