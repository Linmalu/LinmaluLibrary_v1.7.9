package com.linmalu.library.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class LinmaluConfig extends YamlConfiguration
{
	private final File file;
	private boolean autoSave;

	public LinmaluConfig(File file)
	{
		this(file, true);
	}
	public LinmaluConfig(File file, boolean autoSave)
	{
		this.file = file;
		this.autoSave = autoSave;
		reload();
	}
	public boolean isAutoSave()
	{
		return autoSave;
	}
	public void setAutoSave(boolean autoSave)
	{
		this.autoSave = autoSave;
	}
	@Override
	public void set(String path, Object value)
	{
		super.set(path, value);
		if(autoSave)
		{
			save();
		}
	}
	public boolean isLinmaluLocation(String path)
	{
		return isSet(path) && get(path) instanceof LinmaluLocation;
	}
	public LinmaluLocation getLinmaluLocation(String path)
	{
		return getLocation(path, new LinmaluLocation(Bukkit.getWorlds().get(0), 0, 0, 0));
	}
	public LinmaluLocation getLocation(String path, LinmaluLocation def)
	{
		if(isLinmaluLocation(path))
		{
			return (LinmaluLocation)get(path);
		}
		return def;
	}
	public boolean isLinmaluSquareLocation(String path)
	{
		return isSet(path) && get(path) instanceof LinmaluSquareLocation;
	}
	public LinmaluSquareLocation getLinmaluSquareLocation(String path)
	{
		return getLinmaluSquareLocation(path, new LinmaluSquareLocation(Bukkit.getWorlds().get(0), 0, 0, 0, 0, 0, 0));
	}
	public LinmaluSquareLocation getLinmaluSquareLocation(String path, LinmaluSquareLocation def)
	{
		if(isLinmaluSquareLocation(path))
		{
			return (LinmaluSquareLocation)get(path);
		}
		return def;
	}
	public <T> List<T> getListData(String path)
	{
		return getListData(path, new ArrayList<T>());
	}
	@SuppressWarnings("unchecked")
	public <T> List<T> getListData(String path, List<T> def)
	{
		try
		{
			def = (List<T>)getList(path, def);
		}
		catch(Exception e)
		{
		}
		return def;
	}
	public void remove(String key)
	{
		set(key, null);
	}
	public void clear()
	{
		getKeys(false).iterator().forEachRemaining(this::remove);
	}
	public void reload()
	{
		try
		{
			load(file);
		}
		catch(FileNotFoundException e)
		{
		}
		catch(IOException | InvalidConfigurationException e)
		{
			e.printStackTrace();
		}
	}
	public void save()
	{
		try
		{
			save(file);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public String saveToString()
	{
		String data = new String();
		boolean first = true;
		for(String s : super.saveToString().split("\\\\u"))
		{
			if(s.length() >= 4 && !first)
			{
				data += (char)Integer.parseInt(s.substring(0, 4), 16);
				if(s.length() >= 5)
				{
					data += s.substring(4);
				}
			}
			else
			{
				data += s;
				first = false;
			}
		}
		return data;
	}
	@Override
	public void save(File file) throws IOException
	{
		Validate.notNull(file, "File cannot be null");
		Files.createParentDirs(file);
		String data = saveToString();
		try(Writer writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8))
		{
			writer.write(data);
		}
	}
	@Override
	public void load(File file) throws FileNotFoundException, IOException, InvalidConfigurationException
	{
		Validate.notNull(file, "File cannot be null");
		FileInputStream stream = new FileInputStream(file);
		load(new InputStreamReader(stream, Charsets.UTF_8));
	}
}
