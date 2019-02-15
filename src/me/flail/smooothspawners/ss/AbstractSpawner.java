package me.flail.smooothspawners.ss;

import java.util.HashMap;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.flail.smooothspawners.API.Spawner;
import me.flail.smooothspawners.ss.Utilities.Tools;

public abstract class AbstractSpawner extends HashMap<ItemStack, String> implements Spawner {

	private static final long serialVersionUID = 1L;
	public ItemStack item;
	public String type;

	public AbstractSpawner(ItemStack item, String type) {
		this.type = type;
		this.item = item;
	}

	private Spawner spawnerType(ItemStack item) {
		if (item instanceof CreatureSpawner) {
			CreatureSpawner creatureSpawner = (CreatureSpawner) item;
			String type = creatureSpawner.getSpawnedType().name();

			this.clear();
			this.put(item, type);

			return this;
		}

		return null;
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

		if (this.item() instanceof CreatureSpawner) {
			CreatureSpawner spawnerItem = (CreatureSpawner) this.item();

			spawnerItem.setSpawnedType(type);

			this.clear();
			this.put((ItemStack) spawnerItem, this.type);
		}

		return this.convert();
	}

	@Override
	public void setDisplayName(String name) {
		Tools tools = new Tools();
		ItemStack newItem = this.item();
		if (newItem.hasItemMeta()) {
			ItemMeta im = this.item().getItemMeta();
			im.setDisplayName(tools.chat(name));
			newItem.setItemMeta(im);

			this.put(newItem, type);
		}
	}

}
