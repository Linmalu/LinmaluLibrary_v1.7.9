package com.linmalu.library.api;

import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;

public class LinmaluAutoRespawn
{
	public static void Respawn(Player player)
	{
		ProtocolManager pm = ProtocolLibrary.getProtocolManager();
		PacketContainer pc = pm.createPacket(PacketType.Play.Client.CLIENT_COMMAND);
		try
		{
			pm.recieveClientPacket(player, pc);
		}
		catch (Exception e)
		{
		}
	}
}
