package jp.dev.jcash.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jp.dev.jcash.Cash;
import jp.dev.jcash.Main;
import jp.dev.jcash.manager.CashManager;

public class CashPlayerCommand implements CommandExecutor{

    private final CashManager cashManager;
    private Main pl;

    public CashPlayerCommand(CashManager cashManager, Main pl) {
        this.cashManager = cashManager;
        this.pl = pl;
    }	
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		Player p = (Player)s;
		if (c.getName().equalsIgnoreCase("cash")) {
			if (a.length < 2) {
				Cash cash = cashManager.cash.get(p.getUniqueId());
				if (cash != null) {
					p.sendMessage(pl.getConfig().getString("Mensagens.Cash").replace("&", "§").replace("{cash}", String.valueOf(cash.getMoney())));
				}
			}
		}
		return false;
	}
	
}
