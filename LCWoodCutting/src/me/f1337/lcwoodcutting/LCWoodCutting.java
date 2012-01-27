package me.f1337.lcwoodcutting;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class LCWoodCutting extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	private final LCBlockListener blockListener = new LCBlockListener(this);
	final LCConfiguration LCConfiguration = new LCConfiguration(this);
	public String ConfigurationFileString =  "plugins/LevelCraftCore/Configs/WoodCutting.cfg";
	public  File ConfigurationFile = new File("plugins/LevelCraftCore/Configs/WoodCutting.cfg");
    public Plugin thisPlug;
	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		new File("plugins/LevelCraftCore/Configs/").mkdirs();
		try {
			ConfigurationFile.createNewFile();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "[LC] "+e);
		}
		LCConfiguration.loadConfig();
		// Set Reference Keys.
		String[] Args = { "W", "Wood", "WC", "woodcutting" };
		getConfiguration().setProperty("ReferenceKeys", Args);
		// Set Unlocks Data
		String[] Unlocks = { "Wooden Hatchet = "+LCConfiguration.WoodAxe, "Stone Hatchet = "+LCConfiguration.StoneAxe,
				"Iron Hatchet = "+LCConfiguration.IronAxe, "Gold Hatchet = "+LCConfiguration.GoldAxe, "Diamond Hatchet = "+LCConfiguration.DiamondAxe  };
		getConfiguration().setProperty("LevelUnlocks", Unlocks);
		String[] Exp = { "Exp Per Plank "+LCConfiguration.ExpPerPlank,"Exp Per Log "+LCConfiguration.ExpPerLog};
		int[] UnlocksLevel = { LCConfiguration.WoodAxe, LCConfiguration.StoneAxe,
				LCConfiguration.IronAxe, LCConfiguration.GoldAxe , LCConfiguration.DiamondAxe};
		
		getConfiguration().setProperty("LevelExpPer", Exp);
		getConfiguration().setProperty("LevelUnlocksLevel", UnlocksLevel);
		// Set Level Name.
		getConfiguration().setProperty("LevelName", "WoodCutting");
		// Set Reference Index (1-2 Characters).
		getConfiguration().setProperty("ReferenceIndex", "W");
		// Set Author :3.
		getConfiguration().setProperty("Author", "Samkio Now f1337_m4573r");
		// Log that it has loaded.
        this.thisPlug = this.getServer().getPluginManager().getPlugin("LCWoodCutting");
		Plugin LevelCraftCore = this.getServer().getPluginManager().getPlugin(
				"LevelCraftCore");
		if (LevelCraftCore == null) {
			logger.log(Level.SEVERE,
					"[LC] Could not fine LevelCraftCore. Disabling.");
			this.getServer().getPluginManager().disablePlugin(this);
		} else {
			registerEvents();
			logger.log(Level.INFO, "[LC] Level WoodCutting Loaded");
			
		}
	}

	

	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener,
				Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener,
				Event.Priority.Low, this);
		
	}
}
