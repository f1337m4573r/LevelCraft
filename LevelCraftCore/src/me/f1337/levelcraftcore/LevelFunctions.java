package me.f1337.levelcraftcore;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.sound.SoundManager;

public class LevelFunctions {
	public static LevelCraftCore plugin;

	public LevelFunctions(LevelCraftCore instance) {
		plugin = instance;
	}

	public static Set<Plugin> getPluginsList() {
		return plugin.LevelNames.keySet();
	}

	public static boolean antiCheat() {
		return plugin.anticheat;
	}

	@SuppressWarnings("static-access")
	public static int getLevel(Player player, Plugin p) {
		if (player == null)
			return 0;
		plugin.Tools.checkAccount(player);
		int level = 0;
		int max = 1000;
		double constant = plugin.Constant;
		if (plugin.EnableLevelCap) {
			max = plugin.LevelCap;
		}
		double exp = plugin.LevelFunctions.getExp(player, p);
		for (int i = 1; i <= max; i++) {
			double levelAti = constant * (i * i);
			if (levelAti > exp) {
				level = i;
				break;
			} else if (i == max && plugin.EnableLevelCap) {
				level = max;
				break;
			}
		}
		return level;

	}

	@SuppressWarnings("static-access")
	public static boolean isMaster(Player player, Plugin p) {
		if (plugin.LevelFunctions.getLevel(player, p) >= plugin.LevelCap)
			return true;
		return false;
	}

	public static double getExp(Player player, Plugin p) {

		plugin.Tools.checkAccount(player);
		if (plugin.database.equalsIgnoreCase("FlatFile")) {
			/*
			 * for (Plugin p1 : plugin.LevelFiles.keySet()) { if (p1 != p)
			 * continue; return plugin.FlatFile.getDouble(player.getName(),
			 * plugin.LevelFiles.get(p1)); }
			 */

			if (plugin.LevelFiles.containsKey(p)
					&& plugin.ExpCache.containsPlugin(p)) {
				/*
				 * for (Plugin p1 : plugin.LevelNames.keySet()) { if (p1 != p)
				 * continue; return
				 * plugin.SqliteDB.getDouble(player.getName(),plugin
				 * .LevelNames.get(p1));
				 */

				double exp = 0;
				// HashMap<Player, Double> expPlayers = ;
				if (plugin.ExpCache.containsPlayer(p, player)) {// In cache, use
																// it !

					exp = plugin.ExpCache.getExp(p, player);
				} else {// Not in cache, add it !
					exp = plugin.FlatFile.getDouble(player.getName(),
							plugin.LevelFiles.get(p));
					synchronized (plugin.ExpCache) {
						plugin.ExpCache.putExp(p, player, exp);
					}
				}
				return exp;
			}
		} else if (plugin.database.equalsIgnoreCase("sqlite")) {
			if (plugin.LevelNames.containsKey(p)
					&& plugin.ExpCache.containsPlugin(p)) {
				/*
				 * for (Plugin p1 : plugin.LevelNames.keySet()) { if (p1 != p)
				 * continue; return
				 * plugin.SqliteDB.getDouble(player.getName(),plugin
				 * .LevelNames.get(p1));
				 */

				double exp = 0;
				// HashMap<Player, Double> expPlayers = plugin.ExpCache.get(p);
				if (plugin.ExpCache.containsPlayer(p, player)) {// In cache, use
																// it !

					exp = plugin.ExpCache.getExp(p, player);
				} else {// Not in cache, add it !
					exp = plugin.SqliteDB.getDouble(player.getName(),
							plugin.LevelNames.get(p));
					synchronized (plugin.ExpCache) {
						plugin.ExpCache.putExp(p, player, exp);
					}
				}
				return exp;
			}
		} else if (plugin.database.equalsIgnoreCase("mysql")) {
			/*
			 * for (Plugin p1 : plugin.LevelNames.keySet()) { if (p1 != p)
			 * continue; return
			 * plugin.MySqlDB.getDouble(player.getName(),plugin.
			 * LevelNames.get(p1)); }
			 */
			// ADD CACHE BY L5D

			if (plugin.LevelNames.containsKey(p)
					&& plugin.ExpCache.containsPlugin(p)) {
				double exp = 0;
				// HashMap<Player, Double> expPlayers = plugin.ExpCache.get(p);
				if (plugin.ExpCache.containsPlayer(p, player)) {// In cache, use
																// it !
					exp = plugin.ExpCache.getExp(p, player);
				} else {// Not in cache, add it !
					exp = plugin.MySqlDB.getDouble(player.getName(),
							plugin.LevelNames.get(p));
					synchronized (plugin.ExpCache) {
						plugin.ExpCache.putExp(p, player, exp);
					}
				}
				return exp;
			}
		}
		return 0;

	}

