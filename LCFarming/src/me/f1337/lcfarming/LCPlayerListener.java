package me.f1337.lcfarming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import me.f1337.levelcraftcore.LCChat;
import me.f1337.levelcraftcore.LevelFunctions;
import me.f1337.levelcraftcore.Whitelist;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class LCPlayerListener extends PlayerListener
{
  public LCFarming plugin;
  private static HashSet<Byte> transparentBlockList = null;
  public LCPlayerListener(LCFarming instance)
  {
    this.plugin = instance;
  }

  public void onPlayerInteract(PlayerInteractEvent event) {
    if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
      return;
    if (!Whitelist.hasLevel(event.getPlayer(), this.plugin.thisPlug)) {
      return;
    }
    Player player = event.getPlayer();
    int iih = player.getItemInHand().getTypeId();
    Material m = event.getClickedBlock().getType();

    if (!Whitelist.hasLevel(player, this.plugin.thisPlug))
      return;
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
    }if ((level < this.plugin.LCConfiguration.WoodHoe) && (iih == 290)) {
      LCChat.warn(player, "Cannot use this tool. Required Level:" + 
        this.plugin.LCConfiguration.WoodHoe);
      event.setCancelled(true);
      return;
    }if ((level < this.plugin.LCConfiguration.DiamondHoe) && (iih == 293)) {
      LCChat.warn(player, "Cannot use this tool. Required Level:" + 
        this.plugin.LCConfiguration.DiamondHoe);
      event.setCancelled(true);
      return;
    }if ((level < this.plugin.LCConfiguration.StoneHoe) && (iih == 291)) {
      LCChat.warn(player, "Cannot use this tool. Required Level:" + 
        this.plugin.LCConfiguration.StoneHoe);
      event.setCancelled(true);
      return;
    }if ((level < this.plugin.LCConfiguration.TillLevel) && 
      ((m == Material.GRASS) || (m == Material.DIRT)) && (isHoe(iih))) {
      LCChat.warn(player, "Cannot cut this block. Required Level:" + 
        this.plugin.LCConfiguration.TillLevel);
      event.setCancelled(true);
      return;
    }
    if (event.getAction() == Action.RIGHT_CLICK_AIR
    	    || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
    	List<Block> list = event.getPlayer().getLineOfSight(
				getTransparentBlockIdList(), 6);
    	   ArrayList<Entity> entityList = new ArrayList<Entity>();
    	   for (Block block : list) {
    	    for (Entity e : block.getWorld().getEntities()) {
    	     if (e.getEntityId() != event.getPlayer().getEntityId()) {
    	      if (e.getLocation()
    	        .toVector()
    	        .distanceSquared(block.getLocation().toVector()) < 1) {
    	       entityList.add(e);

    	      }
    	     }
    	    }
    	   }
    	   Entity best = null;

    	  double distance = Double.MAX_VALUE;
    	   for (Entity e : entityList) {
    	    if (e.getLocation()
    	      .toVector()
    	      .distanceSquared(
    	        event.getPlayer().getLocation().toVector()) < distance) {
    	     distance = e
    	       .getLocation()
    	       .toVector()
    	       .distanceSquared(
    	         event.getPlayer().getLocation().toVector());
    	     best = e;
    	    }
    	   }
    	   if (best != null) {
    	    //FEED ME PL0X
    	   }	   
    }
  

    double gained = 0.0D;
    if (((m == Material.GRASS) || (m == Material.DIRT)) && (isHoe(iih))) {
      gained = this.plugin.LCConfiguration.ExpPerTill;
    }  	

    if (gained == 0.0D)
      return;
    LevelFunctions.addExp(player, this.plugin.thisPlug, gained);
  }

  public void onBlockPlace(BlockPlaceEvent event) {
    if (event.isCancelled())
      return; 
  }

  public boolean isHoe(int i) {
    return (i == 290) || (i == 291) || (i == 292) || (i == 293) || (i == 294);
  }
  
  public void onPlayerEggThrow(PlayerEggThrowEvent event) {
	   if (!(event.isHatching()))
	   return;
	    if (!Whitelist.worldCheck(event.getPlayer().getWorld())) return;
	   if (!Whitelist.hasLevel(event.getPlayer(), this.plugin.thisPlug)) return;
	        if (!(event.getPlayer() instanceof Player)) 
	        	return; 

	        LevelFunctions.addExp((Player) event.getPlayer(),plugin.thisPlug,50);
	   }
  public static HashSet<Byte> getTransparentBlockIdList() {
	   if (transparentBlockList == null) {
	    HashSet<Byte> list = new HashSet<Byte>();
	    for (Material m : Material.values()) {
	     if (m.isBlock() && !isSolidMaterial((m))) {
	      list.add((byte) m.getId());
	     }
	    }
	    transparentBlockList = list;
	    return list;
	   }
	   return transparentBlockList;
	  }

	  public static boolean isSolidMaterial(Material m) {
	   return m == Material.STONE || m == Material.GRASS || m == Material.DIRT
	     || m == Material.COBBLESTONE || m == Material.WOOD
	     || m == Material.BEDROCK || m == Material.SAND
	     || m == Material.GRAVEL || m == Material.GOLD_ORE
	     || m == Material.IRON_ORE || m == Material.COAL_ORE
	     || m == Material.LOG || m == Material.LEAVES
	     || m == Material.SPONGE || m == Material.LAPIS_ORE
	     || m == Material.LAPIS_BLOCK || m == Material.DISPENSER
	     || m == Material.SANDSTONE || m == Material.NOTE_BLOCK
	     || m == Material.WOOL || m == Material.GOLD_BLOCK
	     || m == Material.IRON_BLOCK || m == Material.DOUBLE_STEP
	     || m == Material.STEP || m == Material.BRICK
	     || m == Material.TNT || m == Material.BOOKSHELF
	     || m == Material.MOSSY_COBBLESTONE || m == Material.OBSIDIAN
	     || m == Material.MOB_SPAWNER || m == Material.WOOD_STAIRS
	     || m == Material.CHEST || m == Material.DIAMOND_ORE
	     || m == Material.DIAMOND_BLOCK || m == Material.WORKBENCH
	     || m == Material.SOIL || m == Material.FURNACE
	     || m == Material.BURNING_FURNACE
	     || m == Material.COBBLESTONE_STAIRS
	     || m == Material.REDSTONE_ORE
	     || m == Material.GLOWING_REDSTONE_ORE || m == Material.ICE
	     || m == Material.SNOW_BLOCK || m == Material.CACTUS
	     || m == Material.CLAY || m == Material.JUKEBOX
	     || m == Material.FENCE || m == Material.PUMPKIN
	     || m == Material.NETHERRACK || m == Material.SOUL_SAND
	     || m == Material.GLOWSTONE || m == Material.JACK_O_LANTERN
	     || m == Material.CAKE_BLOCK

	   ;
	  }

}