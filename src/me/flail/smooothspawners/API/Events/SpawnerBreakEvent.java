package me.flail.smooothspawners.API.Events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class SpawnerBreakEvent extends BlockBreakEvent {

	public SpawnerBreakEvent(Block block, Player player) {
		super(block, player);
	}

}
