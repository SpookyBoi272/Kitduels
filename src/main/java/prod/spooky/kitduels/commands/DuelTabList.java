package prod.spooky.kitduels.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DuelTabList implements TabCompleter {
    public static ArrayList<String> kit = new ArrayList<>();
    public static ArrayList<String> map = new ArrayList<>();
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(strings.length==2) {
            kit.add("Shield"); kit.add("Buff"); kit.add("Sword");
            return kit;
        } else if (strings.length==3){
            map.add("Museum"); map.add("Arena"); map.add("Highset"); map.add("Fractal");
            return map;
        } else if (strings.length==4) {
            return null;
        }
        return null;
    }
}
