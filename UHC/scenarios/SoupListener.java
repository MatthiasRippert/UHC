package com.matze.UHC.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.matze.UHC.main.Main;

public class SoupListener implements Listener{
	private boolean _activated = false;
	
	public boolean isActivated() {
		return _activated;
	}
	public void setActivated(boolean activated) {
		_activated = activated;
	}
	public SoupListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(_activated) {
			Player p = event.getPlayer();
			if(p.getInventory().getItemInMainHand().equals(new ItemStack(Material.MUSHROOM_STEW, 1))) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					p.getInventory().getItemInMainHand().setType(Material.BOWL);
					p.setHealth(event.getPlayer().getHealth() + 4);
				}
			}
		}
	}
}
