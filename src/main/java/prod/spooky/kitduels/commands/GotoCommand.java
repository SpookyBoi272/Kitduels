package prod.spooky.kitduels.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import prod.spooky.kitduels.utils.ConfigReader;

import java.util.Objects;

public class GotoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        if (ConfigReader.mapList.contains(strings[0])){
            World arena = Objects.requireNonNull(Bukkit.getWorld(strings[0]));
            player.teleport(arena.getSpawnLocation());
        }


        return true;
    }
}
