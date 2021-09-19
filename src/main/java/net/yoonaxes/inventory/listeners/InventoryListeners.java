package net.yoonaxes.inventory.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryListeners implements Listener {

    @EventHandler (priority = EventPriority.NORMAL)
    public void onInventoryOpen(InventoryOpenEvent event) {

    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void onInventoryClose(InventoryCloseEvent event) {

    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void onInventoryClick(InventoryClickEvent event) {

    }
}
