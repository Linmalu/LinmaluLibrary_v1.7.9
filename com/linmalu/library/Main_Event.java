package com.linmalu.library;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.linmalu.library.api.LinmaluBlacklist;
import com.linmalu.library.api.LinmaluServer;

public class Main_Event implements Listener
{
	@EventHandler
	public void Event(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if(player.isOp())
		{
			LinmaluServer.version(LinmaluLibrary.getMain(), player);
		}
		LinmaluBlacklist.check(player);
	}
}
