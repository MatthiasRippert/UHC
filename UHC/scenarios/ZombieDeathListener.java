package com.matze.UHC.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.matze.UHC.main.Main;

public class ZombieDeathListener implements Listener{
	private boolean _activated = false;
	
	public boolean isActivated() {
		return _activated;
	}
	public void setActivated(boolean activated) {
		_activated = activated;
	}
	public ZombieDeathListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if(_activated) {
			Location loc = e.getEntity().getLocation();
			Zombie z = (Zombie) Bukkit.getWorld(e.getEntity().getWorld().getName()).spawnEntity(loc, EntityType.ZOMBIE);
			z.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1));
			z.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
			z.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
			z.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
		}
	}
}
