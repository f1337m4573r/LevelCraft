package me.f1337.levelcraftcore;


import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.player.SpoutPlayer;

public class LCGui {
	public  LevelCraftCore plugin;

	public LCGui(LevelCraftCore instance) {
		plugin = instance;
	}
	  
   
	   public  void initializeGUIBasic(SpoutPlayer sPlayer) {
		   GenericPopup popupMenu = new GenericPopup();
		   GenericButton creationButton = new GenericButton();
		   GenericTextField GTF = new GenericTextField();
		   GTF.setHeight(40);
		   GTF.setWidth(40);
		   GTF.setX(20);
		   GTF.setY(20);
		   GTF.setVisible(true).setDirty(true);
		   
		  
		   
	        creationButton.setText("Next Page ->");
	        creationButton.setX(sPlayer.getMainScreen().getWidth()-80);
	        creationButton.setY(sPlayer.getMainScreen().getHeight()-80);
	        creationButton.setHeight(20);
	        creationButton.setWidth(60);  
	        creationButton.setVisible(true).setDirty(true);
	        popupMenu.attachWidget(plugin, creationButton);
	       // popupMenu.attachWidget(plugin, GTF);
	        sPlayer.sendMessage(GTF.getContainer()+"");
	       sPlayer.getMainScreen().attachPopupScreen(popupMenu);
	    }
	 
}