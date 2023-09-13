package prod.spooky.kitduels.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;
import prod.spooky.kitduels.utils.Duel;


public class DuelCommand implements CommandExecutor, Listener {

    Duel duel = new Duel();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player p){
            if (strings.length<1) {
                p.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"Please Select a player to duel");
                return true;
            }
            Player target = Bukkit.getPlayer(strings[0]);
            if (!(target ==null) && Duel.playersInDuel.contains(target.getUniqueId())){
                Duel.playersInDuel.add(p.getUniqueId());
                Duel.playersInDuel.add(target.getUniqueId());
                duel.startDuel(p,target);

            }else {
               p.sendMessage("Player is currently offline.");
            }
        }else {
            System.out.println("This command must be executed by a player");
        }
        return true;
    }

    @EventHandler
    public void onDuelEnd(PlayerDeathEvent event){
        if ((event.getPlayer().getKiller()!= null)){
            if (Duel.playersInDuel.contains(event.getPlayer().getUniqueId())){
                Duel.playersInDuel.remove(event.getPlayer().getUniqueId());
                Duel.playersInDuel.remove(event.getPlayer().getKiller().getUniqueId());
                Bukkit.dispatchCommand(event.getPlayer().getKiller(),"hub");
                Bukkit.unloadWorld("Arena",true);
                WorldCreator worldCreator = new WorldCreator("Arena");
                worldCreator.createWorld();
            }
        }
    }

}
