package net.yoonaxes.inventory.listener.impl;

import net.yoonaxes.inventory.gui.MenuHolder;
import net.yoonaxes.inventory.listener.ListenerHandler;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class InventoryInteractListener extends ListenerHandler<InventoryInteractEvent> {

    public InventoryInteractListener(Plugin plugin) {
        super(plugin);
    }

    @Override
    @EventHandler (priority = EventPriority.NORMAL)
    protected void onEvent(InventoryInteractEvent event) {
        Inventory inventory = event.getInventory();

        if(!(inventory.getHolder() instanceof MenuHolder))
            return;

        event.setResult(Event.Result.DENY);
        event.setCancelled(true);
    }
}
