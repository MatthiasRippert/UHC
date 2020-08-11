package com.matze.UHC.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Chest.Type;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;


import com.matze.UHC.main.Main;

import net.md_5.bungee.api.ChatColor;

public class TimeBombListener implements Listener{
	private Player _p;
	private Main _plugin;
	private ArmorStand _as;
	private int _threadId;
	private int _timer = 30;
	private Location _loc;
	private boolean _activated = false;
	
	public boolean isActivated() {
		return _activated;
	}
	public void setActivated(boolean activated) {
		_activated = activated;
	}
	
	public TimeBombListener(Main plugin) {
		this._plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if(_activated) {
			_timer = 30;
			event.getDrops().clear();
			_p = event.getEntity();
			_loc = _p.getLocation();
			Location l = _p.getLocation();
			Location l2 = new Location(_p.getWorld(), _p.getLocation().getX()+1, _p.getLocation().getY(), _p.getLocation().getZ());
			
			org.bukkit.block.data.type.Chest chestData1;
			org.bukkit.block.data.type.Chest chestData2;
			
			l.getBlock().setType(Material.CHEST);
			l2.getBlock().setType(Material.CHEST);
			
			Chest chest1 = (Chest)l.getBlock().getState();
			Chest chest2 = (Chest)l2.getBlock().getState();
			
			chestData1 = (org.bukkit.block.data.type.Chest) chest1.getBlockData();
			chestData2 = (org.bukkit.block.data.type.Chest) chest2.getBlockData();
			
			chestData1.setType(Type.LEFT);
			l.getBlock().setBlockData(chestData1, true);
			chestData2.setType(Type.RIGHT);
			l2.getBlock().setBlockData(chestData2, true);
			
			
			for(ItemStack i : _p.getInventory().getContents()) {
				if(i != null) {
					if(chest1.getBlockInventory().getSize() < 27)
						chest1.getBlockInventory().addItem(i);
					else {
						chest2.getBlockInventory().addItem(i);
					}
				}
			}
	
			_as = _createHolo(new Location(_p.getWorld(), l.getX()+0.5, l.getY()-1, l.getZ()));
			_threadId = Bukkit.getScheduler().scheduleSyncRepeatingTask(_plugin, new Runnable() {
				public void run() {
					if(_timer >= 10)
						_as.setCustomName(ChatColor.GREEN + "00:" + _timer);
					else {
						_as.setCustomName(ChatColor.RED + "00:0" + _timer);	
					}
					if(_timer == 0) {
						Bukkit.getScheduler().cancelTask(_threadId);
						for(ItemStack i : chest1.getInventory().getContents()) {
							if(i != null)
								chest1.getInventory().removeItem(i);
						}
						for(ItemStack i : chest2.getInventory().getContents()) {
							if(i != null)
								chest2.getInventory().removeItem(i);
						}
						Bukkit.getWorld(_p.getWorld().getName()).createExplosion(_loc, 4);
						_as.remove();
					}
					_timer--;
				}
			}, 20, 20);
			
		}
	}
	private ArmorStand _createHolo(Location loc) {
		ArmorStand as = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		as.setGravity(false);
		as.setCanPickupItems(false);
		as.setCustomNameVisible(true);
		as.setVisible(false);
		
		return as;
	}
}
