package jcash;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import comandos.CashPlayerCommand;
import eventos.JoinCash;
import manager.CashManager;
import manager.DBManager;

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
