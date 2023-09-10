package prod.spooky.kitduels.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import prod.spooky.kitduels.utils.Duel;

public class DuelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player p){
            if (strings.length<1) {
                p.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"Please Select a player to duel");

                return true;
            }
            Player target = Bukkit.getPlayer(strings[0]);
            if (!(target ==null)){
                new Duel(p , target);
            }else {
               p.sendMessage("Player is currently offline.");
            }
        }else {
            System.out.println("This command must be executed by a player");
        }

        return true;
    }
}
