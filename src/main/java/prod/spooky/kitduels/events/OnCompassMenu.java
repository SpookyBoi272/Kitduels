package prod.spooky.kitduels.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import prod.spooky.kitduels.utils.Hubitems;

import java.util.List;

public class OnCompassMenu implements Listener {
    @EventHandler
    public void onCompassRightClick(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getType() == Material.COMPASS) {
            Hubitems hub = new Hubitems();
            ItemMeta checkMeta = hub.getCompassmeta();
            if (event.getAction().name().contains("RIGHT") /*&& event.getItem().getItemMeta() == checkMeta*/) {
                openMainMenu(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if (item != null && item.getType() == Material.DIAMOND_SWORD && item.hasItemMeta()
                /*&& item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW+"Duels")*/) {
            openPlayerListInventory(player);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();
        String invName = clickedInventory. getViewers(). get(0). getOpenInventory(). getTitle();

        if (clickedInventory != null && invName.equals("Online Players")) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() == Material.PLAYER_HEAD) {
                String playerName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

                // Execute the /duel command with the selected player's name
                Bukkit.dispatchCommand(player, "duel " + playerName);
            }
        }
    }

    private void openPlayerListInventory(Player player) {
        Inventory playerListInventory = Bukkit.createInventory(player, 27, "Online Players");

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            ItemStack playerHead = createPlayerHead(onlinePlayer.getName());
            playerListInventory.addItem(playerHead);
        }

        Inventory fixedPlayerListInventory = removePlayerHead(playerListInventory, player.getName());
        player.openInventory(fixedPlayerListInventory);
    }

    private ItemStack createPlayerHead(String playerName) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = playerHead.getItemMeta();
        meta.setDisplayName(playerName);
        playerHead.setItemMeta(meta);
        return playerHead;
    }

    private void openMainMenu(Player player) {
        Inventory mainMenu = player.getServer().createInventory(null, 27, ChatColor.YELLOW + "Games");
        mainMenu.setItem(0,getDuelsIcon());

        player.openInventory(mainMenu);
    }

    private ItemStack getDuelsIcon(){
        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordMeta = diamondSword.getItemMeta();
        swordMeta.setLore(List.of(new String[]{ChatColor.YELLOW+"Join Duels"}));
        swordMeta.setDisplayName(ChatColor.RED + "Duels");
        diamondSword.setItemMeta(swordMeta);
        return diamondSword;
    }

    public Inventory removePlayerHead(Inventory inventory, String headOwner) {


        for (int slot = 0; slot < inventory.getSize(); slot++) {
            ItemStack item = inventory.getItem(slot);

            if (item != null && item.getType() == Material.PLAYER_HEAD) {
                // Check if the item has the correct owner (player's name or UUID)
                String itemOwner = getPlayerHeadOwner(item);

                if (itemOwner != null && itemOwner.equalsIgnoreCase(headOwner)) {
                    // Remove the player head from the inventory
                    inventory.setItem(slot, new ItemStack(Material.AIR));
                }
            }
        }
        return inventory;
    }

    private String getPlayerHeadOwner(ItemStack playerHead) {
        if (playerHead != null && playerHead.getType() == Material.PLAYER_HEAD) {
            if (playerHead.hasItemMeta() && playerHead.getItemMeta() instanceof SkullMeta skullMeta) {

                if (skullMeta.hasOwner()) {
                    return skullMeta.getOwningPlayer().getName();
                }
            }
        }
        return null;
    }
}

