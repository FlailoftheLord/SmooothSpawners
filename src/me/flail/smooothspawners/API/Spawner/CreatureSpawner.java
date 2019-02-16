package me.flail.smooothspawners.API.Spawner;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.flail.smooothspawners.ss.Spawner.AbstractSpawner;

public class CreatureSpawner extends AbstractSpawner {

	/**
	 * Uid
	 */
	private static final long serialVersionUID = 1L;

	public CreatureSpawner(ItemStack item, String type) {
		super(item, type);
		this.clear();
		this.put(item, type);
	}

	public Spawner create() throws NullPointerException {

		this.setType(EntityType.valueOf(type.toUpperCase()));

		this.clear();
		this.put(item, type);
		if (this.isEmpty()) {
			throw new NullPointerException();
		}

		return this;

	}

}
