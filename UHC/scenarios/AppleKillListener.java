package com.matze.UHC.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.matze.UHC.main.Main;

import net.md_5.bungee.api.ChatColor;

public class AppleKillListener implements Listener{
	private boolean _activated = false;
	
	public boolean isActivated() {
		return _activated;
	}
	public void setActivated(boolean activated) {
		_activated = activated;
	}
	public AppleKillListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		if(_activated) {
			if(event.getEntity().getKiller() != null) {
				Player killer = event.getEntity().getKiller();
				killer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));	
			}
		}
	}
}
