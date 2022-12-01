package org.hero.heroactions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.hero.heroactions.commands.*;
import org.hero.heroactions.events.ActionChat;
import org.hero.heroactions.events.BisbiglioChat;
import org.hero.heroactions.events.UrloChat;

public final class HeroActions extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        registerCommands();
        registerEvents();
        Bukkit.getLogger().info("HeroActions attivato.");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("HeroActions disabilitato.");
    }
    private void registerCommands(){
        this.getCommand("godo").setExecutor(new Godo());
        this.getCommand("a").setExecutor(new Action());
        this.getCommand("action").setExecutor(new ActionReload());
        this.getCommand("actionreload").setExecutor(new ActionReload());
    }
    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new ActionChat(), this);
        this.getServer().getPluginManager().registerEvents(new BisbiglioChat(), this);
        this.getServer().getPluginManager().registerEvents(new UrloChat(), this);
    }
}
