package com.viaflow.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.viaflow.api.model.*;
import com.viaflow.connection.ConnectionMySQL;

public class DaoCompra implements DaoBase<Compra> {

	private int getLastIndex() {
		String query = " select max(ID) from compra";
		Statement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().createStatement();
			ResultSet rs = preparedStmt.executeQuery(query);
			rs.next();
			int index = rs.getInt(1);
			preparedStmt.close();
			return index;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public double totalCompra(double total) {
		String query = "select sum(preco) as total from produtocompra INNER JOIN produto \r\n"
				+ "ON PRODUTOCOMPRA.IdProduto = produto.Id where idcompra=?";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setDouble(1, total);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public Compra insert(Compra object) {
		String query = " insert into compra (IdUsuario,IdRestaurante)" + " values (?,?)";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setInt(1, object.getUsuario().getId());
			preparedStmt.setInt(2, object.getRestaurante().getId());
			preparedStmt.execute();
			preparedStmt.close();

			int id = getLastIndex();

			for (Produto item : object.getProdutos()) {
				String queryProd = " insert into produtocompra (IdProduto,IdCompra)" + " values (?,?)";
				try {
					PreparedStatement statementItem = null;
					statementItem = ConnectionMySQL.getConexao().prepareStatement(queryProd);
					statementItem.setInt(1, item.getId());
					statementItem.setInt(2, id);
					statementItem.execute();
					statementItem.close();

				} catch (Exception e) {
					System.out.println("Erro:" + e);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;

	}

	@Override
	public Compra disable(int id) {
		String query = "update compra set status = 0 where id = ?";
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

	@Override
	public Compra findById(int id) {
		String query = "select * from compra where id =?";
		Compra compra = null;
		PreparedStatement preparedStmt = null;

		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				compra = new Compra();
				compra.setId(id);
				compra.setUsuario(DaoSupplier.getDaoUsuario().findById(rs.getInt("idUsuario")));
//					compra.setRestaurante(DaoSupplier.getDaoRestaurante().findById(rs.getInt("idRestaurante")));'
				compra.setProdutos(new DaoProdutoCompra().findProdutosByID(id));
				break;
			}
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compra;

	}

	@Override
	public List<Compra> findAll() {
		String query = "select * from compra";
		List<Compra> compras = new ArrayList<Compra>();

		Statement statment = null;
		try {
			statment = ConnectionMySQL.getConexao().createStatement();
			ResultSet rs = statment.executeQuery(query);
			while (rs.next()) {
				Compra compra = new Compra();
				compra.setId(rs.getInt("id"));
				compra.setUsuario(DaoSupplier.getDaoUsuario().findById(rs.getInt("IdUsuario")));
//					compra.setRestaurante(DaoSupplier.getDaoRestaurante().findById(rs.getInt("id")));
//					compra.setStatus(rs.getBoolean("status"));

				compra.setProdutos(new DaoProdutoCompra().findProdutosByID(rs.getInt("id")));
				compras.add(compra);
			}
			statment.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compras;
	}

	public int countById(int idRest) {
		String query = "SELECT COUNT(IdRestaurante) FROM compra WHERE IdRestaurante=?";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setInt(1, idRest);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Usuario> countbyIdUser() {
		String query = "SELECT DISTINCT idusuario FROM compra";
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Statement statment = null;
		try {
			statment = ConnectionMySQL.getConexao().createStatement();
			ResultSet rs = statment.executeQuery(query);
			while (rs.next()) {
				Usuario user = DaoSupplier.getDaoUsuario().findById(rs.getInt("IdUsuario"));
				usuarios.add(user);
			}
			statment.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;

	}

	public List<Produto> countByIdProd() {
		// String query = "SELECT DISTINCT IdProduto FROM produtocompra ORDER BY
		// IdProduto DESC LIMIT 3";
		String query = "Select idproduto , count(*) FROM produtocompra Group by idproduto Order by count(*) desc";
		List<Produto> produtos = new ArrayList<Produto>();
		Statement statment = null;
		try {
			statment = ConnectionMySQL.getConexao().createStatement();
			ResultSet rs = statment.executeQuery(query);
			while (rs.next()) {
				Produto prod = DaoSupplier.getDaoProduto().findById(rs.getInt("IdProduto"));
				produtos.add(prod);
			}
			statment.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}
	
	@Override
	public Compra delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compra update(Compra object) {
		// TODO Auto-generated method stub
		return null;
	}
}
