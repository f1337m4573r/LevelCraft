package me.f1337.lcfarming;

import java.util.Random;
import me.f1337.levelcraftcore.LCChat;
import me.f1337.levelcraftcore.LevelFunctions;
import me.f1337.levelcraftcore.Whitelist;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class LCBlockListener extends BlockListener
{
  public LCFarming plugin;

  public LCBlockListener(LCFarming instance)
  {
    this.plugin = instance;
  }

  public void onBlockBreak(BlockBreakEvent event) {
	    if (event.isCancelled()) {
	      return;
	}
    
    if (!Whitelist.worldCheck(event.getBlock().getWorld()))
      return;
    Player player = event.getPlayer();

    if (!Whitelist.hasLevel(player, this.plugin.thisPlug))
      return;
    int iih = player.getItemInHand().getTypeId();
    Material m = event.getBlock().getType();
    int level = LevelFunctions.getLevel(player, this.plugin.thisPlug);
    if ((level < this.plugin.LCConfiguration.IronHoe) && (iih == 292)) {
      LCChat.warn(player, "Cannot use this tool. Required Level:" + 
        this.plugin.LCConfiguration.IronHoe);
      event.setCancelled(true);
      return;
    }
    if ((level < this.plugin.LCConfiguration.GoldHoe) && (iih == 294)) {
      LCChat.warn(player, "Cannot use this tool. Required Level:" + 
        this.plugin.LCConfiguration.GoldHoe);
      event.setCancelled(true);
      return;
    }
    if ((level < this.plugin.LCConfiguration.WoodHoe) && (iih == 290)) {
      LCChat.warn(player, "Cannot use this tool. Required Level:" + 
        this.plugin.LCConfiguration.WoodHoe);
      event.setCancelled(true);
      return;
    }
    if ((level < this.plugin.LCConfiguration.DiamondHoe) && (iih == 293)) {
      LCChat.warn(player, "Cannot use this tool. Required Level:" + 
        this.plugin.LCConfiguration.DiamondHoe);
      event.setCancelled(true);
      return;
    }
    if ((level < this.plugin.LCConfiguration.StoneHoe) && (iih == 291)) {
      LCChat.warn(player, "Cannot use this tool. Required Level:" + 
        this.plugin.LCConfiguration.StoneHoe);
      event.setCancelled(true);
      return;
    }
    if (event.getBlock().getType() == Material.LEAVES) {
      Random randomGenerator = new Random();
      int random1 = randomGenerator.nextInt(100);
      if ((random1 == 0) && (level >= this.plugin.LCConfiguration.GoldenAppleLevel)) {
        Location locy = new Location(event.getBlock().getWorld(), event
          .getBlock().getX(), event.getBlock().getY(), event
          .getBlock().getZ(), 0.0F, 0.0F);
        event.getBlock().getWorld()
          .dropItem(locy, new ItemStack(322, 1));
        LevelFunctions.addExp(player, this.plugin.thisPlug, 
          this.plugin.LCConfiguration.ExpPerGoldenApple);
      } else if ((random1 > 0) && (random1 < 10) && (level >= this.plugin.LCConfiguration.AppleLevel)) {
        Location locy = new Location(event.getBlock().getWorld(), event
          .getBlock().getX(), event.getBlock().getY(), event
          .getBlock().getZ(), 0.0F, 0.0F);
        LevelFunctions.addExp(player, this.plugin.thisPlug, 
          this.plugin.LCConfiguration.ExpPerApple);
        event.getBlock().getWorld()
          .dropItem(locy, new ItemStack(260, 1));
      }
      return;
    }
    double gained = 0.0D;
    if ((m == Material.SUGAR_CANE_BLOCK) && (level >= this.plugin.LCConfiguration.SugarCaneLevel))
    { 
      gained = this.plugin.LCConfiguration.ExpPerSugarCane;
    }
    if ((m.getId() == 59) && (event.getBlock().getData() == 7) && (level >= this.plugin.LCConfiguration.HarvestLevel))
    {
      gained = this.plugin.LCConfiguration.ExpPerHarvest;
    }
    if ((m == Material.CACTUS) && (level >= this.plugin.LCConfiguration.CactusLevel))
    {
    	gained = this.plugin.LCConfiguration.ExpPerCactus;
    }
    if (gained == 0.0D)
      return;
    LevelFunctions.addExp(player, this.plugin.thisPlug, gained);
  }

  public void onBlockPlace(BlockPlaceEvent event) {
    if (event.isCancelled())
      return;
    if (!Whitelist.worldCheck(event.getBlock().getWorld()))
        return;
      Player player = event.getPlayer();

      if (!Whitelist.hasLevel(player, this.plugin.thisPlug))
        return;
      int iih = player.getItemInHand().getTypeId();
      Material m = event.getBlock().getType();
      int level = LevelFunctions.getLevel(player, this.plugin.thisPlug);
      
      double gained = 0.0D;
      
      if (m == Material.SAPLING && (level >= this.plugin.LCConfiguration.SaplingLevel)) {
      		gained = this.plugin.LCConfiguration.ExpPerSapling; 
      }
      if (m == Material.CACTUS && (level >=this.plugin.LCConfiguration.CactusLevel)) {
    	  gained = this.plugin.LCConfiguration.ExpPerCactus;
      }
      if (m == Material.RED_ROSE && (level >= this.plugin.LCConfiguration.RedRoseLevel)) {
    	
    	  gained = this.plugin.LCConfiguration.ExpPerRedRose;
      }
      if (m == Material.YELLOW_FLOWER && (level >= this.plugin.LCConfiguration.YellowFlowerLevel)) {
    		gained = this.plugin.LCConfiguration.ExpPerYellowFlower; 
      }
      if (m == Material.BROWN_MUSHROOM  && (level >= this.plugin.LCConfiguration.MushroomLevel)) {
    	  gained = this.plugin.LCConfiguration.ExpPerMushroom;
      }
      if (m == Material.RED_MUSHROOM && (level >= this.plugin.LCConfiguration.MushroomLevel)) {
    	  gained = this.plugin.LCConfiguration.ExpPerMushroom;
      }
      if (m == Material.NETHER_WARTS && (level >= this.plugin.LCConfiguration.NetherWartLevel)) {
          gained = this.plugin.LCConfiguration.ExpPerNetherWart;
      }
      if (gained == 0.0D)
        return;
      LevelFunctions.addExp(player, this.plugin.thisPlug, gained);
      
  }
  

}