	@SuppressWarnings("static-access")
	public static double getExpLeft(Player player, Plugin p) {
		double exp = plugin.LevelFunctions.getExp(player, p);
		int level = plugin.LevelFunctions.getLevel(player, p);
		if (level >= plugin.LevelCap && plugin.EnableLevelCap)
			return 0;
		double nextExp = plugin.Constant * ((level) * (level));
		double leftExp = nextExp - exp;
		return leftExp;

	}

	@SuppressWarnings("static-access")
	public static void updateLevel(Player player, Plugin p, int i) {
		if (i >= plugin.LevelCap && plugin.EnableLevelCap)
			i = plugin.LevelCap;
		double exp = plugin.Constant * ((i - 1) * (i - 1));
		plugin.LevelFunctions.updateExp(player, p, exp);

	}

	public static void updateExp(Player player, Plugin p, double i) {
		if (plugin.database.equalsIgnoreCase("flatfile")) {
			/*
			 * for (Plugin p1 : plugin.LevelFiles.keySet()) { if (p1 != p)
			 * continue; plugin.FlatFile.write(player.getName(),
			 * plugin.LevelFiles.get(p1), i); return; }
			 */if (plugin.LevelFiles.containsKey(p)
					&& plugin.ExpCache.containsPlugin(p)) {
				if (!plugin.PeriodicSave) {
					plugin.FlatFile.write(player.getName(),
							plugin.LevelFiles.get(p), i);
				}
				synchronized(plugin.ExpCache)
				{
					plugin.ExpCache.putExp(p, player, i);
				}

			}
		} else if (plugin.database.equalsIgnoreCase("sqlite")) {
			/*
			 * for (Plugin p1 : plugin.LevelNames.keySet()) { if (p1 != p)
			 * continue; plugin.SqliteDB.update(player.getName(),
			 * plugin.LevelNames.get(p1), i); return; }
			 */
			if (plugin.LevelNames.containsKey(p)
					&& plugin.ExpCache.containsPlugin(p)) {
				if (!plugin.PeriodicSave) {

					plugin.SqliteDB.update(player.getName(),
							plugin.LevelNames.get(p), i);
				}
				synchronized(plugin.ExpCache)
				{
					plugin.ExpCache.putExp(p, player, i);
				}

			}
		} else if (plugin.database.equalsIgnoreCase("mysql")) {
			/*
			 * for (Plugin p1 : plugin.LevelNames.keySet()) { if (p1 != p)
			 * continue;
			 * plugin.MySqlDB.update(player.getName(),plugin.LevelNames.get(p1),
			 * i); return; }
			 */
			// ADD CACHE BY L5D
			if (plugin.LevelNames.containsKey(p)
					&& plugin.ExpCache.containsPlugin(p)) {
				if (!plugin.PeriodicSave) {
					plugin.MySqlDB.update(player.getName(),
							plugin.LevelNames.get(p), i);
				}
				synchronized(plugin.ExpCache)
				{
					plugin.ExpCache.putExp(p, player, i);
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	public static void addExp(Player player, Plugin p, double i) {
		// if(plugin.Permissions.hasLevelNoExp(player, p)) return;
		if (i == 0)
			return;
		if (!plugin.Permissions.hasLevelExp(player, p))
			return;

		int beforeLevel = plugin.LevelFunctions.getLevel(player, p);
		plugin.LevelFunctions.updateExp(player, p,
				(plugin.LevelFunctions.getExp(player, p) + i));
		if (isNotified(player))
			if (i > 0)
				plugin.LCChat.good(player, "[" + plugin.LevelIndexes.get(p)
						+ "] " + plugin.lang.YouGained + i + " exp");
			else if (i < 0)
				plugin.LCChat.bad(player, "[" + plugin.LevelIndexes.get(p)
						+ "] " + plugin.lang.YouLost + i + " exp");
		int newLevel = plugin.LevelFunctions.getLevel(player, p);
		if (beforeLevel < newLevel) {
			if (plugin.SpoutEnabled) {
				if (((SpoutPlayer) player).isSpoutCraftEnabled()) {
					SpoutPlayer sp = (SpoutPlayer) player;
					Material m = Material.SIGN_POST;
					String s = plugin.LevelNames.get(p);
					if (s.equalsIgnoreCase("mining")) {
						m = Material.DIAMOND_PICKAXE;
					} else if (s.equalsIgnoreCase("woodcutting")) {
						m = Material.DIAMOND_AXE;
					} else if (s.equalsIgnoreCase("combat")) {
						m = Material.DIAMOND_SWORD;
					} else if (s.equalsIgnoreCase("range")) {
						m = Material.BOW;
					} else if (s.equalsIgnoreCase("dexterity")) {
						m = Material.DIAMOND_BOOTS;
					} else if (s.equalsIgnoreCase("farming")) {
						m = Material.DIAMOND_HOE;
					} else if (s.equalsIgnoreCase("digging")
							|| s.equalsIgnoreCase("excavation")) {
						m = Material.DIAMOND_SPADE;
					} else if (s.equalsIgnoreCase("swimming")) {
						m = Material.PUMPKIN;
					} else if (s.equalsIgnoreCase("explosives")) {
						m = Material.TNT;
					} else if (s.equalsIgnoreCase("health")) {
						m = Material.APPLE;
					} else if (s.equalsIgnoreCase("defence")) {
						m = Material.DIAMOND_CHESTPLATE;
					} else if (s.equalsIgnoreCase("forgery")) {
						m = Material.BURNING_FURNACE;
					} else if (s.equalsIgnoreCase("scavenger")) {
						m = Material.SADDLE;
					} else if (s.equalsIgnoreCase("prayer")) {
						m = Material.WATCH;
					} else if (s.equalsIgnoreCase("construction")) {
						m = Material.BRICK;
					}
					sp.sendNotification("LevelUp", s + " is now " + newLevel, m);
					if (!plugin.EnableSound)
						return;
					SoundManager soundM = SpoutManager.getSoundManager();
					soundM.playCustomSoundEffect(plugin, (SpoutPlayer) player,
							plugin.LevelUpSound, false);
				} else {
					plugin.LCChat.good(player, plugin.lang.LevelUp + newLevel
							+ " in " + plugin.LevelNames.get(p));
					plugin.LCChat.good(player, plugin.lang.SeeLevelUnlocks
							+ plugin.LevelIndexes.get(p)
							+ " - To see what you have unlocked.");
				}
			} else {

				plugin.LCChat.good(player, plugin.lang.LevelUp + newLevel
						+ " in " + plugin.LevelNames.get(p));
				plugin.LCChat.good(player, plugin.lang.SeeLevelUnlocks
						+ plugin.LevelIndexes.get(p)
						+ " - To see what you have unlocked.");

			}

			if (plugin.NotifyAll) {
				plugin.LCChat.broadcast(player.getName()
						+ plugin.lang.IsNowLevel + newLevel + " in "
						+ plugin.LevelNames.get(p) + ".");
			}
		}
	}

	public static boolean isNotified(Player p) {
		return plugin.Tools.enabled(p);
	}

	@SuppressWarnings("static-access")
	public static int getPos(Player sender, String string) {
		for (Plugin p : plugin.LevelReferenceKeys.keySet()) {
			String[] reference = plugin.LevelReferenceKeys.get(p);
			if (plugin.Tools.containsValue(reference, string)
					&& plugin.Permissions.hasLevel(sender, p)) {
				if (plugin.database.equalsIgnoreCase("mysql"))
					return plugin.MySqlDB.getPos(sender.getName(),
							plugin.LevelNames.get(p));
				if (plugin.database.equalsIgnoreCase("sqlite"))
					return plugin.SqliteDB.getPos(sender.getName(),
							plugin.LevelNames.get(p));
				if (plugin.database.equalsIgnoreCase("flatfile"))
					return plugin.FlatFile.getPos(sender.getName(),
							plugin.LevelFiles.get(p));
			}
		}
		return 0;
	}

	public static String getPlayerAtPos(String string, int i) {
		for (Plugin p : plugin.LevelReferenceKeys.keySet()) {
			String[] reference = plugin.LevelReferenceKeys.get(p);
			if (plugin.Tools.containsValue(reference, string)) {
				if (plugin.database.equalsIgnoreCase("mysql"))
					return plugin.MySqlDB.getPlayerAtPos(
							plugin.LevelNames.get(p), i);
				if (plugin.database.equalsIgnoreCase("sqlite"))
					return plugin.SqliteDB.getPlayerAtPos(
							plugin.LevelNames.get(p), i);
				if (plugin.database.equalsIgnoreCase("flatfile"))
					return plugin.FlatFile.getPlayerAtPos(
							plugin.LevelNames.get(p), i,
							plugin.LevelFiles.get(p));
			}
		}
		return "None";
	}

}
