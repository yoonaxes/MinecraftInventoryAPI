package net.yoonaxes.inventory.listener;

import net.yoonaxes.inventory.callback.WindowCallback;
import net.yoonaxes.inventory.callback.WindowCloseCallback;
import net.yoonaxes.inventory.callback.WindowOpenCallback;
import net.yoonaxes.inventory.gui.MenuHolder;
import net.yoonaxes.inventory.gui.MenuWindow;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListeners implements Listener {

    @EventHandler (priority = EventPriority.NORMAL)
    public void onInventoryOpen(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();

        if(!(inventory.getHolder() instanceof MenuHolder))
            return;

        MenuHolder holder = (MenuHolder) inventory.getHolder();
        if(holder == null)
            return;

        MenuWindow window = holder.getWindow();

        window.getWindowCallbackList().forEach(windowCallback -> {
            if(windowCallback.type() == WindowCallback.Type.OPEN) {
                if(windowCallback instanceof WindowOpenCallback) {
                    ((WindowOpenCallback) windowCallback).onWindowOpen(event.getPlayer(), holder);
                }
            }
        });
    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();

        if(!(inventory.getHolder() instanceof MenuHolder))
            return;

        MenuHolder holder = (MenuHolder) inventory.getHolder();
        if(holder == null)
            return;

        MenuWindow window = holder.getWindow();

        window.getWindowCallbackList().forEach(windowCallback -> {
            if(windowCallback.type() == WindowCallback.Type.CLOSE) {
                if(windowCallback instanceof WindowCloseCallback) {
                    ((WindowCloseCallback) windowCallback).onWindowClose(event.getPlayer(), holder);
                }
            }
        });
    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        if(!(inventory.getHolder() instanceof MenuHolder))
            return;

        MenuHolder holder = (MenuHolder) inventory.getHolder();
        if(holder == null)
            return;

        event.setResult(Event.Result.DENY);
        event.setCancelled(true);
    }
}
