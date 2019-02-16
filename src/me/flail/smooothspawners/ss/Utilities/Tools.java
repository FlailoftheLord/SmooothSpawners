package me.flail.smooothspawners.ss.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.smooothspawners.SmooothSpawners;

public class Tools {

	private SmooothSpawners plugin;

	public String chat(String msg) {
		plugin = JavaPlugin.getPlugin(SmooothSpawners.class);
		return ChatColor.translateAlternateColorCodes('&', msg.replace("{prefix}", "&7(&2&lSpawners&7)"));
	}

	public String mobName(EntityType type, boolean replaceUnderscores) {

		String mobType = type.toString().toLowerCase();

		String mobName = "";

		for (String s : mobType.split("_")) {

			String firstLetter = Character.toString(s.charAt(0)).toUpperCase();

			String finalName = s.replaceFirst("[a-zA-Z]", firstLetter);

			if (mobName.isEmpty() || mobName.equals(" ")) {
				mobName = mobName.concat(finalName);
			} else {

				if (replaceUnderscores) {
					mobName = mobName.concat(" " + finalName);
				} else {
					mobName = mobName.concat("_" + finalName);
				}

			}

		}

		return mobName;

	}

}
