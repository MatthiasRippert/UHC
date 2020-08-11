package com.matze.UHC.scenarios;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.matze.UHC.main.Main;

public class NoFallListener implements Listener{
	private boolean _activated = false;
	
	public boolean isActivated() {
		return _activated;
	}
	public void setActivated(boolean activated) {
		_activated = activated;
	}
	public NoFallListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler 
	public void onEntityDamage(EntityDamageEvent event) {
		if(_activated) {
			if(event.getCause() == DamageCause.FALL) {
				event.setCancelled(true);
			}
		}
	}
}
