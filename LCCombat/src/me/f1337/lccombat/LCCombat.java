//Declare the package name
package me.f1337.lccombat;

//Add all the necessary imports.
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

//Start the class.
public class LCCombat extends JavaPlugin {
	// Assign a logger with the value 'logger'
	public final Logger logger = Logger.getLogger("Minecraft");
	// Create a Block Listener.
	final LCEntityListener entListener = new LCEntityListener(this);
	// Create a configuration.
	final LCConfiguration LCConfiguration = new LCConfiguration(this);
	// Create a Configuration File (String).
	public String ConfigurationFileString = "plugins/LevelCraftCore/Configs/Combat.cfg";
	// Create a Configuration File(File).
	public File ConfigurationFile = new File(
			"plugins/LevelCraftCore/Configs/Combat.cfg");
	// Create a Plugin variable 'thisPlug'
	public Plugin thisPlug;

	// onDisable. Called when the plugin is disabled.
	@Override
	public void onDisable() {
		// Log that the plugin has been disabled.
		logger.log(Level.INFO, "[LC] Level Combat Unloaded");
	}

	// onEnable. Called when the plugin is enabled.
	@Override
	public void onEnable() {
		// Create a new dirctory structure if not exsits.
		new File("plugins/LevelCraftCore/Configs/").mkdirs();
		try {
			// Create a new configuration file.
			ConfigurationFile.createNewFile();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "[LC] " + e);
		}
		// Load this configuration file.
		LCConfiguration.loadConfig();
		// Set Reference Keys. 1-4 good.
		String[] Args = { "c", "Combat", "Attack" };
		getConfiguration().setProperty("ReferenceKeys", Args);
		// Set data to be shown on /lvl unlocks <ref>
		String[] Unlocks = {
				"Wooden Sword = "
						+ LCConfiguration.ToolLevels.get(Material.WOOD_SWORD
								.getId()),
				"Stone Sword = "
						+ LCConfiguration.ToolLevels.get(Material.STONE_SWORD
								.getId()),
				"Iron Sword = "
						+ LCConfiguration.ToolLevels.get(Material.IRON_SWORD
								.getId()),
				"Gold Sword = "
						+ LCConfiguration.ToolLevels.get(Material.GOLD_SWORD
								.getId()),
				"Diamond Sword = "
						+ LCConfiguration.ToolLevels.get(Material.DIAMOND_SWORD
								.getId()),
				"Wooden Battle Axe = "
						+ LCConfiguration.ToolLevels.get(Material.WOOD_AXE
								.getId()),
				"Stone Battle Axe = "
						+ LCConfiguration.ToolLevels.get(Material.STONE_AXE
								.getId()),
				"Iron Battle Axe = "
						+ LCConfiguration.ToolLevels.get(Material.IRON_AXE
								.getId()),
				"Gold Battle Axe = "
						+ LCConfiguration.ToolLevels.get(Material.GOLD_AXE
								.getId()),
				"Diamond Battle Axe = "
						+ LCConfiguration.ToolLevels.get(Material.DIAMOND_AXE
								.getId()) };
		// Set data in parallel with Unlocks. (This denotes wethere the user has
		// the unlock or not.)
		int[] UnlocksLevel = {
				LCConfiguration.ToolLevels.get(Material.WOOD_SWORD.getId()),
				LCConfiguration.ToolLevels.get(Material.STONE_SWORD.getId()),
				LCConfiguration.ToolLevels.get(Material.IRON_SWORD.getId()),
				LCConfiguration.ToolLevels.get(Material.GOLD_SWORD.getId()),
				LCConfiguration.ToolLevels.get(Material.DIAMOND_SWORD.getId()),
				LCConfiguration.ToolLevels.get(Material.WOOD_AXE.getId()),
				LCConfiguration.ToolLevels.get(Material.STONE_AXE.getId()),
				LCConfiguration.ToolLevels.get(Material.IRON_AXE.getId()),
				LCConfiguration.ToolLevels.get(Material.GOLD_AXE.getId()),
				LCConfiguration.ToolLevels.get(Material.DIAMOND_AXE.getId()) };
		// Sets the data for /lvl exp <ref>
		String[] Exp = { "Exp Per Damage " + LCConfiguration.ExpPerDamage };
		// Set the LevelExpPer to the Exp Array.
		getConfiguration().setProperty("LevelExpPer", Exp);
		// Set the LevelUnlocksLevel to the UnlocksLevel int array.
		getConfiguration().setProperty("LevelUnlocksLevel", UnlocksLevel);
		// Set the LevelUnlocks to the Unlocks string array.
		getConfiguration().setProperty("LevelUnlocks", Unlocks);
		// Set Level Name.
		getConfiguration().setProperty("LevelName", "Combat");
		// Set Reference Index (1-2 Characters).
		getConfiguration().setProperty("ReferenceIndex", "C");
		// Set Author :3.
		getConfiguration().setProperty("Author", "Samkio & f1337_m4573r");
		// Get the plugin that this one is.
		this.thisPlug = this.getServer().getPluginManager()
				.getPlugin("LCCombat");
		// Get the plugin that LevelCraftCore is.
		Plugin LevelCraftCore = this.getServer().getPluginManager()
				.getPlugin("LevelCraftCore");
		// If the plugin for LevelCraftCore is null then disable the plugin. If
		// not register the events and state that it is loaded.
		if (LevelCraftCore == null) {
			logger.log(Level.SEVERE,
					"[LC] Could not fine LevelCraftCore. Disabling.");
			this.getServer().getPluginManager().disablePlugin(this);
		} else {
			registerEvents();
			logger.log(Level.INFO, "[LC] Level Combat Loaded");

		}
	}

	private void registerEvents() {
		// Register the events necessary
		PluginManager pm = getServer().getPluginManager();
		// Block_Break event.
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entListener,
				Event.Priority.Highest, this);

	}
}
