package prod.spooky.kitduels.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import prod.spooky.kitduels.utils.HubItems;

import java.util.Objects;

public class HubCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player p) {
            p.teleport(Objects.requireNonNull(Bukkit.getWorld("hub")).getSpawnLocation());
            p.getInventory().clear();
            HubItems item = new HubItems();
            item.addItems(p);
            p.setInvulnerable(true);
            for (PotionEffectType type : PotionEffectType.values()) {
                p.removePotionEffect(type);
            }
        }
        return true;
    }
}
