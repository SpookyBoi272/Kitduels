package prod.spooky.kitduels.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.*;

public class NetheritePotKit {
    ItemStack armor1 = new ItemStack(Material.NETHERITE_HELMET);
    ItemStack armor2 = new ItemStack(Material.NETHERITE_CHESTPLATE);
    ItemStack armor3 = new ItemStack(Material.NETHERITE_LEGGINGS);
    ItemStack armor4 = new ItemStack(Material.NETHERITE_BOOTS);
    ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
    ItemStack instantHealth = new ItemStack(Material.POTION);
    ItemStack speedPot = new ItemStack(Material.POTION);
    ItemStack strengthPot = new ItemStack(Material.POTION);
    ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
    ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 64);
    ItemStack enchantBottle = new ItemStack(Material.EXPERIENCE_BOTTLE);

    public NetheritePotKit() {
        setPotionType(instantHealth, PotionEffectType.HEAL,100, 2 ,true );
        setPotionType(speedPot, PotionEffectType.SPEED, 1800, 2, true);
        setPotionType(strengthPot, PotionEffectType.INCREASE_DAMAGE, 1800 , 2, true);
        enchantArmour(armor1);
        enchantArmour(armor2);
        enchantArmour(armor3);
        enchantArmour(armor4);
        sword.addEnchantment(Enchantment.DAMAGE_ALL,5);
        sword.addEnchantment(Enchantment.SWEEPING_EDGE,3);
        sword.addEnchantment(Enchantment.MENDING,1);
        sword.addEnchantment(Enchantment.DURABILITY,3);
    }

    public void addItems(Player p){
        p.getInventory().setHelmet(armor1);
        p.getInventory().setChestplate(armor2);
        p.getInventory().setLeggings(armor3);
        p.getInventory().setBoots(armor4);
        p.getInventory().setItemInOffHand(totem);
        addPots(p);
        itemSet(p,0,sword);
        itemSet(p,1,apple);
    }

    public void itemSet(Player p, int location, ItemStack item){
        p.getInventory().setItem(location,item);
    }

    public void setPotionType(ItemStack potion,PotionEffectType type, int duration, int amplifier, boolean isSplash) {
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.addCustomEffect(new PotionEffect(type, duration, amplifier), isSplash);
        potion.setItemMeta(meta);
    }

    public void enchantArmour(ItemStack armour){
        armour.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,4);
        armour.addEnchantment(Enchantment.DURABILITY,4);
        armour.addEnchantment(Enchantment.MENDING,4);
    }

    public void addPots(Player p){
        itemSet(p, 19,enchantBottle);
        itemSet(p,28,enchantBottle);
        itemSet(p,8,totem);
        itemSet(p,3,instantHealth);
        itemSet(p,4,instantHealth);
        itemSet(p,5,instantHealth);
        itemSet(p,9,instantHealth);
        itemSet(p,10,instantHealth);
        itemSet(p,11,instantHealth);
        itemSet(p,12,instantHealth);
        itemSet(p,13,instantHealth);
        itemSet(p,18,instantHealth);
        itemSet(p,20,instantHealth);
        itemSet(p,21,instantHealth);
        itemSet(p,22,instantHealth);
        itemSet(p,27,instantHealth);
        itemSet(p,29,instantHealth);
        itemSet(p,30,instantHealth);
        itemSet(p,31,instantHealth);
        itemSet(p,7,speedPot);
        itemSet(p,14,speedPot);
        itemSet(p,17,speedPot);
        itemSet(p,23,speedPot);
        itemSet(p,26,speedPot);
        itemSet(p,32,speedPot);
        itemSet(p,35,speedPot);
        itemSet(p,6,strengthPot);
        itemSet(p,15,strengthPot);
        itemSet(p,16,strengthPot);
        itemSet(p,24,strengthPot);
        itemSet(p,25,strengthPot);
        itemSet(p,33,strengthPot);
        itemSet(p,34,strengthPot);

    }

}
