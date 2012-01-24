//Declare the package name
package me.f1337.lcmining;
//Add all the necessary imports.
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
//Start the class.
public class LCMining extends JavaPlugin {
	//Assign a logger with the value 'logger'
	public final Logger logger = Logger.getLogger("Minecraft");
	//Create a Block Listener.
	private final LCBlockListener blockListener = new LCBlockListener(this);
	//Create a configuration.
	final LCConfiguration LCConfiguration = new LCConfiguration(this);
	//Create a Configuration File (String).
	public String ConfigurationFileString = "plugins/LevelCraftCore/Configs/Mining.cfg";
    //Create a Configuration File(File).
	public File ConfigurationFile = new File(
			"plugins/LevelCraftCore/Configs/Mining.cfg");
	//Create a Plugin variable 'thisPlug'
	public Plugin thisPlug;

	
	//onDisable. Called when the plugin is disabled.
	@Override
	public void onDisable() {
		//Log that the plugin has been disabled.
		logger.log(Level.INFO, "[LC] Level Mining Unloaded");
	}

	//onEnable. Called when the plugin is enabled.
	@Override
	public void onEnable() {
		//Create a new dirctory structure if not exsits.
		new File("plugins/LevelCraftCore/Configs/").mkdirs();
		try {
			//Create a new configuration file.
			ConfigurationFile.createNewFile();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "[LC] " + e);
		}
		//Load this configuration file.
		LCConfiguration.loadConfig();
		// Set Reference Keys. 1-4 good.
		String[] Args = { "M", "Mining", "Mine", "Pick" };
		getConfiguration().setProperty("ReferenceKeys", Args);
        //Set data to be shown on /lvl unlocks <ref>
		String[] Unlocks = { "Wooden Pickaxe = " + LCConfiguration.WoodPick,
				"Stone Pickaxe = " + LCConfiguration.StonePick,
				"Iron Pickaxe = " + LCConfiguration.IronPick,
				"Gold Pickaxe = " + LCConfiguration.GoldPick,
				"Diamond Pickaxe = " + LCConfiguration.DiamondPick };
		//Set data in parallel with Unlocks. (This denotes wethere the user has the unlock or not.)
		int[] UnlocksLevel = { LCConfiguration.WoodPick,
				LCConfiguration.StonePick, LCConfiguration.IronPick,
				LCConfiguration.GoldPick, LCConfiguration.DiamondPick };
		//Sets the data for /lvl exp <ref>
		String[] Exp = { "Exp Per Stone " + LCConfiguration.ExpPerStone,
				"Exp Per CobbleStone " + +LCConfiguration.ExpPerCobble,
				"Exp Per RedStone " + LCConfiguration.ExpPerRedstone,
				"Exp Per GoldOre " + +LCConfiguration.ExpPerGoldOre,
				"Exp Per IronOre " + LCConfiguration.ExpPerIronOre,
				"Exp Per CoalOre " + LCConfiguration.ExpPerCoalOre,
				"Exp Per LapisOre " + LCConfiguration.ExpPerLapisOre,
				"Exp Per MossStone " + LCConfiguration.ExpPerMossStone,
				"Exp Per Obsidian " + LCConfiguration.ExpPerObsidian,
				"Exp Per DiamondOre " + LCConfiguration.ExpPerDiamondOre,
				"Exp Per Netherrack " + LCConfiguration.ExpPerNetherrack,
				"Exp Per SandStone " + LCConfiguration.ExpPerSandStone, 
                                "Exp Per NetherBrick " + LCConfiguration.ExpPerNetherBrick,
                                "Exp Per Smoothbrick " + LCConfiguration.ExpPerSmoothbrick,};
		//Set the LevelExpPer to the Exp Array.
		getConfiguration().setProperty("LevelExpPer", Exp);
		//Set the LevelUnlocksLevel to the UnlocksLevel int array.
		getConfiguration().setProperty("LevelUnlocksLevel", UnlocksLevel);
		//Set the LevelUnlocks to the Unlocks string array.
		getConfiguration().setProperty("LevelUnlocks", Unlocks);
		// Set Level Name.
		getConfiguration().setProperty("LevelName", "Mining");
		// Set Reference Index (1-2 Characters).
		getConfiguration().setProperty("ReferenceIndex", "M");
		// Set Author :3.
		getConfiguration().setProperty("Author", "Samkio");
		// Get the plugin that this one is.
		this.thisPlug = this.getServer().getPluginManager().getPlugin("LCMining");
		//Get the plugin that LevelCraftCore is.
		Plugin LevelCraftCore = this.getServer().getPluginManager().getPlugin("LevelCraftCore");
		//If the plugin for LevelCraftCore is null then disable the plugin. If not register the events and state that it is loaded. 
		if (LevelCraftCore == null) {
			logger.log(Level.SEVERE,
					"[LC] Could not fine LevelCraftCore. Disabling.");
			this.getServer().getPluginManager().disablePlugin(this);
		} else {
			registerEvents();
			logger.log(Level.INFO, "[LC] Level Mining Loaded");

		}
	}

	private void registerEvents() {
		//Register the events necessary 
		PluginManager pm = getServer().getPluginManager();
		//Block_Break event.
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener,
				Event.Priority.Highest, this);

	}
}
