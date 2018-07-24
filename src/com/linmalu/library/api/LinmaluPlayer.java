package com.linmalu.library.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class LinmaluPlayer
{
	public static boolean addPotionEffect(Player player, PotionEffect potion)
	{
		if(player.hasPotionEffect(potion.getType()))
		{
			for(PotionEffect pe : player.getActivePotionEffects())
			{
				if(pe.getType().getName().equals(potion.getType().getName()) && (pe.getAmplifier() < potion.getAmplifier() || (pe.getAmplifier() == potion.getAmplifier() && pe.getDuration() < potion.getDuration())))
				{
					player.addPotionEffect(potion, true);
					return true;
				}
			}
			return false;
		}
		else
		{
			player.addPotionEffect(potion, true);
			return true;
		}
	}
	public static Collection<? extends Player> getOnlinePlayers()
	{
		return Arrays.asList(Bukkit.getOnlinePlayers());
	}
	public static List<Player> getPlayers(String name)
	{
		if(name.equals("@"))
		{
			return Arrays.asList(Bukkit.getOnlinePlayers());
		}
		List<Player> players = new ArrayList<>();
		@SuppressWarnings("deprecation")
		Player player = Bukkit.getPlayer(name);
		if(player != null)
		{
			players.add(player);
		}
		return players;
	}
}
