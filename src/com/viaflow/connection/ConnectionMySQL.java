package com.viaflow.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {

	private static Connection conexao;

	private ConnectionMySQL() {
		conexao = getConexaoMySQL();
	}

	@SuppressWarnings("unused")
	private static ConnectionMySQL connMySQL = new ConnectionMySQL();

	public static Connection getConexao() {
		return conexao;
	}

	private java.sql.Connection getConexaoMySQL() {
		Connection connection = null;
		try {
			String serverName = "localhost:3306";
			String mydatabase = "vFood";
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?useTimezone=true&serverTimezone=UTC";
			String username = "root";
			String password = "root";
			connection = DriverManager.getConnection(url, username, password);
			if (connection != null) {
				System.out.println("STATUS--->Conectado com sucesso!");
			} else {
				System.out.println("STATUS--->Não foi possivel realizar conexão");
			}
			return connection;

		} catch (SQLException e) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados." + e.getMessage());
			return null;
		}
	}

	private static boolean FecharConexao() {
		try {
			conexao.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@SuppressWarnings("unused")
	private java.sql.Connection ReiniciarConexao() {
		FecharConexao();
		return getConexaoMySQL();
	}
}
