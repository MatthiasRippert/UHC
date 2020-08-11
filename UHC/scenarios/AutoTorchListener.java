package com.matze.UHC.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Torch;

import com.matze.UHC.main.Main;

import net.md_5.bungee.api.ChatColor;

public class AutoTorchListener implements Listener {
	private boolean _activated = false;
	
	public AutoTorchListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public boolean isActivated() {
		return _activated;
	}
	public void setActivated(boolean activated) {
		_activated = activated;
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if(_activated) {
			Player p = event.getPlayer();
			Block b = p.getLocation().getBlock();
			String lightLevelFromSky = Byte.toString(b.getLightFromSky());
			String lightLevel = Byte.toString(b.getLightLevel());
			if(Integer.parseInt(lightLevelFromSky) < 5 && Integer.parseInt(lightLevel) < 5) {
				if(p.getInventory().contains(Material.TORCH)) {
					if(p.getLocation().getPitch() >= 70.0 || p.getLocation().getPitch() <= -70) {
						b.setType(Material.WALL_TORCH);
					}else {
						if(p.getWorld().getBlockAt(new Location(p.getWorld(), b.getX(), b.getY()-1, b.getZ())).getType() == Material.AIR) {
							Block b1 = p.getWorld().getBlockAt(new Location(p.getWorld(), b.getX(), b.getY()-1, b.getZ()));
							b1.setType(Material.TORCH);
						}else {
							b.setType(Material.TORCH);	
						}
					}
					p.getInventory().removeItem(new ItemStack(Material.TORCH, 1));
				}
			}
		}
	}
}
