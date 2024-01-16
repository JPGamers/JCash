package jp.dev.jcash.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jp.dev.jcash.Cash;

public class CashManager {

	public static Map<UUID, Cash> cash = new HashMap<UUID, Cash>();

	public Map<UUID, Cash> getCash() {
		return cash;
	}

	public void setCash(Map<UUID, Cash> cash) {
		CashManager.cash = cash;
	}
	
	
	
}
