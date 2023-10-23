package prod.spooky.kitduels.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class GotoCommand implements CommandExecutor {

    public static ArrayList<String> map = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        map.add("SkyPort"); map.add("Museum"); map.add("Arena"); map.add("Highset"); map.add("Fractal");
        Player player = (Player) commandSender;
        if (map.contains(strings[0])){
            World arena = Objects.requireNonNull(Bukkit.getWorld(strings[0]));
            player.teleport(arena.getSpawnLocation());
        }


        return true;
    }
}
