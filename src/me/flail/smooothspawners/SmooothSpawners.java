package me.flail.smooothspawners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Set;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.smooothspawners.ss.Utilities.Boot;

public class SmooothSpawners extends JavaPlugin {

	public Server server = this.getServer();
	public PluginManager pm = server.getPluginManager();

	public String version = this.getDescription().getVersion();
	public String author = this.getDescription().getAuthors().get(0);

	@Override
	public void onEnable() {

		new Boot().bootDump(this);

	}

	public static void loadApi() {

	}

	public boolean registerCommands() {
		Set<String> commands = this.getDescription().getCommands().keySet();

		for (String command : commands) {
			this.getCommand(command).setExecutor(this);
		}

		return true;
	}

	public boolean registerEvents() {

		return true;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().toLowerCase().equals("smooothspawners")) {

			return new SpawnerCommand().process(sender, label, args);
		}
		return true;
	}

	/**
	 * loads up a spittin' image of the plugin.yml file.
	 *
	 * @return a FileConfiguration containing all teh fancy info in the plugin.yml
	 *         file.
	 */
	public FileConfiguration pluginDesc() {
		File pluginDescription = new File(this.getDataFolder() + "/plugin.yml");

		Writer writer = null;

		try {
			this.getDescription().save(writer = new BufferedWriter(new FileWriter(pluginDescription)));
		} catch (Throwable t) {
		}

		try {
			writer.close();
		} catch (Exception e) {
		}

		pluginDescription.setReadable(true);

		pluginDescription = new File(this.getDataFolder() + "/plugin.yml");

		FileConfiguration pluginData = YamlConfiguration.loadConfiguration(pluginDescription);

		try {
			pluginData.load(pluginDescription);

			return pluginData;
		} catch (Throwable t) {
		}

		return pluginData;

	}

	/**
	 * Wads up lose ends and glues the bag close on the fake plugin.yml file.
	 */
	public void saveDesc() {
		File pluginDescription = new File(this.getDataFolder() + "/plugin.yml");
		try {
			this.pluginDesc().save(pluginDescription);
		} catch (Throwable t) {
		}
	}

}
