package com.matze.UHC.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.matze.UHC.main.Main;

import net.md_5.bungee.api.ChatColor;

public class HealingKillListener implements Listener{
	private boolean _activated = false;
	
	public boolean isActivated() {
		return _activated;
	}
	public void setActivated(boolean activated) {
		_activated = activated;
	}
	public HealingKillListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if(_activated) {
			if(event.getEntity().getKiller() != null) {
				Player killer = event.getEntity().getKiller();
				killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1), true);
			}
		}
	}
}
