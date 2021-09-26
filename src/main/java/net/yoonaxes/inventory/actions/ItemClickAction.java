package net.yoonaxes.inventory.actions;

import net.yoonaxes.inventory.gui.MenuHolder;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ItemClickAction {

    private final MenuHolder menuHolder;
    private final ClickType clickType;
    private final int slot;
    private final ItemStack cursor;

    public ItemClickAction(MenuHolder holder, ClickType clickType, int slot, ItemStack cursor) {
        this.menuHolder = holder;
        this.clickType = clickType;
        this.slot = slot;
        this.cursor = cursor;
    }

    public MenuHolder getMenuHolder() {
        return menuHolder;
    }

    public ClickType getClickType() {
        return clickType;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getCursor() {
        return cursor;
    }
}
