package me.flail.smooothspawners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.smooothspawners.API.CreatureSpawner;
import me.flail.smooothspawners.API.Spawner;
import me.flail.smooothspawners.ss.Utilities.Tools;

public class SpawnerCommand {

	private SmooothSpawners plugin = JavaPlugin.getPlugin(SmooothSpawners.class);

	public boolean process(CommandSender sender, Command command, String label, String[] arguments) {
		Tools tools = new Tools();
		command.setUsage(tools.chat("{prefix} &cUsage&8: &7/" + label + " <give:get> [player] [type] [amount]"));

		List<String> aliases = plugin.getConfig().getStringList("CommandAliases");

		if (!aliases.isEmpty()) {
			command.setAliases(aliases);
		}
		if ((sender != null) && label.equalsIgnoreCase(command.getName())) {
			return this.spawnerCommand(sender, label, arguments);
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

		if (arguments.length > 0) {
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

		} else {
			operator.sendMessage(usage);
		}
		return true;

	}

	private boolean spawnerCommand(CommandSender operator, String cmd, String[] args) {

		switch (args.length) {
		case 0:
			return argCheck(operator, cmd, args);
		case 1:
			return argCheck(operator, cmd, args);
		case 2:
			if (args[0].equalsIgnoreCase("get")) {
				return this.getSpawner(operator, EntityType.valueOf(args[1].toUpperCase()), 1, args);
			} else {
				return argCheck(operator, cmd, args);
			}
		case 3:
			if (args[0].equalsIgnoreCase("get")) {
				return this.getSpawner(operator, EntityType.valueOf(args[1].toUpperCase()),
						Integer.parseInt(args[2].trim()), args);
			} else {
				return argCheck(operator, cmd, args);
			}
		}

		return true;
	}

	private boolean getSpawner(CommandSender operator, EntityType type, int amount, String[] args) {
		Tools tools = new Tools();

		if (operator instanceof Player) {
			Player player = (Player) operator;

			if (type != null) {
				ItemStack spawnerItem = new ItemStack(Material.SPAWNER);
				spawnerItem.setAmount(amount);
				ItemMeta meta = spawnerItem.getItemMeta();
				meta.setDisplayName(tools.chat("&eFancy " + type.toString().toLowerCase() + " spawner."));
				spawnerItem.setItemMeta(meta);
				Spawner spawner = new CreatureSpawner(spawnerItem, type.toString()).get();

				player.getInventory().addItem(spawner.setType(type).item());

				player.sendMessage(tools.chat("{prefix} &aYou got a " + args[1] + " spawner!"));

				return true;
			}

		} else {
			return this.argCheck(operator, "smooothspawners", args);
		}

		return false;
	}

}
