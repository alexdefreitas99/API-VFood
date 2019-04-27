package com.viaflow.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.viaflow.connection.ConnectionMySQL;
import com.viaflow.api.model.Usuario;

public class DaoUsuario implements DaoBase<Usuario> {

	@Override
	public Usuario insert(Usuario object) {
		String query = " insert into usuario (nome,idPerfil)" + " values (?,?)";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.setInt(2, object.getPerfil().getId());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public Usuario update(Usuario object) {
		String query = "update usuario set nome = ?, status = ?, IdPerfil =? where id = ?";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setString(1, object.getNome());
			preparedStmt.setBoolean(2, object.isStatus());
			preparedStmt.setInt(3, object.getPerfil().getId());
			preparedStmt.setInt(4, object.getId());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public Usuario findById(int id) {
		String query = "select * from usuario where id =?";
		Usuario usuario = null;
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = ConnectionMySQL.getConexao().prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setStatus(rs.getBoolean("status"));
				// usuario.setPerfil(DaoSupplier.getDaoPerfil().findById(rs.getInt("IdPerfil")));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public List<Usuario> findAll() {
		String query = "select * from usuario";
		List<Usuario> usuarios = new ArrayList<Usuario>();

		Statement statment = null;
		try {
			statment = ConnectionMySQL.getConexao().createStatement();
			ResultSet rs = statment.executeQuery(query);
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setPerfil(DaoSupplier.getDaoPerfil().findById(rs.getInt("IdPerfil")));
				usuarios.add(usuario);
			}
			statment.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public Usuario disable(int id) {
		String query = "update usuario set status = 0 where id = ?";
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
	public Usuario delete(int id) {
		String query = "DELETE FROM usuario WHERE ID = ? ";
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
