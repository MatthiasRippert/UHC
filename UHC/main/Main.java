package com.matze.UHC.main;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;
import com.matze.UHC.scenarioPicker.ScenarioPickerListener;
import com.matze.UHC.scenarios.AppleKillListener;
import com.matze.UHC.scenarios.AutoSmeltListener;
import com.matze.UHC.scenarios.AutoTorchListener;
import com.matze.UHC.scenarios.BedBombListener;
import com.matze.UHC.scenarios.HasteyBoysListener;
import com.matze.UHC.scenarios.HealingKillListener;
import com.matze.UHC.scenarios.HungerlessListener;
import com.matze.UHC.scenarios.NoFallListener;
import com.matze.UHC.scenarios.SniperListener;
import com.matze.UHC.scenarios.SoupListener;
import com.matze.UHC.scenarios.StarterListener;
import com.matze.UHC.scenarios.SwitcherooListener;
import com.matze.UHC.scenarios.TimberListener;
import com.matze.UHC.scenarios.TimeBombListener;
import com.matze.UHC.scenarios.UHCCommands;
import com.matze.UHC.scenarios.ZombieDeathListener;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		UHCCommands uhcCommands = new UHCCommands();
		
		uhcCommands.fillBackpackHashMap();
		getCommand("backpack").setExecutor(uhcCommands);
		getCommand("bp").setExecutor(uhcCommands);
		
		getCommand("workbench").setExecutor(uhcCommands);
		getCommand("wb").setExecutor(uhcCommands);
		
		getCommand("gm").setExecutor(uhcCommands);
		new ScenarioPickerListener(this, uhcCommands);
	}
}
