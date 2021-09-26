package net.yoonaxes.inventory.builders;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.yoonaxes.inventory.MinecraftInventoryAPI;
import net.yoonaxes.translator.ColorTranslator;
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

    private static final ColorTranslator COLOR_TRANSLATOR = MinecraftInventoryAPI.getColorTranslator();

    private final Material material;
    private final int amount;
    private short durability;

    private String displayName;
    private final List<String> loreList = Lists.newArrayList();
    private final List<ItemFlag> itemFlagList = Lists.newArrayList();
    private final Map<Enchantment, Integer> enchantmentMap = Maps.newHashMap();

    /**
     * Create a instance of ItemBuilder.
     * @param material Bukkit Material
     * @param amount amount
     * @param durability damage
     */
    public ItemBuilder(Material material, int amount, short durability) {
        this.material = material;
        this.amount = amount;
        this.durability = durability;
    }

    /**
     * Create a instance of ItemBuilder.
     * @param material Bukkit Material
     * @param amount amount
     */
    public ItemBuilder(Material material, int amount) {
        this(material, amount, (short) 0);
    }

    /**
     * Create a instance of ItemBuilder.
     * @param material Bukkit Material
     */
    public ItemBuilder(Material material) {
        this(material, 1);
    }

    /**
     * Set durability to item.
     * @param durability short damage
     * @return ItemBuilder
     */
    public ItemBuilder withDurability(short durability) {
        this.durability = durability;
        return this;
    }

    /**
     * Set display name of item.
     * @param displayName Display Name Stirng
     * @return ItemBuilder
     */
    public ItemBuilder withName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Add lore to item.
     * @param lores String lore array
     * @return ItemBuilder
     */
    public ItemBuilder withLore(String... lores) {
        this.loreList.addAll(Arrays.asList(lores));
        return this;
    }

    /**
     * Add ItemFlag to item.
     * @param itemFlags Bukkit ItemFlag array
     * @return ItemBuilder
     */
    public ItemBuilder withItemFlag(ItemFlag... itemFlags) {
        this.itemFlagList.addAll(Arrays.asList(itemFlags));
        return this;
    }

    /**
     * Add enchantment to item.
     * @param enchantment Bukkit Enchantment
     * @param level Level of enchantment
     * @return ItemBuilder
     */
    public ItemBuilder withEnchantment(Enchantment enchantment, int level) {
        this.enchantmentMap.put(enchantment, level);
        return this;
    }

    /**
     * Add Glow to item.
     * Adds ItemFlag.HIDE_ENCHANTS.
     * Adds Enchantment.ARROW_INFINITE level 0.
     * @return ItemBuilder
     */
    public ItemBuilder withGlow() {
        this.itemFlagList.add(ItemFlag.HIDE_ENCHANTS);
        this.enchantmentMap.put(Enchantment.ARROW_INFINITE, 0);
        return this;
    }

    /**
     * Adds Attributes to item:
     * - HIDE_ATTRIBUTES
     * - HIDE_UNBREAKABLE
     * - HIDE_DYE
     * @return ItemBuilder
     */
    public ItemBuilder withHiddenAttributes() {
        this.itemFlagList.addAll(Arrays.asList(
                ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_DYE
        ));
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
        if(this.loreList.size() > 0)
            itemMeta.setLore(COLOR_TRANSLATOR.translateList(this.loreList));

        // Add ItemFlags
        this.itemFlagList.forEach(itemMeta::addItemFlags);

        // Add Enchantments
        this.enchantmentMap.forEach((enchantment, level) ->
                itemMeta.addEnchant(enchantment, level, true)
        );

        // Set ItemMeta for ItemStack.
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
