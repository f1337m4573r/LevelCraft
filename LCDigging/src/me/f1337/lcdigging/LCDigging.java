//Declare the package name
package me.f1337.lcdigging;
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
public class LCDigging extends JavaPlugin {
	//Assign a logger with the value 'logger'
	public final Logger logger = Logger.getLogger("Minecraft");
	//Create a Block Listener.
	private final LCBlockListener blockListener = new LCBlockListener(this);
	//Create a configuration.
	final LCConfiguration LCConfiguration = new LCConfiguration(this);
	//Create a Configuration File (String).
	public String ConfigurationFileString = "plugins/LevelCraftCore/Configs/Digging.cfg";
    //Create a Configuration File(File).
	public File ConfigurationFile = new File(
			"plugins/LevelCraftCore/Configs/Digging.cfg");
	//Create a Plugin variable 'thisPlug'
	public Plugin thisPlug;

	
	//onDisable. Called when the plugin is disabled.
	@Override
	public void onDisable() {
		//Log that the plugin has been disabled.
		logger.log(Level.INFO, "[LC] Level Digging Unloaded");
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
		String[] Args = { "D", "Digging", "Dig", "Spade" };
		getConfiguration().setProperty("ReferenceKeys", Args);
        //Set data to be shown on /lvl unlocks <ref>
		String[] Unlocks = { "Wooden Spade = " + LCConfiguration.WoodSpade,
				"Stone Spade = " + LCConfiguration.StoneSpade,
				"Iron Spade = " + LCConfiguration.IronSpade,
				"Gold Spade = " + LCConfiguration.GoldSpade,
				"Diamond Spade = " + LCConfiguration.DiamondSpade };
		//Set data in parallel with Unlocks. (This denotes wethere the user has the unlock or not.)
		int[] UnlocksLevel = { LCConfiguration.WoodSpade,
				LCConfiguration.StoneSpade, LCConfiguration.IronSpade,
				LCConfiguration.GoldSpade, LCConfiguration.DiamondSpade };
		//Sets the data for /lvl exp <ref>
		String[] Exp = { "Exp Per Dirt " + LCConfiguration.ExpPerDirt,
				"Exp Per Sand " + +LCConfiguration.ExpPerSand,
				"Exp Per Gravel " + LCConfiguration.ExpPerGravel,
				"Exp Per Clay " + +LCConfiguration.ExpPerClay,
				"Exp Per Grass " + LCConfiguration.ExpPerGrass,
				"Exp Per Soul Sand " + LCConfiguration.ExpPerSoulSand,
                                "Exp Per Mycelium" + LCConfiguration.ExpPerMycelium, };
		//Set the LevelExpPer to the Exp Array.
		getConfiguration().setProperty("LevelExpPer", Exp);
		//Set the LevelUnlocksLevel to the UnlocksLevel int array.
		getConfiguration().setProperty("LevelUnlocksLevel", UnlocksLevel);
		//Set the LevelUnlocks to the Unlocks string array.
		getConfiguration().setProperty("LevelUnlocks", Unlocks);
		// Set Level Name.
		getConfiguration().setProperty("LevelName", "Digging");
		// Set Reference Index (1-2 Characters).
		getConfiguration().setProperty("ReferenceIndex", "D");
		// Set Author :3.
		getConfiguration().setProperty("Author", "f1337_m4573r");
		// Get the plugin that this one is.
		this.thisPlug = this.getServer().getPluginManager().getPlugin("LCDigging");
		//Get the plugin that LevelCraftCore is.
		Plugin LevelCraftCore = this.getServer().getPluginManager().getPlugin("LevelCraftCore");
		//If the plugin for LevelCraftCore is null then disable the plugin. If not register the events and state that it is loaded. 
		if (LevelCraftCore == null) {
			logger.log(Level.SEVERE,
					"[LC] Could not fine LevelCraftCore. Disabling.");
			this.getServer().getPluginManager().disablePlugin(this);
		} else {
			registerEvents();
			logger.log(Level.INFO, "[LC] Level Digging Loaded");

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
