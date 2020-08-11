package com.matze.UHC.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.matze.UHC.main.Main;

public class SwitcherooListener implements Listener{
	private boolean _activated = false;
	
	public boolean isActivated() {
		return _activated;
	}
	public void setActivated(boolean activated) {
		_activated = activated;
	}

	public SwitcherooListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(_activated) {
			if(event.getDamager().getType() == EntityType.ARROW || event.getDamager().getType() == EntityType.EGG || event.getDamager().getType() == EntityType.SNOWBALL) {
				
				Projectile shooterProjectile = (Projectile) event.getDamager();
				
				Player shooter = null;
				Player reciever = null;
				
				boolean foundShooter = false;
				boolean foundReciever = false;
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(event.getEntity().getName().equals(p.getName())) {
						reciever = p;
						foundReciever = true;
					}
					else if(shooterProjectile.getShooter().toString().contains(p.getName())) {
						shooter = p;
						foundShooter = true;
					}
					
					if(foundReciever && foundShooter)
						break;
				}
				if(foundReciever && foundShooter) {
					Location locationShooter = shooter.getLocation();
					Location locationReciever = reciever.getLocation();
					
					shooter.teleport(locationReciever);
					shooter.getLocation().setDirection(locationReciever.getDirection());
					
					reciever.teleport(locationShooter);
					reciever.getLocation().setDirection(locationShooter.getDirection());
				}	
			}
		}
	}
}
