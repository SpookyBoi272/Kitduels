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
                playerMenuUtility.setOpponent(opponent);
            }
            KitSelectMenu menu = new KitSelectMenu(playerMenuUtility);
            menu.open();
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
        playerHead.setItemMeta(meta);
        return playerHead;
    }

}
