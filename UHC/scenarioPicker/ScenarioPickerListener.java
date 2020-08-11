package com.matze.UHC.scenarioPicker;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.fusesource.jansi.Ansi.Color;

import com.matze.UHC.main.Main;
import com.matze.UHC.scenarios.AppleKillListener;
import com.matze.UHC.scenarios.AutoSmeltListener;
import com.matze.UHC.scenarios.AutoTorchListener;
import com.matze.UHC.scenarios.BedBombListener;
import com.matze.UHC.scenarios.HasteyBoysListener;
import com.matze.UHC.scenarios.HealingKillListener;
import com.matze.UHC.scenarios.HungerlessListener;
import com.matze.UHC.scenarios.LowLifeListener;
import com.matze.UHC.scenarios.NoFallListener;
import com.matze.UHC.scenarios.SniperListener;
import com.matze.UHC.scenarios.SoupListener;
import com.matze.UHC.scenarios.StarterListener;
import com.matze.UHC.scenarios.SwitcherooListener;
import com.matze.UHC.scenarios.TimberListener;
import com.matze.UHC.scenarios.TimeBombListener;
import com.matze.UHC.scenarios.UHCCommands;
import com.matze.UHC.scenarios.ZombieDeathListener;

import net.md_5.bungee.api.ChatColor;

public class ScenarioPickerListener implements Listener{
	private Inventory _scenarioPickerInv = Bukkit.createInventory(null, 45, ChatColor.RED + " " + ChatColor.BOLD + "Scenarios");
	private Main _plugin;
	
	private AppleKillListener _appleKillListener;
	private AutoSmeltListener _autoSmeltListener;
	private AutoTorchListener _autoTorchListener;
	private BedBombListener _bedBombListener;
	private HasteyBoysListener _hasteBoysListener;
	private HealingKillListener _healingKillListener;
	private HungerlessListener _hungerlessListener;
	private LowLifeListener _lowLifeListener;
	private NoFallListener _noFallListener;
	private SniperListener _sniperListener;
	private SoupListener _soupListener;
	private StarterListener _starterListener;
	private SwitcherooListener _switcherooListener;
	private TimberListener _timberListener;
	private TimeBombListener _timeBombListener;
	private UHCCommands _uhcCommands;
	private ZombieDeathListener _zombieDeathListener;
	
	public ScenarioPickerListener(Main plugin, UHCCommands commands) {
		this._appleKillListener = new AppleKillListener(plugin);
		this._autoSmeltListener = new AutoSmeltListener(plugin);
		this._autoTorchListener = new AutoTorchListener(plugin);
		this._bedBombListener = new BedBombListener(plugin);
		this._hasteBoysListener = new HasteyBoysListener(plugin);
		this._healingKillListener = new HealingKillListener(plugin);
		this._hungerlessListener = new HungerlessListener(plugin);
		this._lowLifeListener = new LowLifeListener(plugin);
		this._noFallListener = new NoFallListener(plugin);
		this._sniperListener = new SniperListener(plugin);
		this._soupListener = new SoupListener(plugin);
		this._starterListener = new StarterListener(plugin);
		this._switcherooListener = new SwitcherooListener(plugin);
		this._timberListener = new TimberListener(plugin);
		this._timeBombListener = new TimeBombListener(plugin);
		this._uhcCommands = commands;
		this._zombieDeathListener = new ZombieDeathListener(plugin);
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this._plugin = plugin;
	}
	
