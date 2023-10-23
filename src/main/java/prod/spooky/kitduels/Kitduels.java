package prod.spooky.kitduels;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import prod.spooky.kitduels.commands.*;
import prod.spooky.kitduels.events.OnPlayerJoin;
import prod.spooky.kitduels.events.OnPlayerDeath;
import prod.spooky.kitduels.listeners.CompassListener;
import prod.spooky.kitduels.listeners.MenuListener;
import prod.spooky.kitduels.menusystem.PlayerMenuUtility;

import java.util.HashMap;

public final class Kitduels extends JavaPlugin {

    private static Kitduels plugin;
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;
        registerEvents();
        setCommands();
        Bukkit.unloadWorld("hub_nether", false);
        Bukkit.unloadWorld("hub_the_end", false);
        WorldCreator arenaLoader = new WorldCreator("Arena");
        arenaLoader.createWorld();
        WorldCreator arenaLoader2 = new WorldCreator("Museum");
        arenaLoader2.createWorld();
        WorldCreator arenaLoader4 = new WorldCreator("Highset");
        arenaLoader4.createWorld();
        WorldCreator arenaLoader5 = new WorldCreator("Fractal");
        arenaLoader5.createWorld();


    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new CompassListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new DuelCommand(), this);

        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    private void setCommands(){
        getCommand("Duel").setExecutor(new DuelCommand());
        getCommand("Duel").setTabCompleter(new DuelTabList());
        getCommand("Hub").setExecutor(new HubCommand());
        getCommand("accept").setExecutor(new AcceptCommand());
        getCommand("decline").setExecutor(new DeclineCommand());
        getCommand("goto").setExecutor(new GotoCommand());
    }

    //Provide a player and return a menu system for that player
    //create one if they don't already have one
    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) {
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);
            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p);
        }
    }

    public static Kitduels getPlugin() {
        return plugin;
    }
}
