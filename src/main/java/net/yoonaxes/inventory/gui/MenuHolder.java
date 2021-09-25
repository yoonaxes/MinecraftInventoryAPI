package net.yoonaxes.inventory.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MenuHolder implements InventoryHolder {

    private MenuWindow window;
    private Inventory inventory;

    public MenuHolder(MenuWindow window) {
        this.window = window;
    }

    public MenuWindow getWindow() {
        return window;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
