package me.f1337.lcforager;

import java.util.Random;

import me.f1337.levelcraftcore.LCChat;
import me.f1337.levelcraftcore.LevelFunctions;
import me.f1337.levelcraftcore.Whitelist;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

public class LCBlockListener extends BlockListener {
	public LCForager plugin;

	public LCBlockListener(LCForager instance) {
		this.plugin = instance;
	}

	public void onBlockBreak(BlockBreakEvent event) {
		if (event.isCancelled()) {
			return;
		}

		if (!Whitelist.worldCheck(event.getBlock().getWorld()))
			return;
		Player player = event.getPlayer();

		if (!Whitelist.hasLevel(player, this.plugin.thisPlug))
			return;
		//int iih = player.getItemInHand().getTypeId();
		Material m = event.getBlock().getType();
		int level = LevelFunctions.getLevel(player, this.plugin.thisPlug);
		// Leave Drop
		if (m == Material.LEAVES) {
			Random randomGenerator = new Random();
			int randomApple = randomGenerator.nextInt(100);

			if ((randomApple == 0)
					&& (level >= this.plugin.LCConfiguration.GoldenAppleLevel)) {
				Location locy = new Location(event.getBlock().getWorld(), event
						.getBlock().getX(), event.getBlock().getY(), event
						.getBlock().getZ(), 0.0F, 0.0F);
				event.getBlock().getWorld()
						.dropItem(locy, new ItemStack(322, 1));
				LevelFunctions.addExp(player, this.plugin.thisPlug,
						this.plugin.LCConfiguration.ExpPerGoldenApple);
			} else if ((randomApple > 0) && (randomApple < 10)
					&& (level >= this.plugin.LCConfiguration.AppleLevel)) {
				Location locy = new Location(event.getBlock().getWorld(), event
						.getBlock().getX(), event.getBlock().getY(), event
						.getBlock().getZ(), 0.0F, 0.0F);
				LevelFunctions.addExp(player, this.plugin.thisPlug,
						this.plugin.LCConfiguration.ExpPerApple);
				event.getBlock().getWorld()
						.dropItem(locy, new ItemStack(260, 1));

			}
			return;
		}
if(m != Material.GRASS && m!=Material.DIRT && m!=Material.SAND && m!=Material.GRAVEL) return;
		Random randomGenerator = new Random();

		int i = 1000;
		if (level >= 5 && level < 10) {
			i = 900;
		} else if (level >= 10 && level < 20) {
			i = 600;
		} else if (level >= 20 && level < 40) {
			i = 500;
		} else if (level >= 40 && level < 70) {
			i = 300;
		} else if (level >= 70 && level < 90) {
			i = 150;
		} else if (level >= 90 && level < 100) {
			i = 100;
		}
		int randomSaddle = randomGenerator.nextInt(i);
		int randomBowl = randomGenerator.nextInt(i);
		int randomIngot = randomGenerator.nextInt(i);
		int randomBoots = randomGenerator.nextInt(i);
		if ((randomSaddle > 0) && (randomSaddle < 10)
				&& (level >= this.plugin.LCConfiguration.ForageStickLevel)) {

			Location locy = new Location(event.getBlock().getWorld(), event
					.getBlock().getX(), event.getBlock().getY(), event
					.getBlock().getZ(), 0.0F, 0.0F);
			event.getBlock().getWorld().dropItem(locy, new ItemStack(329, 1));

			LCChat.info(player, "You found a stick!");
			LevelFunctions.addExp(player, this.plugin.thisPlug,
					this.plugin.LCConfiguration.ExpPerForageStick);
			return;
		} else if (randomIngot == 1
				&& (level >= this.plugin.LCConfiguration.ForageIngotLevel)) {

			Location locy = new Location(event.getBlock().getWorld(), event
					.getBlock().getX(), event.getBlock().getY(), event
					.getBlock().getZ(), 0.0F, 0.0F);

			LevelFunctions.addExp(player, this.plugin.thisPlug,
					this.plugin.LCConfiguration.ExpPerForageStick);
			event.getBlock().getWorld().dropItem(locy, new ItemStack(266, 1));

			LCChat.info(player,
					"Hey, do you see that shiny ingot in the sand?.");
			return;

		} else if ((randomBowl > 0) && (randomBowl < 50)
				&& (level >= this.plugin.LCConfiguration.ForageBowlLevel)) {
			Location locy = new Location(event.getBlock().getWorld(), event
					.getBlock().getX(), event.getBlock().getY(), event
					.getBlock().getZ(), 0.0F, 0.0F);
			LevelFunctions.addExp(player, this.plugin.thisPlug,
					this.plugin.LCConfiguration.ExpPerForageBowl);
			event.getBlock().getWorld().dropItem(locy, new ItemStack(281, 1));
			LCChat.info(player, "Get those mushrooms ready, you found a bowl!");
			return;
		} else if ((randomBoots > 0) && (randomBoots < 55)
				&& (level >= this.plugin.LCConfiguration.ForageBowlLevel)) {
			Location locy = new Location(event.getBlock().getWorld(), event
					.getBlock().getX(), event.getBlock().getY(), event
					.getBlock().getZ(), 0.0F, 0.0F);
			LevelFunctions.addExp(player, this.plugin.thisPlug,
					this.plugin.LCConfiguration.ExpPerForageBowl);
			event.getBlock().getWorld().dropItem(locy, new ItemStack(301, 1));
			LCChat.info(player,
					"Someone missing footwear? You found a pair of boots!");
		}
		return;
	}

}
