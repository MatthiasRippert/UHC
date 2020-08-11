package com.matze.UHC.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.matze.UHC.main.Main;

import net.md_5.bungee.api.ChatColor;

public class HungerlessListener implements Listener{
	private boolean _activated = false;
	
	public boolean isActivated() {
		return _activated;
	}
	public void setActivated(boolean activated) {
		_activated = activated;
	}
	public HungerlessListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent event) {
		if(_activated) {
			Player p = (Player) event.getEntity();
			p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20, 1));
		}
	}
}
