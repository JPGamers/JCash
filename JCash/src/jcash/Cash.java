package jcash;

import java.util.UUID;

public class Cash {

	private double money;
	private UUID player;
	
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	public void addMoney(double money) {
		setMoney(getMoney() + money);
	}
	
	public UUID getPlayer() {
		return player;
	}
	public void setPlayer(UUID player) {
		this.player = player;
	}
}
