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
import prod.spooky.kitduels.commands.DuelCommand;
import prod.spooky.kitduels.utils.DuelRequest;
import prod.spooky.kitduels.utils.Hubitems;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OnCompassMenu implements Listener {
    public String map;
    public String kit;
    public Player receiver;

    @EventHandler
    public void onCompassRightClick(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getType() == Material.COMPASS) {
            Hubitems hub = new Hubitems();
            if (event.getAction().name().contains("RIGHT") && event.getItem().hasItemMeta()) {
                openMainMenu(event.getPlayer());
            }
        }
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

    @EventHandler
    public void openPlayerList(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.DIAMOND_SWORD && event.getCurrentItem().hasItemMeta()
                && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED+"Duels")) {
            openPlayerListInventory(player);
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

    public Inventory removePlayerHead(Inventory inventory, String headOwner) {

        for (int slot = 0; slot < inventory.getSize(); slot++) {
            ItemStack item = inventory.getItem(slot);

            if (item != null && item.getType() == Material.PLAYER_HEAD) {
                // Check if the item has the correct owner (player's name or UUID)
                String itemOwner = item.getItemMeta().getDisplayName();

                if (itemOwner != null && itemOwner.equalsIgnoreCase(headOwner)) {
                    // Remove the player head from the inventory
                    inventory.setItem(slot, new ItemStack(Material.AIR));
                }
            }
        }
        return inventory;
    }

    @EventHandler
    public void openKitList(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player) && event.getCurrentItem() == null && event.getClickedInventory()== null) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        String invName = event.getView().getTitle();

        if (invName.equals("Online Players")) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() == Material.PLAYER_HEAD) {
                String receiverName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

                Inventory gui = Bukkit.createInventory(player, 9, ChatColor.RED + "Kit Selection");
                // Add items for each kit
                addItemToGUI(gui, Material.DIAMOND_SWORD, "Sword", ChatColor.LIGHT_PURPLE);
                addItemToGUI(gui, Material.SHIELD, "Shield", ChatColor.LIGHT_PURPLE);
                addItemToGUI(gui, Material.ENCHANTED_GOLDEN_APPLE, "Buff", ChatColor.LIGHT_PURPLE);
//                addItemToGUI(gui, Material.LAVA_BUCKET, "BuildUHC", ChatColor.LIGHT_PURPLE);
                player.openInventory(gui);
                receiver = Bukkit.getPlayer(receiverName);
            }
        }
    }

    private void addItemToGUI(Inventory gui, Material material, String name, ChatColor color) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color + name);
        item.setItemMeta(meta);
        gui.addItem(item);
    }

    @EventHandler
    public void openMapList(InventoryClickEvent e){
        if (!(e.getWhoClicked() instanceof Player) && e.getCurrentItem() == null && e.getClickedInventory()== null) {
            return;
        }
        Player player = (Player) e.getWhoClicked();
        String invName = e.getView().getTitle();
        if (invName.equals(ChatColor.RED + "Kit Selection")){
            e.setCancelled(true);
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem!=null && Objects.equals(clickedItem.getItemMeta().displayName().toString(), ChatColor.LIGHT_PURPLE + "Sword")){
                kit = "Sword";
                createMapGui(player);
            } else if (clickedItem!=null && Objects.equals(clickedItem.getItemMeta().displayName().toString(), ChatColor.LIGHT_PURPLE + "Shield")){
                createMapGui(player);
                kit = "Shield";
            } else if (clickedItem!=null && Objects.equals(clickedItem.getItemMeta().displayName().toString(), ChatColor.LIGHT_PURPLE + "Buff")){
                createMapGui(player);
                kit = "Buff";
            }
        }
    }

    private void createMapGui(Player player){
        Inventory gui = Bukkit.createInventory(player, 9, ChatColor.RED + "Map Selection");
        // Add items for each map
        addItemToGUI(gui, Material.MOSSY_STONE_BRICKS, "Museum", ChatColor.LIGHT_PURPLE);
        addItemToGUI(gui, Material.ORANGE_WOOL, "Arena", ChatColor.GOLD);
        addItemToGUI(gui, Material.SEA_LANTERN, "SkyPort", ChatColor.AQUA);
        addItemToGUI(gui, Material.OAK_LEAVES, "Highset", ChatColor.GREEN);
        player.openInventory(gui);
    }

    @EventHandler
    public void checkMap(InventoryClickEvent event){
        if (!(event.getWhoClicked() instanceof Player) && event.getCurrentItem() == null && event.getClickedInventory()== null) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        String invName = event.getView().getTitle();
        if (invName.equals(ChatColor.RED + "Map Selection")){
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem!=null && Objects.equals(clickedItem.getItemMeta().displayName().toString(), ChatColor.LIGHT_PURPLE + "Museum")){
                map = "Museum";
            } else if (clickedItem!=null && Objects.equals(clickedItem.getItemMeta().displayName().toString(), ChatColor.GOLD + "Arena")){
                map = "Arena";
            }else if (clickedItem!=null && Objects.equals(clickedItem.getItemMeta().displayName().toString(), ChatColor.AQUA + "Highset")){
                map = "Highset";
            }else if (clickedItem!=null && Objects.equals(clickedItem.getItemMeta().displayName().toString(), ChatColor.AQUA + "Fractal")){
                map = "Fractal";
            }
            sendDuelRequest(player,receiver);
        }
    }

    public void sendDuelRequest(Player sender, Player receiver) {
        UUID senderUUID = sender.getUniqueId();
        UUID receiverUUID = receiver.getUniqueId();

        DuelRequest request = new DuelRequest(senderUUID, receiverUUID, kit, map);
        DuelCommand.pendingRequests.put(receiverUUID, request);

        // Send a message to the receiver indicating the duel request
        receiver.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+sender.getName() + " has challenged you to a duel.");
        receiver.sendMessage("Type "+ChatColor.GREEN+"/accept"+ChatColor.WHITE+" or "+ChatColor.RED+"/decline "+ChatColor.WHITE+"to respond.");
        sender.sendMessage(ChatColor.RED+"[KitDuels] "+ChatColor.WHITE+"Sent Duel request to "+receiver.getName());
    }

}
