package me.f1337.levelcraftcore.Listeners;

import java.io.File;

import me.f1337.levelcraftcore.LevelCraftCore;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.AppearanceManager;
import org.getspout.spoutapi.player.SpoutPlayer;


public class LCPlayerListener extends PlayerListener {
	public LevelCraftCore plugin;

	public LCPlayerListener(LevelCraftCore instance) {
		plugin = instance;
	}

	@SuppressWarnings("static-access")
	public void onPlayerJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();
		plugin.Tools.checkAccount(player);
if(plugin.SpoutEnabled && plugin.EnableSkillMastery && plugin.EnableCapes){
	SpoutPlayer sp = (SpoutPlayer) event.getPlayer();	
	File CapeFile = new File(plugin.getDataFolder() + "/Data/Cape.data");
	if(plugin.FlatFile.contains(sp.getName(), CapeFile)){
		AppearanceManager appearM = SpoutManager.getAppearanceManager();
	    appearM.setGlobalCloak(sp, "http://cloud.github.com/downloads/samkio/Levelcraft/"+plugin.FlatFile.getString(sp.getName(), CapeFile)+".png");
	    return;
	}
	for (Plugin plug : plugin.LevelNames.keySet()) {
		if (!plugin.Permissions.hasLevel(player, plug))
			continue;
		if (!(plugin.LevelFunctions.getLevel(player, plug) >= plugin.LevelCap))
			continue;
		AppearanceManager appearM = SpoutManager.getAppearanceManager();
	    appearM.setGlobalCloak(sp, "http://cloud.github.com/downloads/samkio/Levelcraft/"+plugin.LevelNames.get(plug)+".png");
	    return;
	}
	
	
	
}
	}

	@SuppressWarnings("static-access")
	public void onPlayerChat(PlayerChatEvent event) {
		
	//	plugin.LCGui.initializeGUIBasic(sp);
		
	
		if (!plugin.EnableSkillMastery)
			return;
		Player p = event.getPlayer();
		String str = event.getFormat();
		for (Plugin plug : plugin.LevelNames.keySet()) {
			if (!plugin.Permissions.hasLevel(p, plug))
				continue;

			if (!(plugin.LevelFunctions.getLevel(p, plug) >= plugin.LevelCap))
				continue;

			str = plugin.c1 + "[" + plugin.LevelIndexes.get(plug) + "]WHITE"
					+ str;
		}
		event.setFormat(plugin.Tools.format(str));
	}

}
