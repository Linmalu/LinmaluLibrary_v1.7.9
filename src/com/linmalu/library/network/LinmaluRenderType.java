package com.linmalu.library.network;

public enum LinmaluRenderType
{
	NONE,
	ALL,
	HELMET,
	PORTAL,
	CROSSHAIRS,
	BOSSHEALTH,
	ARMOR,
	HEALTH,
	FOOD,
	AIR,
	HOTBAR,
	EXPERIENCE,
	TEXT,
	HEALTHMOUNT,
	JUMPBAR,
	CHAT,
	PLAYER_LIST,
	DEBUG;

	public final short ID = (short)ordinal();

	public static LinmaluRenderType getLinmaluRenderType(int id)
	{
		for(LinmaluRenderType type : values())
		{
			if(type.ordinal() == id)
			{
				return type;
			}
		}
		return NONE;
	}

	public static LinmaluRenderType getLinmaluRenderType(String name)
	{
		for(LinmaluRenderType type : values())
		{
			if(name.equalsIgnoreCase(type.toString()))
			{
				return type;
			}
		}
		return NONE;
	}
}
