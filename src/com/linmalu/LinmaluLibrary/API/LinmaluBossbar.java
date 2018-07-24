package com.linmalu.LinmaluLibrary.API;

import org.bukkit.entity.Player;

@Deprecated
public class LinmaluBossbar
{
	public static void setMessage(String message)
	{
		com.linmalu.library.api.LinmaluBossbar.sendMessage(message);
	}
	public static void setMessage(String message, float health)
	{
		com.linmalu.library.api.LinmaluBossbar.sendMessage(message, health);
	}
	public static void setMessage(Player player, String message)
	{
		com.linmalu.library.api.LinmaluBossbar.sendMessage(player, message);
	}
	public static void setMessage(Player player, String message, float health)
	{
		com.linmalu.library.api.LinmaluBossbar.sendMessage(player, message, health);
	}
}
