package prod.spooky.kitduels.menusystem.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import prod.spooky.kitduels.menusystem.Menu;
import prod.spooky.kitduels.menusystem.PlayerMenuUtility;
import prod.spooky.kitduels.utils.Duel;

import java.util.List;
import java.util.Objects;

public class PlayerSelectMenu extends Menu {
    public PlayerSelectMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.YELLOW+"Online Players";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
            Player opponent = Bukkit.getPlayer(ChatColor.stripColor(Objects.requireNonNull(e.getCurrentItem()).getItemMeta().getDisplayName()));
            if(opponent != null) {
                if (Duel.playersInDuel.contains(opponent.getUniqueId())) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "This Player is Currently not Available for Duel");
                    return;
                }
                playerMenuUtility.setOpponent(opponent);
                KitSelectMenu menu = new KitSelectMenu(playerMenuUtility);
                menu.open();
            }
    }

    public void setMenuItems() {
        String name = playerMenuUtility.getOwner().getDisplayName();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            ItemStack playerHead = createPlayerHead(onlinePlayer.getName());
            if (!playerHead.getItemMeta().getDisplayName().equals(name)){
                inventory.addItem(playerHead);
            }
        }
        setFillerGlass();
    }

    private ItemStack createPlayerHead(String playerName) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = playerHead.getItemMeta();
        meta.setDisplayName(playerName);
        if (Duel.playersInDuel.contains(Objects.requireNonNull(Bukkit.getPlayer(playerName)).getUniqueId())){
            meta.setLore(List.of(new String[]{ChatColor.RED+"Not Available",ChatColor.RED+"Current in a Duel"}));
        }
        playerHead.setItemMeta(meta);
        return playerHead;
    }

}
