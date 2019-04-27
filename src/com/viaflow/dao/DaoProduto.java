package com.viaflow.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.viaflow.connection.*;
import com.viaflow.api.model.Produto;

public class DaoProduto implements DaoBase<Produto> {

	@Override
	public Produto insert(Produto object) {
		String query = " insert into produto (nome,preco,tempoPreparo,IdCategoria,IdRestaurante)"
				+ " values (?,?,?,?,?)";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.setDouble(2, object.getPreco());
			preparedStmt.setDouble(3, object.getTempoPreparo());
			preparedStmt.setInt(4, object.getCategoria().getId());
			preparedStmt.setInt(5, object.getRestaurante().getId());

			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;

	}

	@Override
	public Produto update(Produto object) {
		String query = "update produto set nome = ?, IdCategoria = ?, IdRestaurante = ?, preco = ?, tempoPreparo = ?, status = ? where id = ?";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.setInt(2, object.getCategoria().getId());
			preparedStmt.setInt(3, object.getRestaurante().getId());
			preparedStmt.setDouble(4, object.getPreco());
			preparedStmt.setBoolean(6, object.isStatus());
			preparedStmt.setInt(7, object.getId());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public Produto findById(int id) {
		String query = "select * from produto where id =?";
		Produto produto = null;
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs = preparedStmt.executeQuery();
			if (rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setCategoria(DaoSupplier.getDaoCategoria().findById(rs.getInt("IdCategoria")));
				produto.setRestaurante(DaoSupplier.getDaoRestaurante().findById(rs.getInt("id")));
				produto.setTempoPreparo(rs.getInt("tempoPreparo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produto;
	}

	@Override
	public List<Produto> findAll() {
		String query = "select * from produto";
		List<Produto> produtos = new ArrayList<Produto>();

		Statement statment = null;
		try {
			statment = ConnectionMySQL.getConexao().createStatement();
			ResultSet rs = statment.executeQuery(query);
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setTempoPreparo(rs.getInt("tempoPreparo"));
				produto.setCategoria(DaoSupplier.getDaoCategoria().findById(rs.getInt("IdCategoria")));
				produto.setRestaurante(DaoSupplier.getDaoRestaurante().findById(rs.getInt("IdRestaurante")));
				produtos.add(produto);
			}
			statment.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}

	public List<Produto> getByIdRestaurante(int id) {
		String query = "select * from produto where IdRestaurante = ?";
		List<Produto> produtos = new ArrayList<Produto>();

		PreparedStatement statment = null;
		try {
			statment = ConnectionMySQL.getConexao().prepareStatement(query);
			statment.setInt(1, id);
			ResultSet rs = statment.executeQuery();
			while (rs.next()) {
				Produto produto = findById(rs.getInt("id"));

				produtos.add(produto);
			}
			statment.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}

	@Override
	public Produto disable(int id) {
		String query = "update produto set status = 0 where id = ?";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setInt(1, id);
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Produto> getByIdProduto(int id) {
		String query = "select * from produtocompra where IdCompra = ?";
		List<Produto> produtos = new ArrayList<Produto>();

		PreparedStatement statment = null;
		try {
			statment = ConnectionMySQL.getConexao().prepareStatement(query);
			statment.setInt(1, id);
			ResultSet rs = statment.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("IdProduto"));
//				produto.setNome(rs.getString));
				produtos.add(produto);
			}
			statment.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;

	}

	@Override
	public Produto delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
