package prod.spooky.kitduels.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SetDuelTabList implements TabCompleter {
    public ArrayList<String> arena = new ArrayList<>();
    public ArrayList<String> spawnSide = new ArrayList<>();
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length==1) {
            arena.add("Arena1");
            arena.add("Arena2");
            arena.add("Arena3");
            arena.add("Arena4");
            return arena;
        }else if (strings.length==2){
            spawnSide.add("spawn1");
            spawnSide.add("spawn2");
            return spawnSide;
        }
        return null;
    }
}
