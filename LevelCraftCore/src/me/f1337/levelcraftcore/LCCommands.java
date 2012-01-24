package me.f1337.levelcraftcore;

import java.io.File;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.AppearanceManager;

public class LCCommands {
	public LevelCraftCore plugin;

	public LCCommands(LevelCraftCore instance) {
		plugin = instance;
	}

	@SuppressWarnings("static-access")
	public void showStat(Player s, String string) {
		for (Plugin p : plugin.LevelReferenceKeys.keySet()) {
			String[] reference = plugin.LevelReferenceKeys.get(p);
			if (plugin.Tools.containsValue(reference, string)
					&& plugin.Permissions.hasLevel(s, p)) {
				plugin.LCChat.topBar(s);
				plugin.LCChat.info(s,
						plugin.LevelNames.get(p) + plugin.lang.LevelS
								+ plugin.LevelFunctions.getLevel(s, p));
				plugin.LCChat.info(
						s,
						plugin.LevelNames.get(p)
								+ plugin.lang.Experience
								+ plugin.Tools
										.roundTwoDecimals(plugin.LevelFunctions
												.getExp(s, p)));
				plugin.LCChat.info(
						s,
						plugin.lang.ExperienceToNextLevel
								+ plugin.Tools
										.roundTwoDecimals(plugin.LevelFunctions
												.getExpLeft(s, p)));
				return;
			}
		}
		plugin.LCChat.info(s, plugin.lang.None);
	}

	@SuppressWarnings("static-access")
	public void listLevels(Player player) {
		String s = plugin.lang.YourActiveLevels;
		boolean one = false;
		for (Plugin p : plugin.LevelNames.keySet()) {
			if (one && plugin.Permissions.hasLevelExp(player, p))
				s = s + ",";
			if (plugin.Permissions.hasLevelExp(player, p)) {
				s = s + plugin.LevelNames.get(p) + "("
						+ plugin.LevelIndexes.get(p) + ")";
				one = true;
			}
		}
		plugin.LCChat.info(player, s);
	}

	@SuppressWarnings("static-access")
	public void about(CommandSender sender) {
		plugin.LCChat.topBar(sender);
		plugin.LCChat.info(sender, "/lvl list - " + plugin.lang.ShowsActive);
		plugin.LCChat.info(sender, "/lvl notify - " + plugin.lang.ToggleNote);
		plugin.LCChat.info(sender,
				"/lvl " + plugin.Tools.getIndexBar((Player) sender) + " - "
						+ plugin.lang.ShowsLevelStats);
		plugin.LCChat.info(sender, "/lvl unlocks [REF] <Page> - "
				+ plugin.lang.ShowsToolBlock);
		plugin.LCChat.info(sender, "/lvl exp [REF] <Page> - "
				+ plugin.lang.ShowsExp);
		plugin.LCChat.info(sender, "/lvl shout [REF] - " + plugin.lang.Shout);
		plugin.LCChat.info(sender, "/lvl total - " + plugin.lang.ShowsTotal);
		plugin.LCChat.info(sender, "/lvl all - " + plugin.lang.ShowsAll);
		plugin.LCChat
				.info(sender, "/lvl rank [REF] - " + plugin.lang.ShowsRank);
		plugin.LCChat.info(sender, "/lvl top [REF] - " + plugin.lang.ShowsTop);
		plugin.LCChat
				.info(sender, "/lvl help [REF] - " + plugin.lang.ShowsHelp);
		
		
		if(plugin.EnableCapes){
		plugin.LCChat
		.info(sender, "/lvl cape [REF] - Selects Master Cape");
		}
		plugin.LCChat.info(sender, "/lvl  - " + plugin.lang.ShowsThis);
		if (plugin.Permissions.isAdmin(sender)) {
			plugin.LCChat.good(sender, "/lvl admin - "
					+ plugin.lang.AdminCommands);
		}
		return;

	}

