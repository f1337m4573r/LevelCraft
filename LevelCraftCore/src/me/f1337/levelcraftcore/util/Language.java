package me.f1337.levelcraftcore.util;

import java.io.IOException;
import java.util.logging.Level;

import me.f1337.levelcraftcore.LevelCraftCore;

public class Language {
	public String ClassCommands;
	public LevelCraftCore plugin;
	public String LanguageFile = "plugins/LevelCraftCore/Language.cfg";

	public String LevelSetSuccess;
	public String LevelSetFalse;
	public String ExperienceSetSuccess;
	public String ExperienceSetFalse;
	public String ExperienceResetSuccess;
	public String SetLevelForPlayers;
	public String SetExperienceForPlayers;
	public String GetExperienceOfPlayer;
	public String GetLevelOfPlayer;
	public String ResetExperience;
	public String ShowsThis;
	public String NoMatch;
	public String TooMany;
	public String ExperienceIs;
	public String LevelIs;
	public String SyntaxA;
	public String SyntaxP;
	public String YourActiveLevels;
	public String NoLevelFound;
	public String Experience;
	public String LevelS;
	public String ExperienceToNextLevel;
	public String NoHelpFileYet;
	public String TopPlayersIn;
	public String YouAreNumber;
	public String YouDoNotHavePermission;
	public String YourTotalLevelIs;
	public String None;
	public String TotalLevel;
	public String TotalExp;
	public String HighestLevel;
	public String YouGained;
	public String YouLost;
	public String LevelUp;
	public String IsNowLevel;
	public String SeeLevelUnlocks;
	public String ExperienceResetFalse;
	public String NotifyOn;
	public String NotifyOff;

	public String ShowsActive;
	public String ToggleNote;
	public String ShowsLevelStats;
	public String ShowsToolBlock;
	public String ShowsExp;
	public String ShowsTotal;
	public String ShowsAll;
	public String ShowsRank;
	public String ShowsTop;
	public String ShowsHelp;
	public String AdminCommands;
	public String Shout;

	public Language(LevelCraftCore instance) {
		plugin = instance;
	}

	public void LoadLang() {
		Properties properties = new Properties(this.LanguageFile);
		try {
			properties.load();
		} catch (IOException e) {
			plugin.logger.log(Level.SEVERE, "[LC] " + e);
		}

		this.LevelSetSuccess = properties.getString("LevelSetSuccess",
				"Level set successfully.");
		this.LevelSetFalse = properties.getString("LevelSetFalse",
				"Could not set level please check log.");
		this.ExperienceSetSuccess = properties.getString(
				"ExperienceSetSuccess", "Experience set successfully.");
		this.ExperienceSetFalse = properties.getString("ExperienceSetFalse.",
				"Could not set experience please check log.");
		this.ExperienceResetSuccess = properties.getString("ResetSuccess",
				"Experience reset successfully.");
		this.ExperienceResetFalse = properties.getString("ResetFalse",
				"Could not reset experience.");
		this.NoMatch = properties.getString("NoPlayer", "No matching player.");
		this.TooMany = properties.getString("ManyPlayer",
				"Too many players. Be more specific");
		this.ExperienceIs = properties.getString("ExperienceIs",
				" experience is ");
		this.LevelIs = properties.getString("LevelIs", " level is ");
		this.SyntaxA = properties.getString("AdminSyntax",
				"Syntax Error. See /level admin. For help.");
		this.ShowsThis = properties.getString("ShowsThis", "Shows this.");
		this.ResetExperience = properties.getString("ResetExperience",
				"Resets experience for player.");
		this.GetLevelOfPlayer = properties.getString("LevelOfPlayer",
				"Gets level of player.");
		this.GetExperienceOfPlayer = properties.getString("ExperienceOfPlayer",
				"Gets experience for player.");
		this.SetLevelForPlayers = properties.getString("SetLevelOfPlayer",
				"Sets level for player.");
		this.SetExperienceForPlayers = properties.getString(
				"SetExperienceOfPlayer", "Sets experience for player.");
		this.IsNowLevel = properties.getString("IsNowLevel", " is now level ");
		this.YouGained = properties.getString("YouGained", "You gained: ");
		this.YouLost = properties.getString("YouLost", "You lost: ");
		this.SeeLevelUnlocks = properties.getString("SeeLevelUnlocks",
				"See /level unlocks ");
		this.LevelUp = properties.getString("LevelUp",
				"LEVEL UP. You are now level ");
		this.NotifyOn = properties.getString("NotifyOn",
				"Experience notify enabled.");
		this.NotifyOff = properties.getString("NotifyOff",
				"Experience notify disabled.");
		this.NoLevelFound = properties.getString("NoLevel", "No level found.");
		this.LevelS = properties.getString("Level", " Level: ");
		this.Experience = properties.getString("Experience", " Experience: ");
		this.ExperienceToNextLevel = properties.getString(
				"ExperienceToNextLevel", "Experience to next level: ");
		this.YourActiveLevels = properties.getString("ActiveLevels",
				"Your Active Levels are: ");
		this.NoHelpFileYet = properties.getString("NoHelp",
				"No Help file for level yet.");
		this.SyntaxP = properties.getString("PlayerSyntax",
				"Wrong Syntax. Please use '/level' for more infomation.");
		this.YouDoNotHavePermission = properties.getString("NoPermissions",
				"You do not have permission to do that.");
		this.TopPlayersIn = properties.getString("TopPlayer", "Top Players In");
		this.YouAreNumber = properties
				.getString("YouNumber", "You are number ");
		this.TotalExp = properties.getString("TotalExp", "Total Experience: ");
		this.TotalLevel = properties.getString("TotalLevel", "Total Level: ");
		this.HighestLevel = properties.getString("HighestLevel",
				"Highest Level: ");
		this.None = properties.getString("None", "No stat/command found.");
		this.ClassCommands = properties.getString("ClassCommands",
				"Class Commands");
		this.ShowsActive = properties.getString("ShowActive",
				"Shows active stats.");
		this.ToggleNote = properties.getString("ToogleNote",
				"Toggles notifications.");
		this.ShowsLevelStats = properties.getString("ShowLevelStats",
				"Shows Level Statistics.");
		this.ShowsToolBlock = properties.getString("ShowsToolBlock",
				"Shows Tool and Block unlocks.");
		this.ShowsExp = properties.getString("ShowsExp",
				"Shows Experience For Actions.");
		this.Shout = properties.getString("ShowShout",
				"Shouts Level Statistics.");
		this.ShowsTotal = properties.getString("ShowsTotal",
				"Shows total Level.");
		this.ShowsAll = properties.getString("ShowsAll", "Shows all Levels.");
		this.ShowsRank = properties.getString("ShowsRank", "Shows your rank.");
		this.ShowsTop = properties.getString("ShowsTop",
				"Shows top players in that level.");
		this.ShowsHelp = properties.getString("ShowsHelp",
				"Gets help on a level.");
		this.AdminCommands = properties.getString("AdminCommands",
				"Admin Commands.");
	}
}
