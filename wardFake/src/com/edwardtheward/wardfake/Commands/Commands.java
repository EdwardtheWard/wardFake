package com.edwardtheward.wardfake.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.edwardtheward.wardfake.Main;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor
{
	private Main plugin;
	
	public Commands(Main plugin)
	{
		this.plugin = plugin;
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
					String cmdjoin = plugin.getConfig().getString("fake-join-message");
					String cmdconfigjoin = cmdjoin.replace("{NAME}", sender.getName());
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', cmdconfigjoin));
					return true;
				} else
				if(args[0].equalsIgnoreCase("leave") && sender.hasPermission("wardfake.fakeleave.me"))
				{
					String cmdleave = plugin.getConfig().getString("fake-leave-message");
					String cmdconfigleave = cmdleave.replace("{NAME}", sender.getName());
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', cmdconfigleave));
					return true;
				} else
				if(args[0].equalsIgnoreCase("reload"))
				{
					if(sender.hasPermission("wardfake.reloadconfig"))
					{
						plugin.reloadConfig();
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
					String cmdotherjoin = plugin.getConfig().getString("fake-join-message-others");
					String cmdconfigotherjoin = cmdotherjoin.replace("{NAME}", args[1]);
					Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', cmdconfigotherjoin));
					return true;
					
				} else
				if(args[0].equalsIgnoreCase("leave") && sender.hasPermission("wardfake.fakeleave.others"))
				{
					String cmdotherleave = plugin.getConfig().getString("fake-leave-message-others");
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