	@SuppressWarnings("static-access")
	public void credits(CommandSender sender) {
		plugin.LCChat.topBar(sender);
		plugin.LCChat.good(sender, "LevelCraftCore by Samkio Now by f1337_m4573r.");
		plugin.LCChat.good(sender, "SkillCapes by Indy12.");
		for (Plugin p : plugin.LevelAuthors.keySet()) {
			plugin.LCChat.good(sender, plugin.LevelNames.get(p) + " by "
					+ plugin.LevelAuthors.get(p) + ".");
		}
		return;

	}

	@SuppressWarnings("static-access")
	public void determineMethod(Player sender, String[] args) {

		if (args[0].equalsIgnoreCase("list")) {
			plugin.LCCommands.listLevels(sender);
			return;
		} else if (args[0].equalsIgnoreCase("total")) {
			plugin.LCCommands.Total(sender);
			return;
		} else if (args[0].equalsIgnoreCase("all")) {
			plugin.LCCommands.All(sender);
			return;
		} else if (args[0].equalsIgnoreCase("credits")) {
			plugin.LCCommands.credits(sender);
			return;
		} else if (args[0].equalsIgnoreCase("notify")) {
			plugin.LCCommands.Notify(sender);
			return;
		} else if (args[0].equalsIgnoreCase("admin")) {
			plugin.LCCommands.Admin(sender, args);
			return;
		} else if (args[0].equalsIgnoreCase("shout")) {
			if (args.length < 2) {
				plugin.LCChat.info(sender, "Syntax: /level shout [REF]");
			} else {
				plugin.LCCommands.Shout(sender, args[1]);
			}
			return;
		} else if (args[0].equalsIgnoreCase("unlocks")) {
			if (args.length < 2) {
				plugin.LCChat.info(sender,
						"Syntax: /level unlocks [REF] <PageNo>");
			} else if (args.length < 3) {
				plugin.LCCommands.Unlocks(sender, args[1], 1);
			} else {
				plugin.LCCommands.Unlocks(sender, args[1],
						plugin.Tools.convertToInt(args[2]));
			}
			return;
		} else if (args[0].equalsIgnoreCase("exp")) {
			if (args.length < 2) {
				plugin.LCChat.info(sender, "Syntax: /level exp [REF] <PageNo>");
			} else if (args.length < 3) {
				plugin.LCCommands.Exp(sender, args[1], 1);
			} else {
				plugin.LCCommands.Exp(sender, args[1],
						plugin.Tools.convertToInt(args[2]));
			}
			return;
		} else if (args[0].equalsIgnoreCase("rank")) {
			if (args.length < 2) {
				plugin.LCChat.info(sender, "Syntax: /level rank [REF]");
			} else {
				plugin.LCCommands.Rank(sender, args[1]);
			}
			return;
		} else if (args[0].equalsIgnoreCase("help")) {
			if (args.length < 2) {
				plugin.LCChat.info(sender, "Syntax: /level help [REF]");
			} else {
				plugin.LCCommands.LevelHelp(sender, args[1]);
			}
			return;
		} else if (args[0].equalsIgnoreCase("cape") && plugin.EnableCapes) {
			if (args.length < 2) {
				plugin.LCChat.info(sender, "Syntax: /level cape [REF]");
			} else {
				plugin.LCCommands.cape(sender, args[1]);
			}
			return;
		} else if (args[0].equalsIgnoreCase("top")) {
			if (args.length < 2) {
				plugin.LCChat.info(sender, "Syntax: /level top [REF]");
			} else {
				plugin.LCCommands.top(sender, args[1]);
			}
			return;
		} else {
			if (args.length == 1) {
				plugin.LCCommands.showStat(sender, args[0]);
			} else {
				plugin.LCChat.info(sender,
						"No command found. /level - for all commands.");
			}

			return;

			/*
			 * }else if(args[0].equalsIgnoreCase("admin") &&
			 * args[1].equalsIgnoreCase("reload")){
			 * if(plugin.LCAdminCommands.reload()){ plugin.LCChat.good(sender,
			 * "LevelCraft Sucessfully Reloaded"); }else{
			 * plugin.LCChat.warn(sender, "LevelCraft Could not be Reloaded"); }
			 */
		}

	}

