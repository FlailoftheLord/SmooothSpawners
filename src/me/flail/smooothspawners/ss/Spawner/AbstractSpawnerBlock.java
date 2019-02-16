package me.flail.smooothspawners.ss.Spawner;

import org.bukkit.Location;

import me.flail.smooothspawners.API.InvalidSpawnerException;
import me.flail.smooothspawners.API.Spawner.Spawner;
import me.flail.smooothspawners.API.Spawner.SpawnerBlock;

public abstract class AbstractSpawnerBlock implements SpawnerBlock {

	@Override
	public Spawner spawner() {
		return this.keySet().toArray(new Spawner[] {})[0];

	}

	@Override
	public Location location() throws InvalidSpawnerException {
		try {
			return this.values().toArray(new Location[] {})[0];
		} catch (Throwable t) {
			throw new InvalidSpawnerException(this.spawner());
		}
	}

	@Override
	public SpawnerBlock changeType(Spawner spawner) {

		return null;
	}

}
