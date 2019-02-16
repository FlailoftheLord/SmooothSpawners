package me.flail.smooothspawners.API.Spawner;

import java.util.Map;

import org.bukkit.Location;

import me.flail.smooothspawners.API.InvalidSpawnerException;

public interface SpawnerBlock extends Map<Spawner, Location> {

	/**
	 * Gets the {@link Spawner} object related to this SpawnerBlock.
	 *
	 * @return the Spawner.
	 */
	Spawner spawner();

	/**
	 * Gets the location of this Spawner block.
	 *
	 * @return this spawners' location, including the world.
	 */
	Location location() throws InvalidSpawnerException;

	/**
	 * Changes the type of this spawner block.
	 *
	 * @param spawner the new Spawner type to change to.
	 * @return the updated SpawnerBlock
	 */
	SpawnerBlock changeType(Spawner spawner);

}
