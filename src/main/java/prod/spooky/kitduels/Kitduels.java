package prod.spooky.kitduels;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;
import prod.spooky.kitduels.commands.DuelCommand;
import prod.spooky.kitduels.events.Onplayerjoin;

public final class Kitduels extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
//        System.out.println("Spooky Duels Plugin Successfully Loaded");
        getServer().getPluginManager().registerEvents(new Onplayerjoin(), this);
        getCommand("Duel").setExecutor(new DuelCommand());
        WorldCreator worldCreator = new WorldCreator("Arena");
        World world = worldCreator.createWorld();
        Bukkit.unloadWorld("hub_nether", false);
        Bukkit.unloadWorld("hub_the_end", false);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
