package me.f1337.lcforager;

import java.io.IOException;
import java.util.logging.Level;

import me.f1337.levelcraftcore.util.Properties;

public class LCConfiguration
{
  public LCForager plugin;
  public int NoUnlocks;
  public double ExpPerApple;
  public double ExpPerGoldenApple;
  public double ExpPerForageStick;
  public double ExpPerForageBoots;
  public double ExpPerForageBowl;
  public double ExpPerForageIngot;
  public int AppleLevel;
  public int GoldenAppleLevel;
  public int ForageStickLevel;
  public int ForageBootsLevel;
  public int ForageBowlLevel;
  public int ForageIngotLevel;

  public LCConfiguration(LCForager instance)
  {
    this.plugin = instance;
  }

  public void loadConfig()
  {
    Properties properties = new Properties(this.plugin.ConfigurationFileString);
    try {
      properties.load();
    } catch (IOException e) {
      this.plugin.logger.log(Level.SEVERE, "[LC] " + e);
    }
    this.ExpPerApple = properties.getDouble("ExpPerApples", 3.0D);
    this.ExpPerGoldenApple = properties.getDouble("ExpPerGoldenApple", 100.0D);
    this.ExpPerForageStick = properties.getDouble("ExpForageStick", 100.0D);
    this.ExpPerForageBoots = properties.getDouble("ExpForageBoots", 20.0D);
    this.ExpPerForageBowl = properties.getDouble("ExpPerBowl", 15.0D);
    this.ExpPerForageIngot = properties.getDouble("ExpPerIngot", 200.0D);    
    
    this.AppleLevel = properties.getInteger("LevelForApple", 0);
    this.GoldenAppleLevel = properties.getInteger("LevelForGoldenApple", 0);
    this.ForageStickLevel = properties.getInteger("LevelForForageStick", 0);
    this.ForageBootsLevel = properties.getInteger("LevelForForageBoots", 0);
    this.ForageBowlLevel = properties.getInteger("LevelForForageBoots", 0);
    this.ForageIngotLevel = properties.getInteger("LevelForForageIngot", 0);
  }
}