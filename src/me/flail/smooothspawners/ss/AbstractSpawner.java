package me.flail.smooothspawners.ss;

import java.util.HashMap;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.flail.smooothspawners.API.Spawner;
import me.flail.smooothspawners.ss.Utilities.Tools;

public abstract class AbstractSpawner implements Spawner {

	private Spawner spawnerType(ItemStack item) {
		if (item instanceof CreatureSpawner) {
			CreatureSpawner creatureSpawner = (CreatureSpawner) item;
			String type = creatureSpawner.getSpawnedType().name();

			Spawner spawner = (Spawner) new HashMap<ItemStack, String>();

			spawner.put(item, type);

			return spawner;
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
	public Spawner convert(ItemStack item, String type) {
		Spawner spawner = null;
		spawner = (Spawner) new HashMap<ItemStack, String>();
		spawner.put(item, type);

		return spawner;
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
		}

		return this.convert(this.item(), type.toString());
	}

	@Override
	public void setDisplayName(String name) {
		Tools tools = new Tools();
		if (this.item().hasItemMeta()) {
			ItemMeta im = this.item().getItemMeta();
			im.setDisplayName(tools.chat(name));
			this.item().setItemMeta(im);
		}
	}

}
