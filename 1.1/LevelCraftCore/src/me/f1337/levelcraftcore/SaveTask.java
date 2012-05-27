package me.f1337.levelcraftcore;


public class SaveTask implements Runnable {

	private LevelCraftCore plugin;

	public SaveTask(LevelCraftCore plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		plugin.saveExp();
	}

}