	@SuppressWarnings("static-access")
	private void cape(Player sender, String string) {
		for (Plugin p : plugin.LevelReferenceKeys.keySet()) {
			String[] reference = plugin.LevelReferenceKeys.get(p);
			if (plugin.Tools.containsValue(reference, string)
					&& plugin.Permissions.hasLevel(sender, p)) {
				if(plugin.LevelFunctions.getLevel(sender, p)>=plugin.LevelCap){
					File CapeFile = new File(plugin.getDataFolder() + "/Data/Cape.data");
					plugin.FlatFile.writeS(sender.getName(), CapeFile, plugin.LevelNames.get(p));
					plugin.LCChat.good(sender, "Set cape to: "+plugin.LevelNames.get(p));
					AppearanceManager appearM = SpoutManager.getAppearanceManager();
				    appearM.setGlobalCloak(sender, "http://cloud.github.com/downloads/samkio/Levelcraft/"+plugin.LevelNames.get(p)+".png");
				}else{
					plugin.LCChat.bad(sender, "You have not mastered this level.");
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	private void LevelHelp(Player sender, String string) {
		for (Plugin p : plugin.LevelReferenceKeys.keySet()) {
			String[] reference = plugin.LevelReferenceKeys.get(p);
			if (plugin.Tools.containsValue(reference, string)
					&& plugin.Permissions.hasLevel(sender, p)) {
				plugin.LCChat.topBar(sender);
				if (plugin.LevelHelp.get(p) == null) {
					plugin.LCChat.info(sender, plugin.lang.NoHelpFileYet);
					return;
				}
				for (String s : plugin.LevelHelp.get(p)) {
					plugin.LCChat.info(sender, s);
				}
				break;
			}
		}

	}

	@SuppressWarnings("static-access")
	private void top(Player sender, String string) {
		for (Plugin p : plugin.LevelReferenceKeys.keySet()) {
			String[] reference = plugin.LevelReferenceKeys.get(p);
			if (plugin.Tools.containsValue(reference, string)) {
				plugin.LCChat.info(sender, "==" + plugin.lang.TopPlayersIn
						+ " " + plugin.LevelNames.get(p) + "==");
				plugin.LCChat
						.info(sender,
								"1. "
										+ plugin.LevelFunctions.getPlayerAtPos(
												string, 1)+" ("+plugin.LevelFunctions.getLevel(plugin.getServer().getPlayer(plugin.LevelFunctions.getPlayerAtPos(
														string, 1)), p)+")");
				plugin.LCChat
						.info(sender,
								"2. "
										+ plugin.LevelFunctions.getPlayerAtPos(
												string, 2)+" ("+plugin.LevelFunctions.getLevel(plugin.getServer().getPlayer(plugin.LevelFunctions.getPlayerAtPos(
														string, 2)), p)+")");
				plugin.LCChat
						.info(sender,
								"3. "
										+ plugin.LevelFunctions.getPlayerAtPos(
												string, 3)+" ("+plugin.LevelFunctions.getLevel(plugin.getServer().getPlayer(plugin.LevelFunctions.getPlayerAtPos(
														string, 3)), p)+")");
				plugin.LCChat
						.info(sender,
								"4. "
										+ plugin.LevelFunctions.getPlayerAtPos(
												string, 4) +" ("+plugin.LevelFunctions.getLevel(plugin.getServer().getPlayer(plugin.LevelFunctions.getPlayerAtPos(
														string, 4)), p)+")");
				plugin.LCChat
						.info(sender,
								"5. "
										+ plugin.LevelFunctions.getPlayerAtPos(
												string, 5) +" ("+plugin.LevelFunctions.getLevel(plugin.getServer().getPlayer(plugin.LevelFunctions.getPlayerAtPos(
												string, 5)), p)+")");
				return;
			}
		}
		plugin.LCChat.warn(sender, plugin.lang.NoLevelFound);

	}

	@SuppressWarnings("static-access")
	private void Rank(Player sender, String string) {

		for (Plugin p : plugin.LevelReferenceKeys.keySet()) {
			String[] reference = plugin.LevelReferenceKeys.get(p);
			if (plugin.Tools.containsValue(reference, string)
					&& plugin.Permissions.hasLevel(sender, p)) {
				plugin.LCChat.info(sender, plugin.lang.YouAreNumber
						+ plugin.LevelFunctions.getPos(sender, string) + " in "
						+ plugin.LevelNames.get(p));
				return;
			}
		}
		plugin.LCChat.warn(sender, plugin.lang.NoLevelFound);

	}

	@SuppressWarnings("static-access")
	private void Exp(Player sender, String string, Integer page) {
		for (Plugin p : plugin.LevelReferenceKeys.keySet()) {
			String[] reference = plugin.LevelReferenceKeys.get(p);

			if (plugin.Tools.containsValue(reference, string)
					&& plugin.Permissions.hasLevel(sender, p)) {
				plugin.LCChat.topBar(sender);
				String[] Exp = plugin.LevelExp.get(p);
				int maxPages = (Exp.length / plugin.ExpLines);
				if (maxPages <= 0)
					maxPages = 1;
				if (page > maxPages) {
					plugin.LCChat.warn(sender, "No Page.");
					return;
				}
				plugin.LCChat.info(sender, "Showing Experience Table for "
						+ plugin.LevelNames.get(p) + ". Page " + page + " of "
						+ maxPages);
				int startingPoint = page * plugin.ExpLines - plugin.ExpLines;
				int endingPoint = startingPoint + plugin.ExpLines;
				if (endingPoint > Exp.length)
					endingPoint = Exp.length;
				for (int i = startingPoint; i < endingPoint; i++) {

					plugin.LCChat.info(sender, Exp[i]);

				}

				/*
				 * for (String s : plugin.LevelExp.get(p)) {
				 * plugin.LCChat.info(sender, s); }
				 */
				return;
			}
		}
		plugin.LCChat.warn(sender, plugin.lang.NoLevelFound);
	}

	@SuppressWarnings("static-access")
	private void Admin(Player sender, String[] args) {
		if (!plugin.Permissions.isAdmin(sender)) {
			plugin.LCChat.warn(sender, plugin.lang.YouDoNotHavePermission);
			return;
		}
		if (args.length <= 1) {
			plugin.LCAdminCommands.Help(sender);
			return;
		} else {
			plugin.LCAdminCommands.determineMethid(sender, args);
			return;
		}

	}

	@SuppressWarnings("static-access")
	private void Unlocks(Player sender, String string, Integer page) {
		for (Plugin p : plugin.LevelReferenceKeys.keySet()) {
			String[] reference = plugin.LevelReferenceKeys.get(p);
			if (plugin.Tools.containsValue(reference, string)
					&& plugin.Permissions.hasLevel(sender, p)) {
				plugin.LCChat.topBar(sender);
				String[] Unlocks = plugin.LevelUnlocks.get(p);
				int maxPages = (Unlocks.length / plugin.UnlockLines);
				if (maxPages <= 0)
					maxPages = 1;
				if (page > maxPages) {
					plugin.LCChat.warn(sender, "No Page.");
					return;
				}
				int[] UnlockLevel = plugin.LevelUnlocksLevel.get(p);
				int level = plugin.LevelFunctions.getLevel(sender, p);
				plugin.LCChat.info(sender, "Showing Unlocks for "
						+ plugin.LevelNames.get(p) + ". Page " + page + " of "
						+ maxPages);
				int startingPoint = page * plugin.UnlockLines
						- plugin.UnlockLines;
				int endingPoint = startingPoint + plugin.UnlockLines;
				if (endingPoint > Unlocks.length)
					endingPoint = Unlocks.length;
				for (int i = startingPoint; i < endingPoint; i++) {
					if (UnlockLevel[i] > level) {
						plugin.LCChat.warn(sender, Unlocks[i]);
					} else {
						plugin.LCChat.good(sender, Unlocks[i]);
					}
				}
				return;
			}

		}
		plugin.LCChat.warn(sender, plugin.lang.NoLevelFound);
	}

	@SuppressWarnings("static-access")
	private void Shout(Player sender, String string) {

		if (!plugin.Permissions.canShout(sender)) {
			plugin.LCChat.warn(sender, plugin.lang.YouDoNotHavePermission);
			return;
		}
		if (string.equalsIgnoreCase("total")) {
			boolean oneStat = false;
			int TotalLevel = 0;
			for (Plugin p : plugin.LevelNames.keySet()) {
				if (plugin.Permissions.hasLevel(sender, p)) {
					TotalLevel = TotalLevel
							+ plugin.LevelFunctions.getLevel(sender, p);
					oneStat = true;
				}
			}
			if (oneStat) {
				plugin.LCChat.broadcast(sender.getName() + "'s total "
						+ plugin.lang.LevelIs + +TotalLevel + ".");

			} else {
				plugin.LCChat.warn(sender, plugin.lang.NoLevelFound);
			}
			return;
		}
		for (Plugin p : plugin.LevelReferenceKeys.keySet()) {
			String[] reference = plugin.LevelReferenceKeys.get(p);
			if (plugin.Tools.containsValue(reference, string)
					&& plugin.Permissions.hasLevel(sender, p)) {
				plugin.LCChat.broadcast(sender.getName() + "'s "
						+ plugin.LevelNames.get(p) + plugin.lang.LevelIs
						+ plugin.LevelFunctions.getLevel(sender, p) + ".");
				return;
			}
		}
		plugin.LCChat.info(sender, "You do not have that level to shout.");

	}

	private void Notify(Player sender) {
		plugin.Tools.toggleNotify(sender);
		return;

	}

	@SuppressWarnings("static-access")
	private void Total(Player sender) {
		boolean oneStat = false;
		int TotalLevel = 0;
		double TotalExp = 0;
		String HighestLevel = plugin.lang.None;
		int HighestLevelInt = 0;
		for (Plugin p : plugin.LevelNames.keySet()) {
			if (plugin.Permissions.hasLevel(sender, p)) {
				TotalLevel = TotalLevel
						+ plugin.LevelFunctions.getLevel(sender, p);
				TotalExp = TotalExp + plugin.LevelFunctions.getExp(sender, p);
				if (HighestLevelInt < plugin.LevelFunctions.getLevel(sender, p)) {
					HighestLevel = plugin.LevelNames.get(p);
					HighestLevelInt = plugin.LevelFunctions.getLevel(sender, p);
				}
				oneStat = true;
			}
		}
		if (oneStat) {
			plugin.LCChat.topBar(sender);
			plugin.LCChat.info(sender, plugin.lang.TotalLevel + TotalLevel);
			plugin.LCChat.info(sender, plugin.lang.TotalExp + TotalExp);
			plugin.LCChat.info(sender, plugin.lang.HighestLevel + HighestLevel);

		} else {
			plugin.LCChat.warn(sender, plugin.lang.NoLevelFound);
		}
	}

	@SuppressWarnings("static-access")
	public void All(Player sender) {
		boolean oneStat = false;
		String levels = "";
		for (Plugin p : plugin.LevelNames.keySet()) {
			if (plugin.Permissions.hasLevel(sender, p)) {
				levels = levels + plugin.LevelNames.get(p) + "("
						+ plugin.LevelIndexes.get(p) + "): "
						+ plugin.LevelFunctions.getLevel(sender, p) + ".";
				oneStat = true;
			}
		}

		if (oneStat) {
			String[] lines = levels.split("\\.");
			plugin.LCChat.topBar(sender);
			for (int i = 0; i < lines.length; i++) {
				plugin.LCChat.info(sender, lines[i]);
			}
			return;
		} else {
			plugin.LCChat.warn(sender, plugin.lang.NoLevelFound);
		}

	}

}
