package com.edwardtheward.wardfake;

import java.util.logging.Logger;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.edwardtheward.wardfake.Commands.Commands;
import com.edwardtheward.wardfake.Listeners.JoinListener;
import com.edwardtheward.wardfake.Listeners.QuitListener;

public class Main extends JavaPlugin implements Listener
{
	public final Logger logger = Logger.getLogger("wardFake");
	
	@Override
	public void onDisable()
	{
		this.logger.info("Disabled.");
	}
	
	@Override
	public void onEnable()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents((Listener)new JoinListener(this), (Plugin)this);
		pm.registerEvents((Listener)new QuitListener(this), (Plugin)this);
		Commands cmds = new Commands(this);
		this.getCommand("wardfake").setExecutor((CommandExecutor)cmds);
		
		this.logger.info("Enabled.");
		saveDefaultConfig();
	}

	

}