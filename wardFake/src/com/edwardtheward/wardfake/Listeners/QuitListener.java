package com.edwardtheward.wardfake.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.edwardtheward.wardfake.Main;

import net.md_5.bungee.api.ChatColor;

public class QuitListener implements Listener
{
	private Main plugin;
	
	public QuitListener(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent quite)
	{
		Player leavep = quite.getPlayer();
		boolean lenabled = plugin.getConfig().getBoolean("is-leave-message-enabled-on-quit");
		String lcode = plugin.getConfig().getString("leave-message");
		String lconfig = lcode.replace("{NAME}", leavep.getName());
		if(lenabled)
		{
			quite.setQuitMessage(ChatColor.translateAlternateColorCodes('&', lconfig));
		} else
		{
			quite.setQuitMessage("");
		}
	}
	

}
