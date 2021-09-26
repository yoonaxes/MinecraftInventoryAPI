package net.yoonaxes.inventory.listener.impl;

import net.yoonaxes.inventory.actions.ItemClickAction;
import net.yoonaxes.inventory.callback.ItemClickCallback;
import net.yoonaxes.inventory.gui.MenuHolder;
import net.yoonaxes.inventory.gui.MenuWindow;
import net.yoonaxes.inventory.listener.ListenerHandler;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class InventoryClickListener extends ListenerHandler<InventoryClickEvent> {

    public InventoryClickListener(Plugin plugin) {
        super(plugin);
    }

    @Override
    @EventHandler(priority = EventPriority.NORMAL)
    protected void onEvent(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        if(!(inventory.getHolder() instanceof MenuHolder))
            return;

        MenuHolder holder = (MenuHolder) inventory.getHolder();
        MenuWindow window = holder.getWindow();

        event.setResult(Event.Result.DENY);
        event.setCancelled(true);

        ItemClickCallback callback = window.getItemClickCallbackMap().get(event.getRawSlot());
        if(callback == null)
            return;

        callback.onClick(event.getWhoClicked(),
                new ItemClickAction(
                        holder,
                        event.getClick(),
                        event.getRawSlot(),
                        event.getCursor()
                )
        );
    }
}
