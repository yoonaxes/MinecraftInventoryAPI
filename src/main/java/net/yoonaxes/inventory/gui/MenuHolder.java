package net.yoonaxes.inventory.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Basic InventoryHolder class for MinecraftInventoryAPI.
 * @author yoonaxes
 */
public class MenuHolder implements InventoryHolder {

    private final MenuWindow window;
    private Inventory inventory;

    /**
     * Create a instance of MenuHolder.
     * @param window MenuWindow
     */
    public MenuHolder(MenuWindow window) {
        this.window = window;
    }

    /**
     * Get a Window for InventoryHolder.
     * @return MenuWindow
     */
    public MenuWindow getWindow() {
        return window;
    }

    /**
     * Set a inventory for InventoryHolder.
     * @param inventory Bukkit Inventory
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Get a inventory for InventoryHolder.
     * @return Bukkit Inventory
     */
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
