package me.f1337.levelcraftcore;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ExpCache {

	private HashMap<Plugin, HashMap<Player, ExpCacheData>> cache = new HashMap<Plugin, HashMap<Player, ExpCacheData>>();

	public ExpCache() {

	}

	public void clear() {
		for (Plugin key : cache.keySet()) {
			cache.get(key).clear();
		}
		cache.clear();

	}
	
	public Set<Plugin> getPlugins()
	{
		return cache.keySet();
	}
	
	

	public void init(Plugin plugin) {
		cache.put(plugin, new HashMap<Player, ExpCacheData>());
	}

	public boolean containsPlugin(Plugin p) {
		return cache.containsKey(p);
	}

	public boolean containsPlayer(Plugin p, Player player) {
		return cache.containsKey(p) && cache.get(p).containsKey(player);
	}

	public Double getExp(Plugin p, Player player) {
		return cache.get(p).get(player).getValue();
	}
	public ExpCacheData getExpData(Plugin p, Player player)
	{
		return cache.get(p).get(player);
	}

	public void putExp(Plugin p, Player player, double exp) {
		ExpCacheData data = cache.get(p).get(player);
		if (data == null )
		{
			data = new ExpCacheData(exp);
			cache.get(p).put(player, data);
		}
		else
		{
			data.setValue(exp);
			data.setChanged(true);
		}
		
	}

	public Set<Player> getPlayers(Plugin p) {
		return cache.get(p).keySet();
	}

	public void markSaved(Plugin p, Player player) {
		getExpData(p, player).setChanged(false);
	}


	

}
