package me.flail.smooothspawners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.smooothspawners.API.CreatureSpawner;
import me.flail.smooothspawners.API.Spawner;
import me.flail.smooothspawners.ss.Utilities.Tools;

public class SpawnerCommand {

	private SmooothSpawners plugin = JavaPlugin.getPlugin(SmooothSpawners.class);

	public boolean process(CommandSender sender, String command, String[] arguments) {

		List<String> aliases = plugin.pluginDesc().getStringList("commands.smooothspawners.aliases");

		for (String alias : aliases) {
			if (command.equalsIgnoreCase(alias)) {
				return this.spawnerCommand(sender, alias, arguments);
			}
		}

		return true;
	}

	private boolean argCheck(CommandSender operator, String cmd, String[] arguments) {

		Tools tools = new Tools();
		String usage = tools.chat("{prefix} &cUsage&8: &7/{cmd} <give:get> [player] [type] [amount]").replace("{cmd}",
				cmd);
		String giveUsage = tools.chat("{prefix} &cUsage&8: &7/{cmd} give [player] [type] [amount]").replace("{cmd}",
				cmd);
		String getUsage = tools.chat("{prefix} &cUsage&8: &7/{cmd} get [type] [amount]").replace("{cmd}", cmd);
		String invalidUsage = tools.chat("{prefix} &cYou can't use that command in console!");

		switch (arguments[0].toLowerCase()) {
		case "give":
			operator.sendMessage(giveUsage);

		case "get":
			if (operator instanceof Player) {
				operator.sendMessage(getUsage);
			} else {
				operator.sendMessage(invalidUsage);
			}

		default:
			operator.sendMessage(usage);

		}

		return true;

	}

	private boolean spawnerCommand(CommandSender operator, String cmd, String[] args) {
		Tools tools = new Tools();

		switch (args.length) {
		case 0:
			return argCheck(operator, cmd, args);
		case 1:
			return argCheck(operator, cmd, args);
		case 2:
			return argCheck(operator, cmd, args);
		case 3:
			if (args[0].equalsIgnoreCase("get")) {
				if (operator instanceof Player) {
					Player player = (Player) operator;

					EntityType type = EntityType.valueOf(args[1].toUpperCase());

					if (type != null) {
						ItemStack spawnerItem = new ItemStack(Material.SPAWNER);
						Spawner spawner = new CreatureSpawner(spawnerItem, type).get();
						player.getInventory().addItem(spawner.setType(type).item());

						player.sendMessage(tools.chat("{prefix} &aYou got a " + args[1] + " spawner!"));

					}

				}

				return true;
			} else {
				return argCheck(operator, cmd, args);
			}
		}

		return true;
	}

}
