package com.edwardtheward.wardfake.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.edwardtheward.wardfake.Main;

import net.md_5.bungee.api.ChatColor;

public class JoinListener implements Listener 
{
	private Main plugin;
	
	public JoinListener(Main plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent joine)
	{
		Player joinp = joine.getPlayer();
		boolean jenabled = plugin.getConfig().getBoolean("is-join-message-enabled-on-join");
		String jcode = plugin.getConfig().getString("join-message");
		String jconfig = jcode.replace("{NAME}", joinp.getName());
		if(jenabled)
		{	
			joine.setJoinMessage(ChatColor.translateAlternateColorCodes('&', jconfig));
		} else
		{
			joine.setJoinMessage("");
		}
	}

}
