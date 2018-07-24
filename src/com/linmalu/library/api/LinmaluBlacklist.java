package com.linmalu.library.api;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.linmalu.library.LinmaluLibrary;

public class LinmaluBlacklist
{
	public static void check(Player player)
	{
		new Thread(() ->
		{
			if(!getJSON(player, player.getUniqueId().toString()))
			{
				getJSON(player, player.getName());
			}
		}).start();
	}
	private static boolean getJSON(Player player, String check)
	{
		try
		{
			URLConnection url = new URL("http://api.mc-blacklist.kr/API/check/" + check).openConnection();
			url.setRequestProperty("User-Agent", LinmaluLibrary.getMain().getDescription().getName() + " v" + LinmaluLibrary.getMain().getDescription().getVersion());
			StringBuilder sb = new StringBuilder();
			try(InputStream in = url.getInputStream())
			{
				byte[] data = new byte[1024];
				int size;
				while((size = in.read(data)) != -1)
				{
					sb.append(new String(data, 0, size));
				}
			}
			JSONObject json = (JSONObject)new JSONParser().parse(sb.toString());
			if((boolean)json.get("blacklist"))
			{
				Bukkit.getScheduler().scheduleSyncDelayedTask(LinmaluLibrary.getMain(), () ->
				{
					player.kickPlayer(ChatColor.AQUA + "[MC-BlackList]\n" + ChatColor.RESET + (String)json.get("reason") + "\n" + ChatColor.GRAY + "문의 : http://mc-blacklist.kr/inquire");
				});
				return true;
			}
		}
		catch(Exception e)
		{
		}
		return false;
	}
}
