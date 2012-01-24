package me.f1337.levelcraftcore;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LCChat {
	public static LevelCraftCore plugin;

	public LCChat(LevelCraftCore instance) {
		plugin = instance;
	}

	public static void topBar(CommandSender p) {
		p.sendMessage(ChatColor.valueOf(plugin.c1)
				+ "[LC] ---LevelCraftPlugin (C)2011-2012--- ");
	}

	public static void info(CommandSender p, String s) {
		p.sendMessage(ChatColor.valueOf(plugin.c1) + "[LC] "
				+ ChatColor.valueOf(plugin.c2) + s);
	}

	public static void warn(CommandSender p, String s) {
		p.sendMessage(ChatColor.valueOf(plugin.c1) + "[LC] "
				+ ChatColor.valueOf(plugin.c4) + s);
	}

	public static void good(CommandSender p, String s) {
		p.sendMessage(ChatColor.valueOf(plugin.c1) + "[LC] "
				+ ChatColor.valueOf(plugin.c3) + s);
	}

	public void bad(CommandSender p, String s) {
		p.sendMessage(ChatColor.valueOf(plugin.c1) + "[LC] "
				+ ChatColor.valueOf(plugin.c4) + s);
	}

	@SuppressWarnings("static-access")
	public static void broadcast(String s) {
		plugin.LCChat.broadcastWorld(s);
	}

	@SuppressWarnings("static-access")
	public static void broadcastWorld(String s) {
		for (Player p : plugin.getServer().getOnlinePlayers()) {
			if (plugin.Permissions.worldCheck(p.getWorld())) {
				p.sendMessage(ChatColor.valueOf(plugin.c1) + "[LC] "
						+ ChatColor.valueOf(plugin.c2) + s);
			}
		}
	}

}
