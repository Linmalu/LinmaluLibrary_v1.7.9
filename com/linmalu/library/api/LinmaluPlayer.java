package com.linmalu.library.api;

import java.util.Arrays;
import java.util.Collection;

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
}
