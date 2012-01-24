package me.f1337.lcmining;



import me.f1337.levelcraftcore.LCChat;
import me.f1337.levelcraftcore.LevelFunctions;
import me.f1337.levelcraftcore.Whitelist;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
//LevelCraft BlockListener Handles the Events.
public class LCBlockListener extends BlockListener {
	public LCMining plugin;

	public LCBlockListener(LCMining instance) {
		plugin = instance;
	}
	//onBlockBreak. Called when a block is broken.
	public void onBlockBreak(BlockBreakEvent event) {
		//If the event isCancelled don't bother and return.
		if (event.isCancelled()) {
			return;
		}
		//if the event is in a NonLC world don't bother and return.
		if(!Whitelist.worldCheck(event.getBlock().getWorld()))return;
		//Grab the player of the event.
		Player player = event.getPlayer();
		//Grab the item the player is holding.
		int iih = player.getItemInHand().getTypeId();
		//Grab the material of the block broken.
		Material m = event.getBlock().getType();
		//If the player dosn't have the effects of this level. Don't bother and return.
		if(!Whitelist.hasLevel(player, plugin.thisPlug)) return;
		//If the item in hand is not one of these of it is not a block. Don't waste time/RAM. :)
		if (iih != 257 && iih != 285 && iih != 278 && iih != 274 && iih != 270
				&& m != Material.STONE && m != Material.COBBLESTONE
				&& m != Material.REDSTONE_ORE && m != Material.IRON_ORE
				&& m != Material.GOLD_ORE && m != Material.DIAMOND_ORE
				&& m != Material.SANDSTONE && m != Material.MOSSY_COBBLESTONE
				&& m != Material.COAL_ORE && m != Material.LAPIS_ORE
				&& m != Material.OBSIDIAN && m != Material.NETHERRACK
                                && m != Material.NETHER_BRICK && m != Material.ENDER_STONE)
			return;
		//Grab the current level of the player.
        int level = LevelFunctions.getLevel(player, plugin.thisPlug);
        //If the level is less than the level for this tool and they are holding the tool do this:
		if (level < plugin.LCConfiguration.IronPick && iih == 257) {
			//Warn the player they cannot use the tool and state the required level.
			LCChat.warn(player,"Cannot use this tool. Required Level:"
					+ plugin.LCConfiguration.IronPick);
			//Cancel the event and return.
			event.setCancelled(true);
			return;
			//Repeat for all tools.
		} else if (level < plugin.LCConfiguration.GoldPick && iih == 285) {
			LCChat.warn(player,"Cannot use this tool. Required Level:"
					+ plugin.LCConfiguration.GoldPick);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.DiamondPick && iih == 278) {
			LCChat.warn(player,"Cannot use this tool. Required Level:"
					+ plugin.LCConfiguration.DiamondPick);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.StonePick && iih == 274) {
			LCChat.warn(player,"Cannot use this tool. Required Level:"
					+ plugin.LCConfiguration.StonePick);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.WoodPick && iih == 270) {
			LCChat.warn(player,"Cannot use this tool. Required Level:"
					+ plugin.LCConfiguration.WoodPick);
			event.setCancelled(true);
			return;
			//If the level is less than the level for the block and the block they destroyed is that block. Do this:
		} else if (level < plugin.LCConfiguration.StoneLevel && m == Material.STONE) {
			//Warn the user they cannot mine the block and state the level of the block.
			LCChat.warn(player,"Cannot mine this block. Required Level:"
					+ plugin.LCConfiguration.StoneLevel);
			//Set the event to cancelled and return.
			event.setCancelled(true);
			return;
			
		//Repeate for all blocks.
		} else if (level < plugin.LCConfiguration.CobbleLevel
				&& m == Material.COBBLESTONE) {
			LCChat.warn(player,"Cannot mine this block. Required Level:"
					+ plugin.LCConfiguration.CobbleLevel);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.RedLevel
				&& m == Material.REDSTONE_ORE) {
			LCChat.warn(player,"Cannot mine this block. Required Level:"
					+ plugin.LCConfiguration.RedLevel);

			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.IronLevel && m == Material.IRON_ORE) {
			LCChat.warn(player,"Cannot mine this block. Required Level:"
					+ plugin.LCConfiguration.IronLevel);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.GoldLevel && m == Material.GOLD_ORE) {
			LCChat.warn(player,"Cannot mine this block. Required Level:"
					+ plugin.LCConfiguration.GoldLevel);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.DiamondLevel
				&& m == Material.DIAMOND_ORE) {
			LCChat.warn(player,"Cannot mine this block. Required Level:"
					+ plugin.LCConfiguration.DiamondLevel);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.SandStoneLevel
				&& m == Material.SANDSTONE) {
			LCChat.warn(player,"Cannot mine this block. Required Level:"
					+ plugin.LCConfiguration.SandStoneLevel);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.CoalLevel && m == Material.COAL_ORE) {
			LCChat.warn(player,"Cannot mine this block. Required Level:"
					+ plugin.LCConfiguration.CoalLevel);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.MossLevel
				&& m == Material.MOSSY_COBBLESTONE) {
			LCChat.warn(player,"Cannot mine this block. Required Level:"
					+ plugin.LCConfiguration.MossLevel);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.LapisLevel
				&& m == Material.LAPIS_ORE) {
			LCChat.warn(player,"Cannot mine this block. Required Level:"
					+ plugin.LCConfiguration.LapisLevel);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.NetherLevel
				&& m == Material.NETHERRACK) {
			LCChat.warn(player,"Cannot mine this block. Required Level:"
					+ plugin.LCConfiguration.NetherLevel);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.ObsidianLevel
				&& m == Material.OBSIDIAN) {
			LCChat.warn(player,"Cannot mine this block. Required Level:"
					+ plugin.LCConfiguration.ObsidianLevel);
			event.setCancelled(true);
			return;
                } else if  (level < plugin.LCConfiguration.NetherBrickLevel
                                 && m == Material.NETHER_BRICK) {
                        LCChat.warn(player,"Cannot mine this block. Required Level:"
                                        + plugin.LCConfiguration.NetherBrickLevel);
                        event.setCancelled(true);
                        return;
                } else if   (level < plugin.LCConfiguration.EndStoneLevel
                                  && m == Material.ENDER_STONE) {
                        LCChat.warn(player,"Cannot mine this block. Required Level:"
                                         + plugin.LCConfiguration.EndStoneLevel);
                        event.setCancelled(true);
                        return;
		}
        //If the player managed to go through all that ^^. Do this:
		//Start a double set at 0.
		double gained = 0;
		//if the material is a block registered,
		if (m == Material.STONE) {
         //Set the gained exp to that of the block.
			gained = plugin.LCConfiguration.ExpPerStone;
			//Repeat for all registered blocks.
		}else
		if (m == Material.OBSIDIAN) {

			gained = plugin.LCConfiguration.ExpPerObsidian;
		}else
		if (m == Material.MOSSY_COBBLESTONE) {

			gained = plugin.LCConfiguration.ExpPerMossStone;
		}else
		if (m == Material.COBBLESTONE) {
			gained = plugin.LCConfiguration.ExpPerCobble;
		}else
		if (m == Material.GOLD_ORE) {

			gained = plugin.LCConfiguration.ExpPerGoldOre;
		}else
		if (m == Material.IRON_ORE) {
			gained = plugin.LCConfiguration.ExpPerIronOre;
		}else
		if (m == Material.LAPIS_ORE) {

			gained = plugin.LCConfiguration.ExpPerLapisOre;
		}else
		if (m == Material.COAL_ORE) {

			gained = plugin.LCConfiguration.ExpPerCoalOre;
		}else

		if (m == Material.REDSTONE_ORE) {

			gained = plugin.LCConfiguration.ExpPerRedstone;
		}else
		if (m == Material.GLOWING_REDSTONE_ORE) {

			gained = plugin.LCConfiguration.ExpPerRedstone;
		}else
		if (m == Material.NETHERRACK) {

			gained = plugin.LCConfiguration.ExpPerNetherrack;
		}else
		if (m == Material.DIAMOND_ORE) {

			gained = plugin.LCConfiguration.ExpPerDiamondOre;
		}else
		if (m == Material.SANDSTONE) {

			gained = plugin.LCConfiguration.ExpPerSandStone;
                if (m == Material.NETHER_BRICK) {
                    
                        gained = plugin.LCConfiguration.ExpPerNetherBrick;
                }else
                if (m == Material.ENDER_STONE) {
                    
                        gained = plugin.LCConfiguration.ExpPerEndStone;
                }
		}
		//If they gained nothing. (Not really possible but hey.) Return.
		if(gained==0) return;
		//Add experience to the user.
		LevelFunctions.addExp(player, plugin.thisPlug, gained);

	}

}
