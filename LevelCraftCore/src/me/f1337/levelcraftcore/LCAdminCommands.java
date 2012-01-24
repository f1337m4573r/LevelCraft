package me.f1337.levelcraftcore;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class LCAdminCommands {
	public LevelCraftCore plugin;

	public LCAdminCommands(LevelCraftCore instance) {
		plugin = instance;
	}

	@SuppressWarnings("static-access")
	public void Help(Player sender) {
		plugin.LCChat.topBar(sender);
		if(plugin.Permissions.hasAdminCommand(sender, "setlvl")) plugin.LCChat.info(sender,
				"/lvl admin setlvl <Ref> <Player> <Level> - "
						+ plugin.lang.SetLevelForPlayers);
		if(plugin.Permissions.hasAdminCommand(sender, "setexp")) 	plugin.LCChat.info(sender, "/lvl admin setexp <Ref> <Player> <Exp> - "
				+ plugin.lang.SetExperienceForPlayers);
		if(plugin.Permissions.hasAdminCommand(sender, "getexp"))	plugin.LCChat.info(sender, "/lvl admin getexp <Ref> <Player> - "
				+ plugin.lang.GetExperienceOfPlayer);
		if(plugin.Permissions.hasAdminCommand(sender, "getlvl"))plugin.LCChat.info(sender, "/lvl admin getlvl <Ref> <Player> - "
				+ plugin.lang.GetLevelOfPlayer);
		if(plugin.Permissions.hasAdminCommand(sender, "reset"))plugin.LCChat.info(sender, "/lvl admin reset <Ref> <Player>  - "
				+ plugin.lang.ResetExperience);
		if(plugin.Permissions.hasAdminCommand(sender, "reload"))plugin.LCChat.info(sender,
				"/lvl admin reload  - Reloads LevelCraftCore");
		if(plugin.Permissions.hasAdminCommand(sender, "purge"))plugin.LCChat.info(sender,
		"/lvl admin purge  - Removes old Players.");
		// plugin.LCChat.info(sender, "/lvl admin reload - Shows this.");
		plugin.LCChat.info(sender, "/lvl admin - " + plugin.lang.ShowsThis);
	}

	@SuppressWarnings("static-access")
	public void determineMethid(Player sender, String[] args) {
		if (args.length >= 4) {
			if (args[1].equalsIgnoreCase("setlvl") && args.length == 5) {
				if (plugin.LCAdminCommands.setLevel(args[2], args[3], args[4],
						sender)) {
					plugin.LCChat.good(sender, plugin.lang.LevelSetSuccess);
					return;
				} else {
					plugin.LCChat.warn(sender, plugin.lang.LevelSetFalse);
					return;
				}
			} else if (args[1].equalsIgnoreCase("setexp") && args.length == 5) {
				if (plugin.LCAdminCommands.setExp(args[2], args[3], args[4],
						sender)) {
					plugin.LCChat
							.good(sender, plugin.lang.ExperienceSetSuccess);
					return;
				} else {
					plugin.LCChat.warn(sender, plugin.lang.ExperienceSetFalse);
					return;
				}
			} else if (args[1].equalsIgnoreCase("getlvl") && args.length == 4) {
				plugin.LCChat.good(sender, plugin.LCAdminCommands.getLevel(
						args[2], args[3], sender));
			} else if (args[1].equalsIgnoreCase("getexp") && args.length == 4) {
				plugin.LCChat
						.good(sender, plugin.LCAdminCommands.getExp(args[2],
								args[3], sender));
			} else if (args[1].equalsIgnoreCase("reset") && args.length == 4) {
				if (plugin.LCAdminCommands.reset(args[2], args[3], sender)) {
					plugin.LCChat.good(sender,
							plugin.lang.ExperienceResetSuccess);
					return;
				} else {
					plugin.LCChat
							.warn(sender, plugin.lang.ExperienceResetFalse);
					return;
				}
			} else {
				plugin.LCAdminCommands.Syntax(sender);
				return;
			}

		} else if (args[1].equalsIgnoreCase("reload")) {
			if (plugin.Reload()) {
				plugin.LCChat.good(sender, "Reload Successful");
				return;
			} else {
				plugin.LCChat.bad(sender, "Reload Unsuccessful");
				return;
			}
		} else if (args[1].equalsIgnoreCase("purge")) {
			plugin.LCAdminCommands.Purge(sender);
		}

	}

	/*
	 * boolean reload() { for(Plugin p: plugin.LevelNames.keySet()){
	 * plugin.getPluginLoader().disablePlugin(p); } plugin.LevelAuthors.clear();
	 * plugin.LevelFiles.clear(); plugin.LevelUnlocks.clear();
	 * plugin.LevelReferenceKeys.clear(); plugin.LevelIndexes.clear();
	 * for(Plugin p: plugin.LevelNames.keySet()){ //
	 * plugin.getPluginLoader().enablePlugin(p); } plugin.LevelNames.clear();
	 * plugin.onEnable(); return true;
	 * 
	 * }
	 */
	@SuppressWarnings("static-access")
	private boolean reset(String ref, String p, Player pl) {
		if(!plugin.Permissions.hasAdminCommand(pl, "reset")) return false;
		if (plugin.LCAdminCommands.setExp(ref, p, "0", pl))
			return true;
		return false;
	}

	@SuppressWarnings("static-access")
	private String getExp(String ref, String p, Player pl) {
		if(!plugin.Permissions.hasAdminCommand(pl, "getexp")) return "You do not have Permission";
		List<Player> players = plugin.getServer().matchPlayer(p);
		if (players.size() == 0) {
			plugin.LCChat.warn(pl, plugin.lang.NoMatch);
			return "Error.";
		} else if (players.size() != 1) {
			plugin.LCChat.warn(pl, plugin.lang.TooMany);
			return "Error.";
		} else {
			Player leveler = players.get(0);
			for (Plugin plug : plugin.LevelReferenceKeys.keySet()) {
				String[] reference = plugin.LevelReferenceKeys.get(plug);
				if (plugin.Tools.containsValue(reference, ref)
						&& plugin.Permissions.hasLevel(leveler, plug)) {
					return leveler.getName() + "'s "
							+ plugin.LevelNames.get(plug)
							+ plugin.lang.ExperienceIs
							+ plugin.LevelFunctions.getExp(leveler, plug);
				}
			}

		}
		return "Error";
	}

	@SuppressWarnings("static-access")
	private String getLevel(String ref, String p, Player pl) {
		if(!plugin.Permissions.hasAdminCommand(pl, "getlvl")) return "You do not have Permission";
		List<Player> players = plugin.getServer().matchPlayer(p);
		if (players.size() == 0) {
			plugin.LCChat.warn(pl, plugin.lang.NoMatch);
			return "Error.";
		} else if (players.size() != 1) {
			plugin.LCChat.warn(pl, plugin.lang.TooMany);
			return "Error.";
		} else {
			Player leveler = players.get(0);
			for (Plugin plug : plugin.LevelReferenceKeys.keySet()) {
				String[] reference = plugin.LevelReferenceKeys.get(plug);
				if (plugin.Tools.containsValue(reference, ref)
						&& plugin.Permissions.hasLevel(leveler, plug)) {
					return leveler.getName() + "'s "
							+ plugin.LevelNames.get(plug) + plugin.lang.LevelIs
							+ plugin.LevelFunctions.getLevel(leveler, plug);
				}
			}

		}
		return "Error";
	}

	@SuppressWarnings("static-access")
	private boolean setExp(String ref, String p, String exp, Player pl) {
		if(!plugin.Permissions.hasAdminCommand(pl, "setexp")) return false;
		double newexp = Double.parseDouble(exp);
		List<Player> players = plugin.getServer().matchPlayer(p);
		if (players.size() == 0) {
			plugin.LCChat.warn(pl, plugin.lang.NoMatch);
			return false;
		} else if (players.size() != 1) {
			plugin.LCChat.warn(pl, plugin.lang.TooMany);
			return false;
		} else {
			Player leveler = players.get(0);
			for (Plugin plug : plugin.LevelReferenceKeys.keySet()) {
				String[] reference = plugin.LevelReferenceKeys.get(plug);
				if (plugin.Tools.containsValue(reference, ref)
						&& plugin.Permissions.hasLevel(leveler, plug)) {
					plugin.LevelFunctions.updateExp(leveler, plug, newexp);
					return true;
				}
			}

		}
		return false;
	}

	@SuppressWarnings("static-access")
	private boolean setLevel(String ref, String p, String lvl, Player pl) {
		if(!plugin.Permissions.hasAdminCommand(pl, "setlvl")) return false;
		int newlvl = Integer.parseInt(lvl);
		List<Player> players = plugin.getServer().matchPlayer(p);
		if (players.size() == 0) {
			plugin.LCChat.warn(pl, plugin.lang.NoMatch);
			return false;
		} else if (players.size() != 1) {
			plugin.LCChat.warn(pl, plugin.lang.TooMany);
			return false;
		} else {
			Player leveler = players.get(0);
			for (Plugin plug : plugin.LevelReferenceKeys.keySet()) {
				String[] reference = plugin.LevelReferenceKeys.get(plug);
				if (plugin.Tools.containsValue(reference, ref)
						&& plugin.Permissions.hasLevel(leveler, plug)) {
					plugin.LevelFunctions.updateLevel(leveler, plug, newlvl);
					return true;
				}
			}

		}
		return false;
	}

	@SuppressWarnings("static-access")
	public void Syntax(Player sender) {
		plugin.LCChat.warn(sender, plugin.lang.SyntaxA);
		return;
	}

	@SuppressWarnings("static-access")
	public void Purge(Player sender) {
		if(!plugin.Permissions.hasAdminCommand(sender, "purge")) {
			plugin.LCChat.bad(sender, "You do not have permission..");
			return;
		}
		if (plugin.database.equalsIgnoreCase("FlatFile")) {
			if (plugin.FlatFile.purge()) {
				plugin.LCChat.good(sender, "FlatFiles Purged.");
			} else {
				plugin.LCChat.bad(sender, "Failed to Purge FlatFiles.");
			}
			return;
		} else {
			plugin.LCChat.bad(sender,
					"Purge only avaible for FlatFiles at the moment.");
		}
	}
}
