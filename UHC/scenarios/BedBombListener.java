package com.matze.UHC.scenarios;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerBedEnterEvent;

import com.matze.UHC.main.Main;

public class BedBombListener implements Listener{
	private boolean _activated = false;
	
	public boolean isActivated() {
		return _activated;
	}
	public void setActivated(boolean activated) {
		_activated = activated;
	}
	public BedBombListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent event) {
		if(_activated) {
			Player p = event.getPlayer();
			Location loc = p.getLocation();
			
			Bukkit.getWorld(p.getWorld().getName()).createExplosion(loc, 4);
			for(Entity e : p.getNearbyEntities(10, 10, 10)) {
				if(e.getType() != EntityType.PLAYER)
					e.remove();
			}
		}
	}
}
