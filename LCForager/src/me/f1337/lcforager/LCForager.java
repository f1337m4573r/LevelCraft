package me.f1337.lcforager;


import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class LCForager extends JavaPlugin
{
  public final Logger logger = Logger.getLogger("Minecraft");
  private final LCBlockListener blockListener = new LCBlockListener(this);
  final LCConfiguration LCConfiguration = new LCConfiguration(this);
  public String ConfigurationFileString = "plugins/LevelCraftCore/Configs/Forager.cfg";
  public File ConfigurationFile = new File("plugins/LevelCraftCore/Configs/Forager.cfg");
  public Plugin thisPlug;
public Object AnimalFeedLevel;

  public void onDisable()
  {
  }

  public void onEnable()
  {
    new File("plugins/LevelCraftCore/Configs/").mkdirs();
    try {
      this.ConfigurationFile.createNewFile();
    } catch (IOException e) {
      this.logger.log(Level.SEVERE, "[LC] " + e);
    }
    this.LCConfiguration.loadConfig();

    String[] Args = {"find", "fo", "Forager" };
    getConfiguration().setProperty("ReferenceKeys", Args);

    getConfiguration().setProperty("LevelName", "Forager");

    getConfiguration().setProperty("ReferenceIndex", "Fo");

    getConfiguration().setProperty("Author", "Torrent & f1337_m4573r");

    String[] Exp = { 
      "Exp Per Apple " + this.LCConfiguration.ExpPerApple, 
      "Exp Per GoldenApple " + this.LCConfiguration.ExpPerGoldenApple,
      "Exp Per Stick " + this.LCConfiguration.ExpPerForageStick,
      "Exp Per Boots " + this.LCConfiguration.ExpPerForageBoots,
      "Exp Per Ingot " + this.LCConfiguration.ExpPerForageIngot,
      "Exp Per Bowl" + this.LCConfiguration.ExpPerForageBowl};

    getConfiguration().setProperty("LevelExpPer", Exp);
    this.thisPlug = getServer().getPluginManager().getPlugin("LCForager");
    Plugin LevelCraftCore = getServer().getPluginManager().getPlugin(
      "LevelCraftCore");
    if (LevelCraftCore == null) {
      this.logger.log(Level.SEVERE, 
        "[LC] Could not fine LevelCraftCore. Disabling.");
      getServer().getPluginManager().disablePlugin(this);
    } else {
      registerEvents();
      this.logger.log(Level.INFO, "[LC] Level Forager "+this.getDescription().getVersion()+" Loaded");
    }
  }

  private void registerEvents()
  {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvent(Event.Type.BLOCK_BREAK, this.blockListener, 
      Event.Priority.Highest, this);
  }
}