package me.f1337.levelcraftcore;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import com.nijikokun.bukkit.Permissions.Permissions;

public class Whitelist {
	public static LevelCraftCore plugin;

	public Whitelist(LevelCraftCore instance) {
		plugin = instance;
	}

	@SuppressWarnings("static-access")
	public void LoadPerms() {
		Plugin test = plugin.getServer().getPluginManager()
				.getPlugin("Permissions");
		if (this.plugin.PermissionH == null) {
			if (test != null) {
				this.plugin.PermissionH = ((Permissions) test).getHandler();
				plugin.PermissionUse = true;
				plugin.logger.info("[LC] Using Permissions.");
			} else {
				plugin.logger
						.info("[LC] No Permissions found enabling all levels.");
				plugin.logger.info("[LC] Admin commands for OP.");
				plugin.PermissionUse = false;
			}
		}

	}

	public static boolean hasAdminCommand(Player p, String c) {
		if (!plugin.PermissionUse) {
			if (p.isOp())
				return true;
			return false;
		}
		if (plugin.PermissionH.has(p, "lc.admin." + c))
			return true;
		return false;
	}

	/*
	 * public int cap(Player s,Plugin p){
	 * 
	 * if (!plugin.PermissionUse){ return plugin.LevelCap; } String level =
	 * plugin.LevelNames.get(p); plugin.PermissionH.g
	 * if(plugin.PermissionH.has(s, "lc.lcap."+level.toLowerCase())){ //
	 * plugin.PermissionH.getInfoInteger(arg0, arg1, arg2, arg3) //
	 * plugin.PermissionH. }
	 * 
	 * 
	 * return plugin.LevelCap; }
	 */

	public static boolean hasLevel(Player s, Plugin p) {
		for (String playerN : plugin.Bypassers) {
			if (playerN.equalsIgnoreCase(s.getName()))
				return false;
		}
		try {
			if (!plugin.PermissionUse)
				return true;
			if (plugin.PermissionH.has(s, "lc.unlevel."
					+ plugin.LevelNames.get(p).toLowerCase()))
				return true; // Line !
			if (plugin.PermissionH.has(s, "lc.level."
					+ plugin.LevelNames.get(p).toLowerCase()))
				return true; // Line !
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean hasLevelExp(Player s, Plugin p) {
		for (String playerN : plugin.Bypassers) {
			if (playerN.equalsIgnoreCase(s.getName()))
				return false;
		}
		if (!plugin.PermissionUse)
			return true;
		if (plugin.PermissionH.has(s, "lc.level."
				+ plugin.LevelNames.get(p).toLowerCase()))
			
			return true;
		return false;

	}

	public static boolean worldCheck(World world) {
		String w = world.getName();
		for (String s : plugin.Worlds) {
			if (w.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}

	public boolean isAdmin(CommandSender s) {
		if (!plugin.PermissionUse && s.isOp())
			return true;
		if (!plugin.PermissionUse)
			return false;
		if (s instanceof Player) {
			if (plugin.PermissionH.has((Player) s, "lc.admin")
					|| plugin.PermissionH.has((Player) s, "lc.admin.*")
					|| plugin.PermissionH.has((Player) s, "lc.admin.purge")
					|| plugin.PermissionH.has((Player) s, "lc.admin.setexp")
					|| plugin.PermissionH.has((Player) s, "lc.admin.reload")
					|| plugin.PermissionH.has((Player) s, "lc.admin.getexp")
					|| plugin.PermissionH.has((Player) s, "lc.admin.getlvl")
					|| plugin.PermissionH.has((Player) s, "lc.admin.reset")
					|| plugin.PermissionH.has((Player) s, "lc.admin.setlvl")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	public boolean canShout(CommandSender s) {
		if (!plugin.PermissionUse)
			return true;
		if (plugin.PermissionH.has((Player) s, "lc.shout"))
			return true;
		return false;
	}
}