	@EventHandler
	public void openScenarioPicker(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHER_STAR) {
				this._fillScenerioPicker();
				event.getPlayer().openInventory(this._scenarioPickerInv);
			}
		}
	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(event.getInventory().equals(this._scenarioPickerInv)) {
			event.setCancelled(true);
			int pressedSlot = event.getRawSlot();
			if(pressedSlot == 9) {
				this._appleKillListener.setActivated(!this._appleKillListener.isActivated());
				this._activateOrDeactivateScenario("Apple Kill" , this._appleKillListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 10) {
				this._autoSmeltListener.setActivated(!this._autoSmeltListener.isActivated());
				this._activateOrDeactivateScenario("Auto Smelt" , this._autoSmeltListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 11) {
				this._autoTorchListener.setActivated(!this._autoTorchListener.isActivated());
				this._activateOrDeactivateScenario("Auto Torch" , this._autoTorchListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 12) {
				this._uhcCommands.setActivatedBackpack(!this._uhcCommands.isActivatedBackpack());
				this._activateOrDeactivateScenario("Backpack" , this._uhcCommands.isActivatedBackpack(), pressedSlot);
			}
			else if(pressedSlot == 13) {
				this._bedBombListener.setActivated(!this._bedBombListener.isActivated());
				this._activateOrDeactivateScenario("Bed Bomb" , this._bedBombListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 14) {
				this._hasteBoysListener.setActivated(!this._hasteBoysListener.isActivated());
				this._activateOrDeactivateScenario("Hastey Boys" , this._hasteBoysListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 15) {
				this._hungerlessListener.setActivated(!this._hungerlessListener.isActivated());
				this._activateOrDeactivateScenario("Hungerless" , this._hungerlessListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 16) {
				this._lowLifeListener.setActivated(!this._lowLifeListener.isActivated());
				this._activateOrDeactivateScenario("Low Life" , this._lowLifeListener.isActivated(), pressedSlot);
				if(this._lowLifeListener.isActivated()) {
					this._lowLifeListener.startLowLife();
				}else {
					this._lowLifeListener.endLowLife();
				}
			}
			else if(pressedSlot == 17) {
				this._uhcCommands.setActivatedWorkbench(!this._uhcCommands.isActivatedWorkbench());
				this._activateOrDeactivateScenario("Mobile Workbench" , this._uhcCommands.isActivatedWorkbench(), pressedSlot);
			}
			else if(pressedSlot == 36) {
				this._noFallListener.setActivated(!this._noFallListener.isActivated());
				this._activateOrDeactivateScenario("No Fall" , this._noFallListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 37) {
				this._healingKillListener.setActivated(!this._healingKillListener.isActivated());
				this._activateOrDeactivateScenario("Regeneration Kill" , this._healingKillListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 38) {
				this._sniperListener.setActivated(!this._sniperListener.isActivated());
				this._activateOrDeactivateScenario("Sniper" , this._sniperListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 39) {
				this._soupListener.setActivated(!this._soupListener.isActivated());
				this._activateOrDeactivateScenario("Soup" , this._soupListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 40) {
				this._starterListener.setActivated(!this._starterListener.isActivated());
				if(this._starterListener.isActivated()) {
					this._starterListener.giveStartEquipment();
				}
				this._activateOrDeactivateScenario("Starter" , this._starterListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 41) {
				this._switcherooListener.setActivated(!this._switcherooListener.isActivated());
				this._activateOrDeactivateScenario("Switcheroo" , this._switcherooListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 42) {
				this._timberListener.setActivated(!this._timberListener.isActivated());
				this._activateOrDeactivateScenario("Timber" , this._timberListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 43) {
				this._timeBombListener.setActivated(!this._timeBombListener.isActivated());
				this._activateOrDeactivateScenario("Time Bomb" , this._timeBombListener.isActivated(), pressedSlot);
			}
			else if(pressedSlot == 44) {
				this._zombieDeathListener.setActivated(!this._zombieDeathListener.isActivated());
				this._activateOrDeactivateScenario("Zombie Death" , this._zombieDeathListener.isActivated(), pressedSlot);
			}
			return;
		}
	}
	
	public void _fillScenerioPicker() {
		for(int i = 0; i < this._scenarioPickerInv.getSize(); i++) {

			this._scenarioPickerInv.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));

			if(i == 0) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.GOLDEN_APPLE, "Apple Kill", 
						ChatColor.GREEN + "Jeder " + ChatColor.WHITE + "Spieler erhält 2 golden Äpfel " + ChatColor.RED + "nach jedem Kill"));	
			}
			else if(i == 1) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.IRON_INGOT, "Auto Smelt",
						ChatColor.GREEN + "Erze " + ChatColor.WHITE + "und " + ChatColor.GREEN + "Essen " + ChatColor.WHITE + "werden bereits gebraten"));	
			}
			else if(i == 2) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.TORCH, "Auto Torch",
						ChatColor.WHITE + "Fackeln werden in dunklen Umgebungen\n" + ChatColor.GREEN + "automatisch " + ChatColor.WHITE + "gestetzt"));
			}
			else if(i == 3) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.CHEST, "Backpack", 
						ChatColor.WHITE + "Ermöglicht das öffnen einen Backpacks \n" + ChatColor.GREEN + "/backpack " + ChatColor.WHITE + "oder " + ChatColor.GREEN + "/bp"));
			}
			else if(i == 4) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.WHITE_BED, "Bed Bomb",
						ChatColor.GREEN + "Betten " + ChatColor.WHITE + "explodieren, \n" + ChatColor.WHITE + "wenn du versuchst, dich heineinzulegen"));
			}
			else if(i == 5) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.DIAMOND_PICKAXE, "Hastey Boys",
						ChatColor.GREEN + "Jedes " + ChatColor.WHITE + "Werkzeug hat Effizienz und Haltbarkeit 3"));	
			}
			else if(i == 6) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.COOKED_BEEF, "Hungerless", 
						ChatColor.WHITE + "Du bist immer gesättigt"));
			}
			else if(i == 7) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.ENCHANTED_GOLDEN_APPLE, "Low Life", 
						ChatColor.GREEN + "Jeder " + ChatColor.WHITE + "Spieler start mit einem " + ChatColor.RED + "halben Herzen " + ChatColor.WHITE + "und " + ChatColor.RED + "einem Notch-Apfel"));
			}
			else if(i == 8) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.CRAFTING_TABLE, "Mobile Workbench",
						ChatColor.WHITE + "Ermöglicht das öffnen einer Werkbank \n" + ChatColor.GREEN + "/workbench " + ChatColor.WHITE + "oder " + ChatColor.GREEN + "/wb"));
			}
			else if(i == 27) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.DIAMOND_BOOTS, "No Fall",
						ChatColor.WHITE + "Es gibt " + ChatColor.GREEN + "keinen " + ChatColor.WHITE + "Fallschaden"));
			}
			else if(i == 28) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.POTION, "Regeneration Kill",
						ChatColor.WHITE + "Nach jedem " + ChatColor.GREEN + "Kill " + ChatColor.WHITE + "erhält\n" + ChatColor.WHITE + "der Spieler " + ChatColor.GREEN + "10 Sekunden Regeneration 2"));
			}
			else if(i == 29) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.BOW, "Sniper",
						ChatColor.WHITE + "Pfeile, die aus " + ChatColor.GREEN + "mehr als 30 Blöcken\n" + ChatColor.WHITE + "Abstand geschossen werden,\n" + ChatColor.WHITE + "machen doppelten Schaden"));
			}
			else if(i == 30) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.MUSHROOM_STEW, "Soup", 
						ChatColor.WHITE + "Suppenheilung ist aktiviert " + ChatColor.GREEN + "(2 Herzen)"));
			}
			else if(i == 31) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.IRON_CHESTPLATE, "Starter",
						ChatColor.WHITE +"Das UHC beginnt " + ChatColor.GREEN + "für jeden\n" + ChatColor.WHITE + "mit Eisenrüstung und Werkzeug"));
			}
			else if(i == 32) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.SNOWBALL, "Switcheroo",
						ChatColor.WHITE + "Beim Abschießen eines Spielers " + ChatColor.GREEN + "(Pfeil, Ei ...)\n" + ChatColor.WHITE + "tauschen die Positionen der beiden Spieler"));
			}
			else if(i == 33) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.OAK_SAPLING, "Timber",
						ChatColor.WHITE + "Bäume gehen direkt kaputt"));
			}
			else if(i == 34) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.TNT, "Time Bomb", 
						ChatColor.WHITE + "Nach einem Kill spawnt eine " + ChatColor.GREEN + "Kiste\n" + ChatColor.WHITE + "mit den Items des Gegners, \n" + ChatColor.WHITE + "diese " + ChatColor.RED + "explodiert " + ChatColor.WHITE + "nach " + ChatColor.GREEN + "30 Sekunden"));
			}
			else if(i == 35) {
				this._scenarioPickerInv.setItem(i, _changeItemName(Material.ZOMBIE_HEAD, "Zombie Death",
						ChatColor.WHITE + "Wenn ein Spieler stirbt,\n" + ChatColor.WHITE + "spawnt an dessen Standort ein\n" + ChatColor.GREEN + "Zombie mit Diamantrüstung"));
			}
			
			_addGrayDey(9, 17);
			_addGrayDey(36, 44);
			
		}
	}
	
	private void _addGrayDey(int start, int end) {
		for(int i = start; i <= end; i++) {
			this._scenarioPickerInv.setItem(i, new ItemStack(Material.GRAY_DYE));
		}
		
	}

	private ItemStack _changeItemName(Material material, String name, String description) {
		List<String> descriptionList = new ArrayList<String>();
		if(description.contains("\n")) {
			for(String s : description.split("\n")) {
				descriptionList.add(s);
			}
		}
		else
			descriptionList.add(description);
		
		ItemStack item = new ItemStack(material, 1);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(name);
		m.setLore(descriptionList);
		item.setItemMeta(m);
		return item;
	}
	
	private void _activateOrDeactivateScenario(String eventName, boolean activated, int inventorySlot) {
		if(activated) {
			Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + eventName + " wurde aktiviert.");
			this._scenarioPickerInv.setItem(inventorySlot, new ItemStack(Material.LIME_DYE));
		}else {
			Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + eventName + " wurde deaktiviert");
			this._scenarioPickerInv.setItem(inventorySlot, new ItemStack(Material.GRAY_DYE));
		}
	}
}
