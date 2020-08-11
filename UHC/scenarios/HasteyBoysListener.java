package com.matze.UHC.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import com.matze.UHC.main.Main;

import net.md_5.bungee.api.ChatColor;

public class HasteyBoysListener implements Listener {
	private boolean _activated = false;
	
	public HasteyBoysListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public void setActivated(boolean activated) {
		this._activated = activated;
	}
	public boolean isActivated() {
		return this._activated;
	}
	@EventHandler
	public void Craft(CraftItemEvent event) {
		if(_activated) {
			Material craftedItem = event.getRecipe().getResult().getType();
			if(craftedItem.toString().contains("PICKAXE") ||
					craftedItem.toString().contains("SHOVEL") ||
					craftedItem.toString().contains("AXE")) {
				event.getCurrentItem().addEnchantment(Enchantment.DIG_SPEED, 3);
				event.getCurrentItem().addEnchantment(Enchantment.DURABILITY, 3);
			}
		}
	}
}
