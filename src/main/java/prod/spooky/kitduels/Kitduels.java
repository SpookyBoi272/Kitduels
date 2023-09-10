package prod.spooky.kitduels;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import prod.spooky.kitduels.commands.DuelCommand;
import prod.spooky.kitduels.events.OnCompassMenu;
import prod.spooky.kitduels.events.onPlayerJoin;

import java.util.Objects;

public final class Kitduels extends JavaPlugin {

    @Override
    public void onEnable() {
        registerEvents();
        setCommands();
        Bukkit.unloadWorld("hub_nether", false);
        Bukkit.unloadWorld("hub_the_end", false);
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new onPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnCompassMenu(), this);
    }

    private void setCommands(){
        Objects.requireNonNull(getCommand("Duel")).setExecutor(new DuelCommand());
    }
}
