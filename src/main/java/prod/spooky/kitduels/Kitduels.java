package prod.spooky.kitduels;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import prod.spooky.kitduels.commands.*;
import prod.spooky.kitduels.events.OnPlayerJoin;
import prod.spooky.kitduels.events.OnPlayerDeath;
import prod.spooky.kitduels.listeners.CompassListener;
import prod.spooky.kitduels.listeners.DuelEndListener;
import prod.spooky.kitduels.listeners.MenuListener;
import prod.spooky.kitduels.menusystem.PlayerMenuUtility;
import prod.spooky.kitduels.utils.ConfigReader;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public final class Kitduels extends JavaPlugin {

    private static Kitduels plugin;
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        registerEvents();
        setCommands();
        loadArenas();
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new CompassListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new DuelEndListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    private void setCommands(){
        getCommand("Duel").setExecutor(new DuelCommand());
        getCommand("Duel").setTabCompleter(new DuelTabList());
        getCommand("Hub").setExecutor(new HubCommand());
        getCommand("accept").setExecutor(new AcceptCommand());
        getCommand("decline").setExecutor(new DeclineCommand());
        getCommand("goto").setExecutor(new GotoCommand());
        getCommand("setDuelSpawn").setExecutor(new SetDuelSpawnCommand());
        getCommand("setDuelSpawn").setTabCompleter(new SetDuelTabList());
    }

    private void loadArenas(){
        ArrayList<String> mapsList = ConfigReader.getMapsList();
        Bukkit.unloadWorld("hub_nether", false);
        Bukkit.unloadWorld("hub_the_end", false);
        WorldCreator arenaLoader = new WorldCreator(mapsList.get(0));
        arenaLoader.createWorld();
        WorldCreator arenaLoader2 = new WorldCreator(mapsList.get(1));
        arenaLoader2.createWorld();
        WorldCreator arenaLoader4 = new WorldCreator(mapsList.get(2));
        arenaLoader4.createWorld();
        WorldCreator arenaLoader5 = new WorldCreator(mapsList.get(3));
        arenaLoader5.createWorld();
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
