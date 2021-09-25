package net.yoonaxes.inventory.gui;

import com.google.common.collect.Lists;
import net.yoonaxes.inventory.MinecraftInventoryAPI;
import net.yoonaxes.inventory.callback.WindowCallback;
import net.yoonaxes.inventory.callback.WindowCloseCallback;
import net.yoonaxes.inventory.callback.WindowOpenCallback;
import net.yoonaxes.translator.ColorTranslator;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MenuWindow {

    private static final ColorTranslator COLOR_TRANSLATOR = MinecraftInventoryAPI.getColorTranslator();

    private final MenuHolder holder;
    private final Inventory inventory;

    private final List<WindowCallback> windowCallbackList = Lists.newArrayList();

    public MenuWindow (String title, InventoryType type, int slots) {
        this.holder = new MenuHolder(this);

        if(type == InventoryType.CHEST)
            this.inventory = Bukkit.createInventory(
                    this.holder,
                    slots,
                    COLOR_TRANSLATOR.translateString(title)
            );
        else
            this.inventory = Bukkit.createInventory(
                    this.holder,
                    type,
                    COLOR_TRANSLATOR.translateString(title)
            );

        this.holder.setInventory(this.inventory);
    }

    public MenuWindow(String title, Rows rows) {
        this(title, InventoryType.CHEST, rows.getSize());
    }

    public MenuWindow(String title, InventoryType type) {
        this(title, InventoryType.CHEST, type.getDefaultSize());
    }

    public MenuWindow withWindowCallback(WindowOpenCallback windowCallback) {
        this.windowCallbackList.add(windowCallback);
        return this;
    }

    public MenuWindow withWindowCallback(WindowCloseCallback windowCallback) {
        this.windowCallbackList.add(windowCallback);
        return this;
    }

    public List<WindowCallback> getWindowCallbackList() {
        return windowCallbackList;
    }

    public MenuWindow withItem(ItemStack itemStack) {
        this.inventory.addItem(itemStack);
        return this;
    }

    public MenuWindow withItem(int slot, ItemStack itemStack) {
        this.inventory.setItem(slot, itemStack);
        return this;
    }

    /**
     * Open a inventory for entity.
     * @param humanEntity HumanEntity target
     */
    public void open(HumanEntity humanEntity) {
        humanEntity.openInventory(this.inventory);
    }
}
