package prod.spooky.kitduels.menusystem.menus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import prod.spooky.kitduels.menusystem.Menu;
import prod.spooky.kitduels.menusystem.PlayerMenuUtility;

public class KitSelectMenu extends Menu {
    public KitSelectMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.YELLOW+"Kit Selection";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        String kit = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
//        PlayerMenuUtility playerMenuUtility = Kitduels.getPlayerMenuUtility((Player) e.getWhoClicked());
        playerMenuUtility.setKit(kit);

        MapSelectMenu menu = new MapSelectMenu(playerMenuUtility);
        menu.open();
    }

    @Override
    public void setMenuItems() {
        addItemToGUI(inventory, Material.DIAMOND_SWORD, "Sword", ChatColor.LIGHT_PURPLE);
        addItemToGUI(inventory, Material.SHIELD, "Shield", ChatColor.LIGHT_PURPLE);
        addItemToGUI(inventory, Material.ENCHANTED_GOLDEN_APPLE, "Buff", ChatColor.LIGHT_PURPLE);
        setFillerGlass();
    }

    private void addItemToGUI(Inventory gui, Material material, String name, ChatColor color) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color + name);
        item.setItemMeta(meta);
        gui.addItem(item);
    }

}
