package com.matze.UHC.scenarios;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class UHCCommands implements CommandExecutor{
	private HashMap<Player, Inventory> backpackHashMap = new HashMap<Player, Inventory>();
	private HashMap<Player, Inventory> craftingTables = new HashMap<Player, Inventory>();
	private boolean _activatedBackpack = false;
	private boolean _activatedWorkbench = false;
	
	public boolean isActivatedBackpack() {
		return _activatedBackpack;
	}
	public void setActivatedBackpack(boolean activated) {
		_activatedBackpack = activated;
	}
	
	public boolean isActivatedWorkbench() {
		return _activatedWorkbench;
	}
	public void setActivatedWorkbench(boolean activatedWorkbench) {
		this._activatedWorkbench = activatedWorkbench;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("backpack") || cmd.getName().equalsIgnoreCase("bp")) {
			if(_activatedBackpack) {
				Player p = (Player) sender;
				p.openInventory(backpackHashMap.get(p));
			}
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("workbench") || cmd.getName().equalsIgnoreCase("wb")) {
			if(_activatedWorkbench) {
				Player p = (Player) sender;
				p.openWorkbench(null, true);
			}
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("gm")) {
			Player p = (Player) sender;
			if(args[0].equals("1")) {
				p.setGameMode(GameMode.CREATIVE);
			}else {
				p.setGameMode(GameMode.SURVIVAL);
			}
			return true;
		}
		return false;
	}
	public void fillBackpackHashMap() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			backpackHashMap.put(p, Bukkit.createInventory(p, 18, ChatColor.BLUE + " " + ChatColor.BOLD + "Backpack"));
		}
	}
}