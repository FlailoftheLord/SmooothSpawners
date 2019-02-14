package me.flail.smooothspawners.ss.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.smooothspawners.SmooothSpawners;

public class Tools {

	private SmooothSpawners plugin;

	public String chat(String msg) {
		plugin = JavaPlugin.getPlugin(SmooothSpawners.class);
		return ChatColor.translateAlternateColorCodes('&', msg.replace("{prefix}", "&7(&2&lSpawners&7)"));
	}

}
