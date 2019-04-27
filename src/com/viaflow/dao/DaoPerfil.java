package com.viaflow.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.viaflow.connection.ConnectionMySQL;
import com.viaflow.api.model.Perfil;

public class DaoPerfil implements DaoBase<Perfil> {

	@Override
	public Perfil insert(Perfil object) {
		String query = " insert into perfil (nome)" + " values (?)";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public Perfil update(Perfil object) {
		String query = "update perfil set nome = ? where id = ?";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.setInt(2, object.getId());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public Perfil findById(int id) {
		String query = "select * from perfil where id =?";
		Perfil perfil = null;
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				perfil = new Perfil();
				perfil.setId(rs.getInt("id"));
				perfil.setNome(rs.getString("nome"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return perfil;
	}

	@Override
	public List<Perfil> findAll() {
		String query = "select * from perfil";
		List<Perfil> perfils = new ArrayList<Perfil>();

		Statement statment = null;
		try {
			statment = ConnectionMySQL.getConexao().createStatement();
			ResultSet rs = statment.executeQuery(query);
			while (rs.next()) {
				Perfil perfil = new Perfil();
				perfil.setId(rs.getInt("id"));
				perfil.setNome(rs.getString("nome"));
				perfils.add(perfil);
			}
			statment.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return perfils;
	}

	@Override
	public Perfil disable(int id) {
		String query = "update FROM perfil WHERE ID = ? ";
		Perfil perfil = null;
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				perfil = new Perfil();
				perfil.setId(id);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return perfil;

	}

	@Override
	public Perfil delete(int id) {
		String query = "DELETE FROM perfil WHERE ID = ? ";
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