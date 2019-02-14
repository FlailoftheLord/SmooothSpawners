package me.flail.smooothspawners.API;

import java.util.Map;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public interface Spawner extends Map<ItemStack, String> {

	/**
	 *
	 * @return the name of the spawner creature type.
	 */
	String type();

	/**
	 *
	 * @return the spawner ItemStack.
	 */
	ItemStack item();

	/**
	 * converts a regular HashMap to a fancy Spawner object.
	 *
	 * @return the converted spawner object, provided the HashMap is not null
	 */
	Spawner convert(ItemStack item, String type);

	/**
	 * Gets the spawner type of this spawner.
	 *
	 * @return A glorified HashMap containing the spawner ItemStack and the spawner
	 *         item type. Will return null if the item isn't a spawner.
	 */
	Spawner getType();

	/**
	 * Sets the spawner type of the itemstack if its a spawner.
	 *
	 * @param spawner the itemstack to set the spawner type of.
	 * @param type    type of Entity for the spawner to spawn.
	 * @return true if it set the spawner type successfully, false otherwise.
	 */
	Spawner setType(EntityType type);

	/**
	 * Set the display name of the spawner item.s
	 * 
	 * @param name
	 */
	void setDisplayName(String name);

}
