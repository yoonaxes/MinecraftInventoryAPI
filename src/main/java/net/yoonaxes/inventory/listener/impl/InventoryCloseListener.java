package net.yoonaxes.inventory.listener.impl;

import net.yoonaxes.inventory.actions.WindowAction;
import net.yoonaxes.inventory.callback.WindowCloseCallback;
import net.yoonaxes.inventory.gui.MenuHolder;
import net.yoonaxes.inventory.gui.MenuWindow;
import net.yoonaxes.inventory.listener.ListenerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class InventoryCloseListener extends ListenerHandler<InventoryCloseEvent> {

    public InventoryCloseListener(Plugin plugin) {
        super(plugin);
    }


    @Override
    @EventHandler(priority = EventPriority.NORMAL)
    protected void onEvent(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();

        if(!(inventory.getHolder() instanceof MenuHolder))
            return;

        MenuHolder holder = (MenuHolder) inventory.getHolder();
        MenuWindow window = holder.getWindow();

        window.getWindowCallbackList().stream()
                .filter(windowCallback -> windowCallback instanceof WindowCloseCallback)
                .forEach(windowCallback ->
                        ((WindowCloseCallback) windowCallback).onWindowClose(
                                event.getPlayer(),
                                new WindowAction(holder, WindowAction.WindowActionType.CLOSE))
                );
    }
}
