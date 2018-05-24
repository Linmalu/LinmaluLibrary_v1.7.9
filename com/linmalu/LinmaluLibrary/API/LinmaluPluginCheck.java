package com.linmalu.LinmaluLibrary.API;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

@Deprecated
public class LinmaluPluginCheck
{
	public static boolean checkVersion(Plugin plugin)
	{
		new Thread(() ->
		{
			try
			{
				URLConnection url = new URL("http://minecraft.linmalu.com/minecraft/version.html").openConnection();
				url.setDoOutput(true);
				try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(url.getOutputStream())))
				{
					bw.write("name=" + plugin.getDescription().getName());
					bw.write("&version=" + plugin.getDescription().getVersion());
					bw.write("&bukkit=" + Bukkit.getVersion());
					bw.write("&java=" + System.getProperty("java.version"));
					bw.flush();
				}
			}
			catch(Exception e)
			{
			}
		}).start();
		return false;
	}
	public static boolean checkMD5(Plugin plugin)
	{
		return false;
	}
}
