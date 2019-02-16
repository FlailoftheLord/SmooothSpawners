package me.flail.smooothspawners.ss.Utilities;

import org.bukkit.command.ConsoleCommandSender;

import me.flail.smooothspawners.SmooothSpawners;

public class Boot {

	public boolean bootDump(SmooothSpawners plugin) {
		Tools tools = new Tools();
		ConsoleCommandSender console = plugin.server.getConsoleSender();

		try {
			console.sendMessage(tools.chat("|********************"));
			console.sendMessage(tools.chat(" "));
			console.sendMessage(tools.chat(" &eLoading SmooothSpawners..."));

			console.sendMessage(tools.chat(" Loaded:"));
			plugin.saveDefaultConfig();
			plugin.pluginDesc();
			plugin.saveDesc();
			console.sendMessage(tools.chat("    - Files"));
			plugin.registerCommands();
			console.sendMessage(tools.chat("    - Commands"));
			plugin.registerEvents();
			console.sendMessage(tools.chat("    - Events"));
			SmooothSpawners.loadSpawners();
			SmooothSpawners.loadApi();
			console.sendMessage(tools.chat("    - Listeners"));

			console.sendMessage(tools.chat(" "));
			console.sendMessage(tools.chat(" &3SmooothSpawners"));
			console.sendMessage(tools.chat("   &7version " + plugin.version));
			console.sendMessage(tools.chat("  &2by " + plugin.author + "."));
			console.sendMessage(tools.chat(" "));
			console.sendMessage(tools.chat("|********************"));

			return true;
		} catch (Throwable t) {
			return false;
		}

	}

}
