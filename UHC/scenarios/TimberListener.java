package com.matze.UHC.scenarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.matze.UHC.main.Main;

import net.md_5.bungee.api.ChatColor;

public class TimberListener implements Listener{
	
	private World w;
	private Material m;
	private Block destroyedBlock;
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private boolean activated = false;
	
	
	public TimberListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	public boolean isActivated() {
		return this.activated;
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(this.activated) {
			if(event.getBlock().getType().toString().contains("LOG")) {
				blocks = new ArrayList<Block>();
				
				w = event.getPlayer().getWorld();
				this.m = event.getBlock().getType();
				destroyedBlock = event.getBlock();
	
				event.setDropItems(false);
				blocks.add(destroyedBlock);
				
				this._getNearbyLogs(new Location(w, destroyedBlock.getX(), destroyedBlock.getY(), destroyedBlock.getZ()), 7);
				int destroyedLogs = this._destroyConnectedBlocks();
				ItemStack droppedLog = new ItemStack(m, destroyedLogs);
				event.getPlayer().getInventory().addItem(droppedLog);
			}
		}
	}
	
	private void _getNearbyLogs(Location l, int radius) {
		int height = 25;
		for(int x = l.getBlockX() - radius; x <= l.getBlockX() + radius; x++) {
			for(int y = l.getBlockY(); y <= l.getBlockY() + height; y++) {
				for(int z = l.getBlockZ() - radius; z < l.getBlockZ() + radius; z++) {
					if(l.getWorld().getBlockAt(x, y, z).getType() == m) {
						if(x == destroyedBlock.getX() && y == destroyedBlock.getY() && z == destroyedBlock.getZ()) {
						}
						else {
							blocks.add(l.getWorld().getBlockAt(x, y, z));	
						}
					}
				}
			}
		}
	}
	
	private int _destroyConnectedBlocks() {
		ArrayList<Block> connectedBlocks = new ArrayList<>();
		ArrayList<Block> notConnectedBlocks = new ArrayList<>();
		ArrayList<Block> blocksOnSameHeight = new ArrayList<>();
		int i = 0;
		int height;
		
		connectedBlocks.add(blocks.get(0));
		blocks.remove(0);
		
		if(blocks.size() > 0) {
			Collections.sort(blocks, new Comparator<Block>() {
				@Override
				public int compare(Block b1, Block b2) {
					Integer b1Y = b1.getY();
					Integer b2Y = b2.getY();
					return b1Y.compareTo(b2Y);
				}
			});
			height = blocks.get(0).getY();
			while(i < blocks.size()) {
				while(i < blocks.size() && height == blocks.get(i).getY()) {
					blocksOnSameHeight.add(blocks.get(i));
					i++;	
				}
				
				if(i < blocks.size())
					height = blocks.get(i).getY();
				
				for(int j = 0; j < blocksOnSameHeight.size(); j++) {
					for(int k = 0; k < connectedBlocks.size(); k++) {
						if(blocksOnSameHeight.get(j).getX() == connectedBlocks.get(k).getX() || blocksOnSameHeight.get(j).getX() - connectedBlocks.get(k).getX() < 2 && blocksOnSameHeight.get(j).getX() -  connectedBlocks.get(k).getX() > -2) {
							if(blocksOnSameHeight.get(j).getY() == connectedBlocks.get(k).getY() || blocksOnSameHeight.get(j).getY() - connectedBlocks.get(k).getY() < 2 && blocksOnSameHeight.get(j).getY() - connectedBlocks.get(k).getY() > -2) {
								if(blocksOnSameHeight.get(j).getZ() == connectedBlocks.get(k).getZ() || blocksOnSameHeight.get(j).getZ() - connectedBlocks.get(k).getZ() < 2 && blocksOnSameHeight.get(j).getZ() - connectedBlocks.get(k).getZ() > -2) {
									connectedBlocks.add(blocksOnSameHeight.get(j));
									break;
								}
							}
						}
					}
					if(!connectedBlocks.contains(blocksOnSameHeight.get(j)))
						notConnectedBlocks.add(blocksOnSameHeight.get(j));
				}
				
				blocksOnSameHeight.clear();
			}
			
			if(notConnectedBlocks.size() > 0) {
				for(int j = notConnectedBlocks.size()-1; j >= 0; j--) {
					for(int k = 0; k < connectedBlocks.size(); k++) {
						if(notConnectedBlocks.get(j).getX() == connectedBlocks.get(k).getX() || notConnectedBlocks.get(j).getX() - connectedBlocks.get(k).getX() < 2 && notConnectedBlocks.get(j).getX() -  connectedBlocks.get(k).getX() > -2) {
							if(notConnectedBlocks.get(j).getY() == connectedBlocks.get(k).getY() || notConnectedBlocks.get(j).getY() - connectedBlocks.get(k).getY() < 2 && notConnectedBlocks.get(j).getY() - connectedBlocks.get(k).getY() > -2) {
								if(notConnectedBlocks.get(j).getZ() == connectedBlocks.get(k).getZ() || notConnectedBlocks.get(j).getZ() - connectedBlocks.get(k).getZ() < 2 && notConnectedBlocks.get(j).getZ() - connectedBlocks.get(k).getZ() > -2) {
									connectedBlocks.add(notConnectedBlocks.get(j));
									break;
								}
							}
						}
					}
				}	
			}
			
			for(Block b : connectedBlocks) {
				b.setType(Material.AIR);
			}
		}
		return connectedBlocks.size();
	}
}
