package com.edwardtheward.wardfake;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

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
		pm.registerEvents(this, this);
		
		this.logger.info("Enabled.");
		saveDefaultConfig();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent joine)
	{
		Player joinp = joine.getPlayer();
		boolean jenabled = getConfig().getBoolean("is-join-message-enabled-on-join");
		String jcode = getConfig().getString("join-message");
		String jconfig = jcode.replace("{NAME}", joinp.getName());
		if(jenabled)
		{	
			joine.setJoinMessage(ChatColor.translateAlternateColorCodes('&', jconfig));
		} else
		{
			joine.setJoinMessage("");
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent quite)
	{
		Player leavep = quite.getPlayer();
		boolean lenabled = getConfig().getBoolean("is-leave-message-enabled-on-quit");
		String lcode = getConfig().getString("leave-message");
		String lconfig = lcode.replace("{NAME}", leavep.getName());
		if(lenabled)
		{
			quite.setQuitMessage(ChatColor.translateAlternateColorCodes('&', lconfig));
		} else
		{
			quite.setQuitMessage("");
		}
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
	{
		if(commandLabel.equalsIgnoreCase("wardfake") && sender.hasPermission("wardfake.use"))
		{
			if(args.length == 0)
			{
				sender.sendMessage(ChatColor.RED + "/wardfake <join, leave> <username, or leave blank for yourself>");
				return true;
			} else
			if(args.length == 1)
			{
				if(args[0].equalsIgnoreCase("join") && sender.hasPermission("wardfake.fakejoin.me"))
				{
					String cmdjoin = getConfig().getString("fake-join-message");
					String cmdconfigjoin = cmdjoin.replace("{NAME}", sender.getName());
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', cmdconfigjoin));
					return true;
				} else
				if(args[0].equalsIgnoreCase("leave") && sender.hasPermission("wardfake.fakeleave.me"))
				{
					String cmdleave = getConfig().getString("fake-leave-message");
					String cmdconfigleave = cmdleave.replace("{NAME}", sender.getName());
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', cmdconfigleave));
					return true;
				} else
				if(args[0].equalsIgnoreCase("reload"))
				{
					if(sender.hasPermission("wardfake.reloadconfig"))
					{
					reloadConfig();
					sender.sendMessage(ChatColor.RED + "[wardFake] Configuration has been reloaded.");
					return true;
					} else
					{
						sender.sendMessage(ChatColor.RED + "You have no permission.");
						return true;
					}
				} else
				if(!sender.hasPermission("wardfake.fakejoin.me") || !sender.hasPermission("wardfake.fakeleave.me"))
				{
					sender.sendMessage(ChatColor.RED + "You have no permission.");
					return true;
				} else
				{
					sender.sendMessage(ChatColor.RED + "/wardfake <join, leave> <username, or leave blank for yourself>");
					return true;
				}
			} else
			if(args.length == 2)
			{
				if(args[0].equalsIgnoreCase("join") && sender.hasPermission("wardfake.fakejoin.others"))
				{
					String cmdotherjoin = getConfig().getString("fake-join-message-others");
					String cmdconfigotherjoin = cmdotherjoin.replace("{NAME}", args[1]);
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', cmdconfigotherjoin));
					return true;
					
				} else
				if(args[0].equalsIgnoreCase("leave") && sender.hasPermission("wardfake.fakeleave.others"))
				{
					String cmdotherleave = getConfig().getString("fake-leave-message-others");
					String cmdconfigotherleave = cmdotherleave.replace("{NAME}", args[1]); 
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', cmdconfigotherleave));
					return true;
					
				} else
				if(!sender.hasPermission("wardfake.fakejoin.me") || !sender.hasPermission("wardfake.fakeleave.me") || !sender.hasPermission("wardfake.fakejoin.others") || !sender.hasPermission("wardfake.fakeleave.others"))
				{
					sender.sendMessage(ChatColor.RED + "You have no permission.");
					return true;
				} else
				{
					sender.sendMessage(ChatColor.RED + "/wardfake <join, leave> <username, or leave blank for yourself>");
					return true;
				}
			} else
			{
				sender.sendMessage(ChatColor.RED + "/wardfake <join, leave> <username, or leave blank for yourself>");
				return true;
			}
		}
		return false;
	}

}
