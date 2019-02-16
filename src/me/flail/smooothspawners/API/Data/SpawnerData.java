package me.flail.smooothspawners.API.Data;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.smooothspawners.SmooothSpawners;
import me.flail.smooothspawners.API.Spawner.SpawnerBlock;

public class SpawnerData extends HashMap<UUID, SpawnerBlock> {

	private SmooothSpawners plugin = JavaPlugin.getPlugin(SmooothSpawners.class);

	public SpawnerData(UUID playerUuid) {
		this.clear();
		this.put(playerUuid, this.getBlock());
	}

	public SpawnerBlock getBlock() {
		return this.values().toArray(new SpawnerBlock[] {})[0];
	}

	public UUID getId() {
		return this.keySet().toArray(new UUID[] {})[0];
	}

	public OfflinePlayer player() {
		return plugin.server.getOfflinePlayer(this.getId());

	}

}
