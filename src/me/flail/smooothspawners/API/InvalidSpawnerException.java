package me.flail.smooothspawners.API;

import me.flail.smooothspawners.API.Spawner.Spawner;

public class InvalidSpawnerException extends Exception {

	private static final long serialVersionUID = 229912883285732793L;

	public InvalidSpawnerException(String dump) {
		super(dump);
	}

	public InvalidSpawnerException(Spawner spawner) {
		super("Invaid Spawner: " + spawner.toString());
	}

}
