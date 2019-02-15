package me.flail.smooothspawners.API;

import org.bukkit.inventory.ItemStack;

import me.flail.smooothspawners.ss.AbstractSpawner;

public class CreatureSpawner extends AbstractSpawner {

	/**
	 * Uid
	 */
	private static final long serialVersionUID = 1L;

	public CreatureSpawner(ItemStack item, String type) {
		super(item, type);
	}

	public Spawner get() throws ClassCastException {

		this.clear();
		this.put(item, type);

		return this;

	}

}
