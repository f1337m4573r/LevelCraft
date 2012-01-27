package me.f1337.lcwoodcutting;



import me.f1337.levelcraftcore.LCChat;
import me.f1337.levelcraftcore.LevelFunctions;
import me.f1337.levelcraftcore.Whitelist;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Tree;

public class LCBlockListener extends BlockListener {
	public LCWoodCutting plugin;

	public LCBlockListener(LCWoodCutting instance) {
		plugin = instance;
	}
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.isCancelled()) {
			return;
		}
		if(!Whitelist.worldCheck(event.getBlock().getWorld()))return;
		Player player = event.getPlayer();
		int iih = player.getItemInHand().getTypeId();
		Material m = event.getBlock().getType();
		
		if(!Whitelist.hasLevel(player, plugin.thisPlug)) return;
		if (iih != 258 && iih != 286 && iih != 279 && iih != 275 && iih != 271
				&& m != Material.LOG && m != Material.WOOD)
			return;
        int level = LevelFunctions.getLevel(player, plugin.thisPlug);
		if (level < plugin.LCConfiguration.IronAxe && iih == 258) {
			LCChat.warn(player,"Cannot use this tool. Required Level:"
					+ plugin.LCConfiguration.IronAxe);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.GoldAxe && iih == 286) {
			LCChat.warn(player,"Cannot use this tool. Required Level:"
					+ plugin.LCConfiguration.GoldAxe);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.WoodAxe && iih == 271) {
			LCChat.warn(player,"Cannot use this tool. Required Level:"
					+ plugin.LCConfiguration.WoodAxe);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.DiamondAxe && iih == 279) {
			LCChat.warn(player,"Cannot use this tool. Required Level:"
					+ plugin.LCConfiguration.DiamondAxe);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.StoneAxe && iih == 275) {
			LCChat.warn(player,"Cannot use this tool. Required Level:"
					+ plugin.LCConfiguration.StoneAxe);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.PlankLevel && m == Material.WOOD) {
			LCChat.warn(player,"Cannot cut this block. Required Level:"
					+ plugin.LCConfiguration.PlankLevel);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.LogLevel
				&& m == Material.LOG && ((Tree)event.getBlock().getState().getData()).getSpecies() == TreeSpecies.GENERIC) {
			LCChat.warn(player,"Cannot cut this block. Required Level:"
					+ plugin.LCConfiguration.LogLevel);
			event.setCancelled(true);
			return;
		} else if (level < plugin.LCConfiguration.RedwoodLevel
				&& m == Material.LOG && ((Tree)event.getBlock().getState().getData()).getSpecies() == TreeSpecies.REDWOOD) {
			LCChat.warn(player,"Cannot cut this block. Required Level:"
					+ plugin.LCConfiguration.RedwoodLevel);
			event.setCancelled(true);
			return;
		}  else if (level < plugin.LCConfiguration.BirchLevel
				&& m == Material.LOG && ((Tree)event.getBlock().getState().getData()).getSpecies() == TreeSpecies.BIRCH) {
			LCChat.warn(player,"Cannot cut this block. Required Level:"
					+ plugin.LCConfiguration.BirchLevel);
			event.setCancelled(true);
			return;
		}
		double gained = 0;
		if (m == Material.LOG) {

			gained = plugin.LCConfiguration.ExpPerLog;
		}
		if (m == Material.WOOD) {

			gained = plugin.LCConfiguration.ExpPerPlank;
		}
        if(gained == 0) return;
		LevelFunctions.addExp(player, plugin.thisPlug, gained);

	}
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.isCancelled()) {
			return;
		}
	}
}
