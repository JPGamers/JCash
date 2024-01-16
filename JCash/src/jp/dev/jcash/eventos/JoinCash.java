package jp.dev.jcash.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import jp.dev.jcash.Cash;
import jp.dev.jcash.Main;
import jp.dev.jcash.manager.CashManager;

public class JoinCash implements Listener{

	private final CashManager cashManager;
	private Main pl;
	
	public JoinCash(CashManager cashManager, Main pl) {
		this.cashManager = cashManager;
		this.pl = pl;
	}

	@EventHandler
	public void aoEntrar(PlayerJoinEvent e) {
		Player p = (Player) e.getPlayer();
		Cash cash = Main.db.loadCash(p);
		if (cash == null) {
			cash.setPlayer(p.getUniqueId());
			cash.setMoney(pl.getConfig().getDouble("Cash-Inicial"));
		}
		CashManager.cash.put(p.getUniqueId(), cash);
	}
	
	@EventHandler
	public void aoSair(PlayerQuitEvent e) {
	    Player player = e.getPlayer();
	    Cash cash = Main.db.loadCash(player);
	    if (cash != null) {
	    	Main.db.saveCash(player, cash);
	    }
	}
	
}
