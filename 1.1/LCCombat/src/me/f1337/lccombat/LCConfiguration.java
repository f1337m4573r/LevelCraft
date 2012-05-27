package me.f1337.lccombat;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Material;

import me.f1337.levelcraftcore.util.Properties;

/* This class sets up the configuration for the variables.
 * It also sets up the configuration file with the variables.
 * Important to note it imports the properties functions from LevelCraftCore
 */

public class LCConfiguration {
	public LCCombat plugin;
	// TOOLS
	public HashMap<Integer, Integer> ToolLevels = new HashMap<Integer, Integer>();
	
	
	// EXP PER
	public double ExpPerDamage;

	public boolean pvpRangeEnable;
	public int pvpRange;
    public boolean Baxes;
	public LCConfiguration(LCCombat instance) {
		plugin = instance;
	}

	// loadConfig. Called onEnable in main class.
	public void loadConfig() {
		// Create new properties file with the file string.
		Properties properties = new Properties(plugin.ConfigurationFileString);
		try {
			// Try to load it. If not return an error.
			properties.load();
		} catch (IOException e) {
			plugin.logger.log(Level.SEVERE, "[LC] " + e);
		}
		// Set variables for TOOLS
		this.ToolLevels.put(Material.WOOD_SWORD.getId(), properties.getInteger("WoodenSwordLevel", 0));
		this.ToolLevels.put(Material.STONE_SWORD.getId(),properties.getInteger("StoneSwordLevel", 5));
		this.ToolLevels.put(Material.IRON_SWORD.getId(),properties.getInteger("IronSwordLevel", 10));
		this.ToolLevels.put(Material.GOLD_SWORD.getId(),properties.getInteger("GoldSwordLevel", 20));
		this.ToolLevels.put(Material.DIAMOND_SWORD.getId(),properties.getInteger("DiamondSwordLevel", 30));
		this.ToolLevels.put(Material.WOOD_AXE.getId(), properties.getInteger("WoodenBAxeLevel", 0));
		this.ToolLevels.put(Material.STONE_AXE.getId(),properties.getInteger("StoneBAxeLevel", 5));
		this.ToolLevels.put(Material.IRON_AXE.getId(),properties.getInteger("IronBAxeLevel", 10));
		this.ToolLevels.put(Material.GOLD_AXE.getId(),properties.getInteger("GoldBAxeLevel", 20));
		this.ToolLevels.put(Material.DIAMOND_AXE.getId(),properties.getInteger("DiamondBAxeLevel", 30));
		// Set variables for EXP PER
		this.ExpPerDamage = properties.getDouble("ExpPerDamage", 5);
        this.Baxes = properties.getBoolean("EnableBattleAxes",true);
		//
		this.pvpRangeEnable = properties
				.getBoolean("EnablePvpOnlyRange", false);
		this.pvpRange = properties.getInteger("PvpRange", 5);

	}
}
