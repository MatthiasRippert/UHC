package com.matze.UHC.scenarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import com.matze.UHC.main.Main;

import net.md_5.bungee.api.ChatColor;

public class AutoSmeltListener implements Listener{
	private boolean _activated = false;
	
	public AutoSmeltListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	public void setActivated(boolean activated) {
		this._activated = activated;
	}
	public boolean isActivated() {
		return this._activated;
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(_activated) {
			if(event.getBlock().getType().toString().contains("ORE"))
				this._autoSmelt(event.getBlock().getDrops(), event.getPlayer());	
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if(_activated)
			this._autoSmelt(event.getDrops(), event.getEntity().getKiller());
	}
	public void _autoSmelt(Collection<ItemStack> drops, Player p) {
		ItemStack result;
		Iterator<Recipe> iter;
		Recipe recipe;
		boolean found = false;
		for(ItemStack i : drops) {
			iter = Bukkit.recipeIterator();
			while(iter.hasNext()) {
				recipe = iter.next();
				if(recipe instanceof FurnaceRecipe) {
					if(((FurnaceRecipe) recipe).getInput().getType() == i.getType()) {
						result = recipe.getResult();
						p.getInventory().addItem(new ItemStack(result.getType(), i.getAmount()));
						found = true;
						i.setType(Material.AIR);
						break;
					}
				}
			}
			if(!found) {
				p.getInventory().addItem(new ItemStack(i.getType(), i.getAmount()));
				i.setType(Material.AIR);
			}
		}
	}
}
