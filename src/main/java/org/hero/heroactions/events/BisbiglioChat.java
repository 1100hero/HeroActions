package org.hero.heroactions.events;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.hero.heroactions.HeroActions;
import org.hero.heroactions.utils.Cooldown;

public class BisbiglioChat implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChatEvent(PlayerChatEvent event){
        if(event.getMessage().startsWith("*bisbiglio*")){
            event.setCancelled(true);
            final String COOLDOWN_NAME = "bisbiglio.cooldown";
            Plugin plugin = HeroActions.getPlugin(HeroActions.class);
            final String finalMessage = plugin.getConfig().getString("bisbiglio.message").replace("&", "§").replace("%player%", event.getPlayer().getName())
                    .replace("%message%", event.getMessage().substring(11));
            Location loc = event.getPlayer().getLocation();
            final String PREFIX = plugin.getConfig().getString("prefix.display").replace("&", "§");
            final int COOLDOWN = plugin.getConfig().getInt("bisbiglio.cooldown");
            if(Cooldown.isInCooldown(event.getPlayer(), COOLDOWN_NAME)){
                final String TIME_TO_WAIT = plugin.getConfig().getString("bisbiglio.timeToWait").replace("&","§").
                        replace("%time%", String.valueOf(Cooldown.getTimeLeft(event.getPlayer(), COOLDOWN_NAME)));;
                event.getPlayer().sendMessage(PREFIX+TIME_TO_WAIT);
            }else{
                new Cooldown(event.getPlayer(), COOLDOWN_NAME, COOLDOWN).start();
                loc.getWorld().getNearbyPlayers(loc, 1).forEach(s -> event.setMessage(finalMessage));
            }
        }
    }
}
