package me.flail.smooothspawners.API;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class CreatureSpawner {

	ItemStack item;
	EntityType type;

	public CreatureSpawner(ItemStack item, EntityType type) {
		this.item = item;
		this.type = type;
	}

	public Spawner get() {
		Map<ItemStack, String> spawnerMap = new HashMap<>();
		spawnerMap.put(item, type.toString());

		return (Spawner) spawnerMap;

	}

}
