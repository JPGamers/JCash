package jp.dev.jcash;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import jp.dev.jcash.comandos.CashPlayerCommand;
import jp.dev.jcash.eventos.JoinCash;
import jp.dev.jcash.manager.CashManager;
import jp.dev.jcash.manager.DBManager;

public class Main extends JavaPlugin{

	private CashManager cManager;
	public static DBManager db;
	
	@Override
	public void onEnable() {
		
		cManager = new CashManager();
		getCommand("cash").setExecutor(new CashPlayerCommand(cManager, this));
		Bukkit.getPluginManager().registerEvents(new JoinCash(cManager, this), this);
		saveDefaultConfig();
		db = new DBManager(getDataFolder() + File.separator + "cash.db");
		db.createTable();
	}
	
	@Override
	public void onDisable() {
		db.closeConnection();
	}
	
}
