package com.matze.UHC.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.matze.UHC.main.Main;

public class StarterListener implements Listener{
	private boolean _activated = false;
	
	public boolean isActivated() {
		return _activated;
	}
	public void setActivated(boolean activated) {
		_activated = activated;
	}

	public StarterListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	public void giveStartEquipment() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS, 1));
			p.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
			p.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
			p.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
			
			p.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
			p.getInventory().addItem(new ItemStack(Material.IRON_SHOVEL, 1));
			p.getInventory().addItem(new ItemStack(Material.IRON_AXE, 1));
			p.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE, 1));
		}
	}
}
