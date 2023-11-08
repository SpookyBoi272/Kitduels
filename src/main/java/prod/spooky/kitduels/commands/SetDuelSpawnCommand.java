package prod.spooky.kitduels.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import prod.spooky.kitduels.utils.ConfigReader;

import java.util.ArrayList;

public class SetDuelSpawnCommand implements CommandExecutor {
    public ArrayList<String> arena = new ArrayList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player p){
            arena.add("Arena1"); arena.add("Arena2"); arena.add("Arena3"); arena.add("Arena4");
            if (arena.contains(strings[0]) &&(strings[1].equals("spawn1") || strings[1].equals("spawn2"))){
                ConfigReader.setArenaSpawn(strings[0], strings[1], p.getLocation());
            }else {
                p.sendMessage("Invalid Usage of Command. Try /help");
            }
        }else {
            System.out.println("This command must be executed by player.");
        }

        return true;
    }
}
