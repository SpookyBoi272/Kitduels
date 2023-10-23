package prod.spooky.kitduels.menusystem.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import prod.spooky.kitduels.Kitduels;
import prod.spooky.kitduels.menusystem.Menu;
import prod.spooky.kitduels.menusystem.PlayerMenuUtility;

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
        String invName = e.getView().getTitle();
            System.out.println(e.getCurrentItem().getItemMeta().getDisplayName());
            Player opponent = Bukkit.getPlayer(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
//            PlayerMenuUtility playerMenuUtility = Kitduels.getPlayerMenuUtility((Player) e.getWhoClicked());
            playerMenuUtility.setOpponent(opponent);

            KitSelectMenu menu = new KitSelectMenu(playerMenuUtility);
            menu.open();
    }

    @Override
    public void setMenuItems() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            ItemStack playerHead = createPlayerHead(onlinePlayer.getName());
            inventory.addItem(playerHead);
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
