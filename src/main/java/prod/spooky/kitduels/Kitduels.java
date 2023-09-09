package prod.spooky.kitduels;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import prod.spooky.kitduels.commands.DuelCommand;

import java.sql.SQLOutput;

public final class Kitduels extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Spooky Duels Plugin Sussessfully Loaded");
        getCommand("Duel").setExecutor(new DuelCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
