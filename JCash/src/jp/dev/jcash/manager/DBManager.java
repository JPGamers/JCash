package jp.dev.jcash.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import jp.dev.jcash.Cash;

public class DBManager {

	private Connection connection;

	public DBManager(String dbName) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void createTable() {
		try {
			PreparedStatement statement = connection
					.prepareStatement("CREATE TABLE IF NOT EXISTS jogador_cash (player_uuid TEXT, cash DOUBLE)");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveCash(Player player, Cash cash) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("INSERT OR REPLACE INTO jogador_cash (player_uuid, cash) VALUES (?, ?)");
			statement.setString(1, player.getUniqueId().toString());
			statement.setDouble(2, cash.getMoney());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Cash loadCash(Player player) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM jogador_cash WHERE player_uuid = ?");
			statement.setString(1, player.getUniqueId().toString());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Cash cash = new Cash();
				cash.setMoney(resultSet.getDouble("cash"));
				CashManager.cash.put(player.getUniqueId(), cash);
				return cash;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
