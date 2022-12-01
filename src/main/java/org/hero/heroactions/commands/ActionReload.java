package org.hero.heroactions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.hero.heroactions.HeroActions;
import org.jetbrains.annotations.NotNull;

public class ActionReload implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Plugin plugin = HeroActions.getPlugin(HeroActions.class);
        final String PREFIX = plugin.getConfig().getString("prefix.display").replace("&", "§");
        final String NO_PERMISSIONS = plugin.getConfig().getString("reload-config.noPermissions").replace("&", "§");
        final String WRONG_LENGTH = plugin.getConfig().getString("reload-config.wrongLength").replace("&", "§");
        final String MESSAGE_RELOADED = plugin.getConfig().getString("reload-config.message").replace("&", "§");
        if(!sender.hasPermission("heroaction.reload")){
            sender.sendMessage(PREFIX+NO_PERMISSIONS);
            return true;
        }
        if(args.length != 0){
            sender.sendMessage(PREFIX+WRONG_LENGTH);
            return true;
        }
        plugin.reloadConfig();
        sender.sendMessage(PREFIX+MESSAGE_RELOADED);
        return true;
    }
}
