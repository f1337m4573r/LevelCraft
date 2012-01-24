package me.f1337.lcmining;


import java.io.IOException;
import java.util.logging.Level;
import me.f1337.levelcraftcore.util.Properties;
/* This class sets up the configuration for the variables.
 * It also sets up the configuration file with the variables.
 * Important to note it imports the properties functions from LevelCraftCore
 */

public class LCConfiguration {
	public LCMining plugin;
	//TOOLS
	public int WoodPick;
	public int StonePick;
	public int IronPick;
	public int GoldPick;
	public int DiamondPick;
	//EXP PER
	public double ExpPerStone;
	public double ExpPerCobble;
	public double ExpPerRedstone;
	public double ExpPerGoldOre;
	public double ExpPerIronOre;
	public double ExpPerCoalOre;
	public double ExpPerLapisOre;
	public double ExpPerMossStone;
	public double ExpPerObsidian;
	public double ExpPerDiamondOre;
	public double ExpPerNetherrack;
	public double ExpPerSandStone;
        public double ExpPerNetherBrick;
        public double ExpPerEndStone;
	//LEVEL BLOCK
	public  int StoneLevel;
	public  int CobbleLevel;
	public  int IronLevel;
	public  int RedLevel;
	public  int GoldLevel;
	public  int CoalLevel;
	public  int LapisLevel;
	public  int MossLevel;
	public  int ObsidianLevel;
	public  int DiamondLevel;
	public  int NetherLevel;
	public  int SandStoneLevel;
        public  int NetherBrickLevel;
        public  int EndStoneLevel;
	
	public LCConfiguration(LCMining instance) {
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
		this.WoodPick = properties.getInteger("WoodenPickaxeLevel", 0);
		this.StonePick = properties.getInteger("StonePickaxeLevel", 5);
		this.IronPick = properties.getInteger("IronPickaxeLevel", 10);
		this.GoldPick = properties.getInteger("GoldPickaxeLevel", 20);
		this.DiamondPick = properties.getInteger("DiamondPickaxeLevel", 30);
		
		//Set variables for EXP PER
		this.ExpPerStone = properties.getDouble("ExpPerStone", 5);
		this.ExpPerCobble = properties.getDouble("ExpPerCobble", 5);
		this.ExpPerRedstone = properties.getDouble("ExpPerRedstone", 20);
		this.ExpPerGoldOre = properties.getDouble("ExpPerGoldOre", 30);
		this.ExpPerIronOre = properties.getDouble("ExpPerIronOre", 20);
		this.ExpPerCoalOre = properties.getDouble("ExpPerCoalOre", 10);
		this.ExpPerLapisOre = properties.getDouble("ExpPerLapisOre", 100);
		this.ExpPerMossStone = properties.getDouble("ExpPerMossyCobble", 10);
		this.ExpPerObsidian = properties.getDouble("ExpPerObsidian", 200);
		this.ExpPerDiamondOre = properties.getDouble("ExpPerDiamondOre", 100);
		this.ExpPerNetherrack = properties.getDouble("ExpPerNetherrack", 3);
		this.ExpPerSandStone = properties.getDouble("ExpPerSandStone", 3);
                this.ExpPerNetherBrick = properties.getDouble("ExpPerNetherBrick", 15);
                this.ExpPerEndStone = properties.getDouble("ExpPerEndStone", 20);
                
		
		//Set variables for LEVEL BLOCK
		this.StoneLevel = properties.getInteger("LevelForStone", 0);
		this.SandStoneLevel = properties.getInteger("LevelForSandStone", 0);
		this.CobbleLevel = properties.getInteger("LevelForCobble", 0);
		this.RedLevel = properties.getInteger("LevelForRedstone", 10);
		this.GoldLevel = properties.getInteger("LevelForGoldOre", 20);
		this.IronLevel = properties.getInteger("LevelForIronOre", 5);
		this.CoalLevel = properties.getInteger("LevelForCoalOre", 5);
		this.LapisLevel = properties.getInteger("LevelForLapisOre", 20);
		this.MossLevel = properties.getInteger("LevelForMossStone", 10);
		this.ObsidianLevel = properties.getInteger("LevelForObsidian", 35);
		this.DiamondLevel = properties.getInteger("LevelForDiamond", 25);
		this.NetherLevel = properties.getInteger("LevelForNether", 45);
                this.NetherBrickLevel = properties.getInteger("LevelForNetherBrick", 55);
                this.EndStoneLevel = properties.getInteger("LevelForEnderStone", 99);
                
	}
}
