package me.flail.smooothspawners.ss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.smooothspawners.SmooothSpawners;
import me.flail.smooothspawners.API.Spawner;
import me.flail.smooothspawners.ss.Utilities.Tools;

public abstract class AbstractSpawner extends HashMap<ItemStack, String> implements Spawner {

	private SmooothSpawners plugin = JavaPlugin.getPlugin(SmooothSpawners.class);
	Tools tools = new Tools();

	private static final long serialVersionUID = 1L;
	public ItemStack item;
	public String type;

	public AbstractSpawner(ItemStack item, String type) {
		this.clear();
		this.type = type;
		this.item = item;
		this.put(item, type);
	}

	private Spawner spawnerType(ItemStack item) {
		ItemMeta meta = null;

		if (item.hasItemMeta()) {
			meta = item.getItemMeta();
		}

		String mobType = meta.getCustomTagContainer()
				.getCustomTag(new NamespacedKey(plugin, "Spawner"), ItemTagType.STRING).toString().toUpperCase();

		type = mobType;

		this.clear();
		this.put(item, type);

		return this;

	}

	@Override
	public String type() {
		String meta = this.values().toArray(new String[] {})[0];

		return meta.split("~")[0];
	}

	@Override
	public ItemStack item() {
		return this.keySet().toArray(new ItemStack[] {})[0];
	}

	@Override
	public Spawner convert() {
		this.clear();
		this.put(item, type);

		return this;
	}

	@Override
	public Spawner getType() {
		if (this.spawnerType(this.item()) != null) {
			return this.spawnerType(this.item());
		}

		return null;
	}

	@Override
	public Spawner setType(EntityType type) {

		ItemMeta meta = item.getItemMeta();
		meta.getCustomTagContainer().setCustomTag(new NamespacedKey(plugin, "Spawner"), ItemTagType.STRING,
				type.toString());

		List<String> lore = new ArrayList<>();

		lore.add(" ");
		lore.add(tools.chat("&7" + type + " Spawner"));

		meta.setLore(lore);

		item.setItemMeta(meta);
		this.setDisplayName(tools.mobName(type, false) + " Spawner");

		this.clear();
		this.put(item, this.type);

		return this.convert();
	}

	@Override
	public void setDisplayName(String name) {
		ItemStack newItem = this.item();
		if (newItem.hasItemMeta()) {
			ItemMeta im = this.item().getItemMeta();
			im.setDisplayName(ChatColor.RESET + tools.chat(name));
			newItem.setItemMeta(im);

			this.put(newItem, type);
		}

	}

}
