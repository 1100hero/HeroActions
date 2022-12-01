package org.hero.heroactions.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.hero.heroactions.HeroActions;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Action implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Plugin plugin = HeroActions.getPlugin(HeroActions.class);
        if(!(sender instanceof Player)){
            sender.sendMessage("La console non puo' eseguire questo comando.");
            return true;
        }
        Player player = (Player)sender;
        final String PREFIX = plugin.getConfig().getString("prefix.display").replace("&", "§");
        final String WRONG_LENGTH = plugin.getConfig().getString("a.wrongLength").replace("&", "§");
        if(args.length == 0){
            player.sendMessage(PREFIX+WRONG_LENGTH);
            return true;
        }
        List<String> list = new ArrayList<>(Arrays.asList(args));
        StringBuilder message = new StringBuilder();
        list.forEach(s->message.append(" ").append(s));
        final String finalMessage = plugin.getConfig().getString("a.message").replace("&", "§").replace("%player%", player.getName()).replace("%message%", message.substring(1));
        Location loc = player.getLocation();
        loc.getWorld().getNearbyPlayers(loc, 10).forEach(s -> s.sendMessage(finalMessage));
        return true;
    }
}
