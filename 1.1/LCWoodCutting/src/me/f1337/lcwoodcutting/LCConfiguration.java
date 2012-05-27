package me.f1337.lcwoodcutting;


import java.io.IOException;
import java.util.logging.Level;

import me.f1337.levelcraftcore.util.Properties;


public class LCConfiguration {
	public LCWoodCutting plugin;
	//TOOLS
	public int WoodAxe;
	public int StoneAxe;
	public int IronAxe;
	public int GoldAxe;
	public int DiamondAxe;
	//EXP PER
	public double ExpPerLog;
	public double ExpPerPlank;
	//LEVEL BLOCK
	public  int RedwoodLevel;
	public  int BirchLevel;
	public  int LogLevel;
	public  int PlankLevel;

	
	public LCConfiguration(LCWoodCutting instance) {
		plugin = instance;
	}

	public void loadConfig() {

		Properties properties = new Properties(plugin.ConfigurationFileString);
		try {
			properties.load();
		} catch (IOException e) {
			plugin.logger.log(Level.SEVERE,"[LC] "+e);
		}
		//TOOLS
		this.WoodAxe = properties.getInteger("WoodenAxeLevel", 0);
		this.StoneAxe = properties.getInteger("StoneAxeLevel", 5);
		this.IronAxe = properties.getInteger("IronAxeLevel", 10);
		this.GoldAxe = properties.getInteger("GoldAxeLevel", 20);
		this.DiamondAxe = properties.getInteger("DiamondAxeLevel", 30);
		
		//EXP PER
		this.ExpPerLog = properties.getDouble("ExpPerLog", 5);
		this.ExpPerPlank = properties.getDouble("ExpPerPlank", 2);

		
		//LEVEL BLOCK
		this.RedwoodLevel = properties.getInteger("LevelForRedwood", 0);
		this.BirchLevel = properties.getInteger("LevelForBirch", 0);
		this.LogLevel = properties.getInteger("LevelForLog", 0);
		this.PlankLevel = properties.getInteger("LevelForPlank", 0);



	}
}
