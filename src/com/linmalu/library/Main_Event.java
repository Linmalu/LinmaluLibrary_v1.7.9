package com.linmalu.library;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;

import com.linmalu.library.api.LinmaluBlacklist;
import com.linmalu.library.api.LinmaluServer;
import com.linmalu.library.keyboard.LinmaluKeyboardManager;
import com.linmalu.library.network.LinmaluNetwork;

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
		LinmaluNetwork.getInstance().PlayerJoinEvent(player.getUniqueId());
	}

	@EventHandler
	public void Event(PlayerQuitEvent event)
	{
		UUID player = event.getPlayer().getUniqueId();
		LinmaluKeyboardManager.getInstance().PlayerQuitEvent(player);
		LinmaluNetwork.getInstance().PlayerQuitEvent(player);
	}

	@EventHandler
	public void Event(PlayerRegisterChannelEvent event)
	{
		if(event.getChannel().equalsIgnoreCase(LinmaluNetwork.CHANNEL))
		{
			LinmaluNetwork.getInstance().sendConnectMessage(event.getPlayer());
		}
	}
}
