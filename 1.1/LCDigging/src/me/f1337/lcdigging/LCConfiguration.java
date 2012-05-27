package me.f1337.lcdigging;


import java.io.IOException;
import java.util.logging.Level;
import me.digging.levelcraftcore.util.Properties;
/* This class sets up the configuration for the variables.
 * It also sets up the configuration file with the variables.
 * Important to note it imports the properties functions from LevelCraftCore
 */

public class LCConfiguration {
	public LCDigging plugin;
	//TOOLS
	public int WoodSpade;
	public int StoneSpade;
	public int IronSpade;
	public int GoldSpade;
	public int DiamondSpade;
	//EXP PER
	public double ExpPerDirt;
	public double ExpPerClay;
	public double ExpPerSand;
	public double ExpPerGravel;
	public double ExpPerGrass;
	public double ExpPerMycelium;
	public double ExpPerSoulSand;
	//LEVEL BLOCK
	public  int DirtLevel;
	public  int ClayLevel;
	public  int SandLevel;
	public  int GravelLevel;
	public  int GrassLevel;
	public  int MyceliumLevel;
	public  int SoulSandLevel;
	
	public LCConfiguration(LCDigging instance) {
		plugin = instance;
	}
    //loadConfig. Called onEnable in main class.
	public void loadConfig() {
        //Create new properties file with the file string.
		Properties properties = new Properties(plugin.ConfigurationFileString);
		try {
			//Try to load it. If not return an error.
			properties.load();
		} catch (IOException e) {
			plugin.logger.log(Level.SEVERE,"[LC] "+e);
		}
		//Set variables for TOOLS
		this.WoodSpade = properties.getInteger("WoodenSpadeLevel", 0);
		this.StoneSpade = properties.getInteger("StoneSpadeLevel", 5);
		this.IronSpade = properties.getInteger("IronSpadeLevel", 10);
		this.GoldSpade = properties.getInteger("GoldSpadeLevel", 20);
		this.DiamondSpade = properties.getInteger("DiamondSpadeLevel", 30);
		
		//Set variables for EXP PER
		this.ExpPerDirt = properties.getDouble("ExpPerStone", 5);
		this.ExpPerClay = properties.getDouble("ExpPerCobble", 15);
		this.ExpPerSand = properties.getDouble("ExpPerRedstone", 10);
		this.ExpPerGravel = properties.getDouble("ExpPerGoldOre", 2);
		this.ExpPerGrass = properties.getDouble("ExpPerIronOre", 5);
		this.ExpPerMycelium = properties.getDouble("ExpPerCoalOre", 5);
		this.ExpPerSoulSand = properties.getDouble("ExpPerLapisOre", 20);
		//Set variables for LEVEL BLOCK
		this.DirtLevel = properties.getInteger("LevelForDirt", 0);
		this.ClayLevel = properties.getInteger("LevelForClay", 5);
		this.SandLevel = properties.getInteger("LevelForSand", 5);
		this.GravelLevel = properties.getInteger("LevelForGravel", 10);
		this.GrassLevel = properties.getInteger("LevelForGrass", 20);
		this.MyceliumLevel = properties.getInteger("LevelForMycelium", 0);
		this.SoulSandLevel = properties.getInteger("LevelForSoulSand", 30);
		

	}
}
