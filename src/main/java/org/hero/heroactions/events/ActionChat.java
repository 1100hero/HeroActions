package org.hero.heroactions.events;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.hero.heroactions.HeroActions;

public class ActionChat implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChatEvent(PlayerChatEvent event){
        if(event.getMessage().startsWith("*") && event.getMessage().endsWith("*") && !event.getMessage().startsWith("*bisbiglio*") && !event.getMessage().startsWith("*urlo*")){
            event.setCancelled(true);
            Plugin plugin = HeroActions.getPlugin(HeroActions.class);
            final String finalMessage = plugin.getConfig().getString("a.message").replace("&", "§").replace("%player%", event.getPlayer().getName()).replace("%message%", event.getMessage().substring(1, event.getMessage().length()-1));
            Location loc = event.getPlayer().getLocation();
            loc.getWorld().getNearbyPlayers(loc, 10).forEach(s -> event.setMessage(finalMessage));
        }
    }
}
