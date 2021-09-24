package net.yoonaxes.inventory.builders;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.yoonaxes.inventory.MinecraftInventoryAPI;
import net.yoonaxes.translator.ColorTranslator;
import net.yoonaxes.translator.impl.DefaultColorTranslator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Quick and convenient wizard for Bukkit ItemStack.
 * @author yoonaxes
 */
public class ItemBuilder {

    private static final MinecraftInventoryAPI MINECRAFT_INVENTORY_API = MinecraftInventoryAPI.INSTANCE;

    private static final ColorTranslator COLOR_TRANSLATOR = MINECRAFT_INVENTORY_API == null
            ? DefaultColorTranslator.DEFAULT_COLOR_TRANSLATOR
            : MINECRAFT_INVENTORY_API.getColorTranslator();

    private final Material material;
    private final int amount;
    private short durability;

    private String displayName;
    private final List<String> loreList = Lists.newArrayList();
    private final Map<Enchantment, Integer> enchantmentMap = Maps.newHashMap();

    private boolean glowing = false;

    public ItemBuilder(Material material, int amount, short durability) {
        this.material = material;
        this.amount = amount;
        this.durability = durability;
    }

    public ItemBuilder(Material material, int amount) {
        this(material, amount, (short) 0);
    }

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    public ItemBuilder withDurability(short durability) {
        this.durability = durability;
        return this;
    }

    public ItemBuilder withName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemBuilder withLore(String... lores) {
        this.loreList.addAll(Arrays.asList(lores));
        return this;
    }

    public ItemBuilder withEnchantment(Enchantment enchantment, int level) {
        this.enchantmentMap.put(enchantment, level);
        return this;
    }

    public ItemBuilder withGlow() {
        this.glowing = true;
        return this;
    }

    /**
     * Build a ItemStack.
     * @return Created ItemStack
     */
    public ItemStack build() {
        // Create ItemStack and get ItemMeta.
        ItemStack itemStack = new ItemStack(this.material, this.amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        // Set Durability
        if(this.durability != (short) 0)
            itemStack.setDurability(this.durability);

        // Set Display Name
        if(this.displayName != null)
            itemMeta.setDisplayName(COLOR_TRANSLATOR.translateString(this.displayName));

        // Set Lore
        itemMeta.setLore(
                COLOR_TRANSLATOR.translateList(this.loreList)
        );

        // Add Enchantments
        this.enchantmentMap.forEach((enchantment, level) ->
                itemMeta.addEnchant(enchantment, level, true)
        );

        // Add glow to item
        if(this.glowing) {
            itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 0, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        // Set ItemMeta to return ItemStack.
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
