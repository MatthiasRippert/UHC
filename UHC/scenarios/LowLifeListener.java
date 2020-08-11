package com.matze.UHC.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.matze.UHC.main.Main;

public class LowLifeListener implements Listener{
	private Main _plugin;
	private boolean _activated = false;
	
	public boolean isActivated() {
		return _activated;
	}
	public void setActivated(boolean activated) {
		_activated = activated;
	}
	public LowLifeListener(Main plugin) {
		this._plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public void startLowLife() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setHealth(1);
			p.getInventory().addItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1));
		}
	}
	public void endLowLife() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setHealth(20);
			if(p.getInventory().contains(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1))) {
				p.getInventory().remove(Material.ENCHANTED_GOLDEN_APPLE);
			}
		}
	}
}
