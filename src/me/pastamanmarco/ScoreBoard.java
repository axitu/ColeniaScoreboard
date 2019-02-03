package me.pastamanmarco;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ScoreBoard extends JavaPlugin implements Listener{
	
	ScoreboardManager manager = this.getServer().getScoreboardManager();
	Scoreboard board = manager.getNewScoreboard();
	boolean sbo = true;
	String prefix = ChatColor.AQUA + "[" + ChatColor.DARK_AQUA + "Colenia" + ChatColor.AQUA + "] ";
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		
		
		
		
		
		Objective serverName = board.registerNewObjective("ServerName", "");
		serverName.setDisplaySlot(DisplaySlot.SIDEBAR);
		//serverName.setDisplayName(ChatColor.DARK_AQUA + "Colenia");

		
		Score kills = serverName.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Kill Count:");
		Score killCount = serverName.getScore(ChatColor.WHITE + "" + ChatColor.BOLD + "" + Bukkit.getPlayer("name").getStatistic(Statistic.PLAYER_KILLS));
		kills.setScore(16);
		killCount.setScore(15);
		
		
		
		
		String title = ChatColor.DARK_AQUA + ""  + ChatColor.BOLD + "Colenia";
		char[] split = title.toCharArray();
		
		new BukkitRunnable() {
			int counter = 0;
			String finalTitle = "";
			@Override
			public void run() {
				if (counter < split.length) {
					String letter = String.valueOf(split[counter]);
					counter += 1;
					String space = "";
					for(int i = 0; i < split.length - counter; i++) {
						space += " ";
					}
					finalTitle = finalTitle  + letter;
					serverName.setDisplayName(finalTitle);
					
				} else {
					finalTitle = "";
					String space = "";
					for(int i = 0; i < split.length - counter; i++) {
						space += " ";
						serverName.setDisplayName(space);
						counter = 0;
					}
				}
				
			}
		}.runTaskTimer(this, 0, 10);
	}
	
	@EventHandler
	public void joinSB(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(sbo == true) {
			player.setScoreboard(board);
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Remember, you can turn off the scoreboard by doing " + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "/colsb");
		} else {
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Remember, you can turn on the scoreboard by doing " + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "/colsb");
		}
		

		
		
		
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("colsb")) {
			if (sbo == true) {
				sbo = false;
				player.sendMessage(prefix + "Scoreboard has been removed!");
				player.getScoreboard();
			} else {
				player.sendMessage(prefix + "Scoreboard has been shown!");
				player.setScoreboard(board);
				sbo = true;
			}
			return true;
		}
		
		return false;
	}
}
