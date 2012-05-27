package me.f1337.lccombat;

import java.util.HashMap;

import me.f1337.levelcraftcore.LCChat;
import me.f1337.levelcraftcore.LevelFunctions;
import me.f1337.levelcraftcore.Whitelist;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import net.minecraft.server.EntityLiving;

import org.bukkit.craftbukkit.entity.CraftEntity;

public class LCEntityListener extends EntityListener {
	public LCCombat plugin;
	public HashMap<Entity, Block> doneBefore = new HashMap<Entity, Block>();

	public LCEntityListener(LCCombat instance) {
		plugin = instance;
	}

	public void onEntityDamage(EntityDamageEvent event) {
		if (event.isCancelled()) {
			return;
		}
		if(event.getEntity() instanceof CraftEntity)
	     {
	     CraftEntity CEntity = (CraftEntity)event.getEntity();
	     
	if(CEntity.getHandle() instanceof EntityLiving)
	{
		EntityLiving LEntity = (EntityLiving)CEntity.getHandle();
		if(LEntity.noDamageTicks < LEntity.maxNoDamageTicks/2.0F)
		{
		if (event instanceof EntityDamageByEntityEvent) {
			if (((EntityDamageByEntityEvent) event).getDamager() instanceof Player
					&& Whitelist.worldCheck(event.getEntity().getWorld())
					&& Whitelist.hasLevel(
							(Player) ((EntityDamageByEntityEvent) event)
									.getDamager(), plugin.thisPlug)) {

				if (!(event instanceof EntityDamageByProjectileEvent)) {

					plugin.entListener
							.onEDamageByE((EntityDamageByEntityEvent) event);
					return;
				}
			}
		}
		}
	}
	
	}

		
	}

	public void onEDamageByE(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player))
			return;
		if (event.getEntity() instanceof Player) {
			Player Damager = (Player) ((EntityDamageByEntityEvent) event)
					.getDamager();
			Player Damagee = (Player) event.getEntity();
			if (Damager == Damagee)
				return;
		}
		if (event.getEntity() instanceof Player
				&& plugin.LCConfiguration.pvpRangeEnable) {
			Player Damager = (Player) ((EntityDamageByEntityEvent) event)
					.getDamager();
			Player Damagee = (Player) event.getEntity();
			int DamageeLevel = LevelFunctions
					.getLevel(Damagee, plugin.thisPlug);
			int DamagerLevel = LevelFunctions
					.getLevel(Damager, plugin.thisPlug);
			int difference = Math.abs(DamageeLevel - DamagerLevel);
			if (difference > plugin.LCConfiguration.pvpRange) {
				LCChat.warn(Damager, "You can only attack players within a "
						+ plugin.LCConfiguration.pvpRange
						+ " level diffence in Combat.");
				event.setCancelled(true);
				return;
			}
		}
		Player player = (Player) event.getDamager();
		int iih = player.getItemInHand().getTypeId();
		int level = LevelFunctions.getLevel(player, plugin.thisPlug);
		if (plugin.LCConfiguration.ToolLevels.containsKey(iih)) {
			if (level < plugin.LCConfiguration.ToolLevels.get(iih)) {
				LCChat.warn(player, "Cannot use this tool. Required Level:"
						+ plugin.LCConfiguration.ToolLevels.get(iih));
				event.setCancelled(true);
				return;
			
		}else{

			LevelFunctions.addExp(player, plugin.thisPlug,
					plugin.LCConfiguration.ExpPerDamage * event.getDamage());
		}
		}

	}

}
