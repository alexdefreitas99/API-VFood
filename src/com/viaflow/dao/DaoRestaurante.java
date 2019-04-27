package com.viaflow.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.viaflow.connection.ConnectionMySQL;
import com.viaflow.api.model.Restaurante;

public class DaoRestaurante implements DaoBase<Restaurante> {

	@Override
	public Restaurante insert(Restaurante object) {
		String query = " insert into restaurante (nome,telefone,email,descricao,tempoEntrega,horarioAbertura,horarioFechamento)"
				+ " values (?,?,?,?,?,?,?)";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.setString(2, object.getTelefone());
			preparedStmt.setString(3, object.getEmail());
			preparedStmt.setString(4, object.getDescricao());
			preparedStmt.setInt(5, object.getTempoEntrega());
			preparedStmt.setString(6, object.getHorarioAbertura());
			preparedStmt.setString(7, object.getHorarioFechamento());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public Restaurante update(Restaurante object) {
		String query = "update restaurante set nome = ?, telefone = ?, email =?, descricao = ?, tempoEntrega = ?,horarioAbertura = ?,horarioFechamento = ? where id = ?";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.setString(2, object.getTelefone());
			preparedStmt.setString(3, object.getEmail());
			preparedStmt.setString(4, object.getDescricao());
			preparedStmt.setInt(5, object.getTempoEntrega());
			preparedStmt.setString(6, object.getHorarioAbertura());
			preparedStmt.setString(7, object.getHorarioFechamento());
			preparedStmt.setInt(8, object.getId());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public Restaurante findById(int id) {
		String query = "select * from restaurante where id =?";
		Restaurante restaurante = null;
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				restaurante = new Restaurante();
				restaurante.setId(rs.getInt("id"));
				restaurante.setNome(rs.getString("nome"));
				restaurante.setTempoEntrega(rs.getInt("tempoEntrega"));
				restaurante.setDescricao(rs.getString("descricao"));
//				restaurante.setProdutos(new DaoProduto().getByIdRestaurante(restaurante.getId()));
				restaurante.setTelefone(rs.getString("telefone"));
				restaurante.setEmail(rs.getString("email"));
				restaurante.setHorarioAbertura(rs.getString("horarioAbertura"));
				restaurante.setHorarioFechamento(rs.getString("horarioFechamento"));

				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurante;
	}

	@Override
	public List<Restaurante> findAll() {
		String query = "select * from restaurante";
		List<Restaurante> restaurantes = new ArrayList<Restaurante>();

		Statement statment = null;
		try {
			statment = ConnectionMySQL.getConexao().createStatement();
			ResultSet rs = statment.executeQuery(query);
			while (rs.next()) {
				Restaurante restaurante = new Restaurante();
				restaurante.setId(rs.getInt("id"));
				restaurante.setNome(rs.getString("nome"));
				restaurante.setTempoEntrega(rs.getInt("tempoEntrega"));
				restaurante.setDescricao(rs.getString("descricao"));
//				restaurante.setProdutos(new DaoProduto().getByIdRestaurante(restaurante.getId()));
				restaurante.setTelefone(rs.getString("telefone"));
				restaurante.setEmail(rs.getString("email"));
				restaurante.setHorarioAbertura(rs.getString("horarioAbertura"));
				restaurante.setHorarioFechamento(rs.getString("horarioFechamento"));
				restaurantes.add(restaurante);
			}
			statment.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurantes;
	}

	@Override
	public Restaurante disable(int id) {
		String query = "update restaurante set status = 0 where id = ?";
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
	public Restaurante delete(int id) {
		String query = "DELETE FROM restaurante WHERE ID = ? ";
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

}
