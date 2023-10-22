package prod.spooky.kitduels;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;
import prod.spooky.kitduels.commands.AcceptCommand;
import prod.spooky.kitduels.commands.DeclineCommand;
import prod.spooky.kitduels.commands.DuelCommand;
import prod.spooky.kitduels.commands.HubCommand;
import prod.spooky.kitduels.events.OnCompassMenu;
import prod.spooky.kitduels.events.OnPlayerJoin;
import prod.spooky.kitduels.events.OnPlayerDeath;

public final class Kitduels extends JavaPlugin {

    @Override
    public void onEnable() {
        registerEvents();
        setCommands();
        Bukkit.unloadWorld("hub_nether", false);
        Bukkit.unloadWorld("hub_the_end", false);
        WorldCreator arenaLoader = new WorldCreator("Arena");
        arenaLoader.createWorld();
        WorldCreator arenaLoader2 = new WorldCreator("Museum");
        arenaLoader2.createWorld();
        WorldCreator arenaLoader3 = new WorldCreator("SkyPort");
        arenaLoader3.createWorld();
        WorldCreator arenaLoader4 = new WorldCreator("Highset");
        arenaLoader4.createWorld();
        WorldCreator arenaLoader5 = new WorldCreator("Fractal");
        arenaLoader5.createWorld();
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnCompassMenu(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new DuelCommand(), this);
    }

    private void setCommands(){
        getCommand("Duel").setExecutor(new DuelCommand());
        getCommand("Hub").setExecutor(new HubCommand());
        getCommand("accept").setExecutor(new AcceptCommand());
        getCommand("decline").setExecutor(new DeclineCommand());
    }
}
