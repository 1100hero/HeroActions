package org.hero.heroactions.commands;

import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.hero.heroactions.HeroActions;
import org.hero.heroactions.utils.Cooldown;
import org.jetbrains.annotations.NotNull;

public class Godo implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Plugin plugin = HeroActions.getPlugin(HeroActions.class);
        if(!(sender instanceof Player)){
            sender.sendMessage("La console non puo' eseguire questo comando.");
            return true;
        }
        Player player = (Player)sender;
        final String PREFIX = plugin.getConfig().getString("prefix.display").replace("&", "§");
        final String NO_PERMISSIONS = plugin.getConfig().getString("godo.noPermissions").replace("&", "§");
        final String WRONG_LENGTH = plugin.getConfig().getString("godo.wrongLength").replace("&", "§");
        if(args.length!=0){
            player.sendMessage(PREFIX+WRONG_LENGTH);
            return true;
        }
        final String PARTICLE = plugin.getConfig().getString("godo.particle");
        final int NOF_PARTICLES = plugin.getConfig().getInt("godo.numberOfParticles");
        final String TITLE = plugin.getConfig().getString("godo.title").replace("&", "§");
        final String SUBTITLE = plugin.getConfig().getString("godo.subtitle").replace("&", "§");
        final int FADE_IN = plugin.getConfig().getInt("godo.fadeIn");
        final int STAY = plugin.getConfig().getInt("godo.stay");
        final int FADE_OUT = plugin.getConfig().getInt("godo.fadeOut");
        final int COOLDOWN = plugin.getConfig().getInt("godo.cooldown");
        final String COOLDOWN_NAME = "godo.cooldown";
        if(Cooldown.isInCooldown(player, COOLDOWN_NAME)){
            final String TIME_TO_WAIT = plugin.getConfig().getString("godo.timeToWait").replace("&", "§").replace("%time%", String.valueOf(Cooldown.getTimeLeft(player, COOLDOWN_NAME)));
            player.sendMessage(PREFIX+TIME_TO_WAIT);
        }else{
            player.spawnParticle(Particle.valueOf(PARTICLE), player.getLocation(), NOF_PARTICLES, 0.5,1,0.5);
            player.sendTitle(TITLE, SUBTITLE, FADE_IN, STAY, FADE_OUT);
            new Cooldown(player, COOLDOWN_NAME, COOLDOWN).start();
        }
        return true;
    }
}
