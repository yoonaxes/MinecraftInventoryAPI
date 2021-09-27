package net.yoonaxes.inventory.listener.impl;

import net.yoonaxes.inventory.actions.ItemClickAction;
import net.yoonaxes.inventory.callback.ItemClickCallback;
import net.yoonaxes.inventory.gui.MenuHolder;
import net.yoonaxes.inventory.gui.MenuWindow;
import net.yoonaxes.inventory.listener.ListenerHandler;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.ClickType;
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
        Inventory inventory = event.getView().getTopInventory();
        Inventory clickedInventory = event.getClickedInventory();
        if(inventory == null || clickedInventory == null)
            return;

        if(!(inventory.getHolder() instanceof MenuHolder))
            return;

        MenuHolder holder = (MenuHolder) inventory.getHolder();
        MenuWindow window = holder.getWindow();

        ClickType click = event.getClick();
        if(window.isAllowPlayerInventoryClick()) {
            if(click.isShiftClick() || click.isKeyboardClick()) {
                event.setResult(Event.Result.DENY);
                event.setCancelled(true);
            } else {
                if(inventory.getType() == clickedInventory.getType()) {
                    event.setResult(Event.Result.DENY);
                    event.setCancelled(true);
                }
            }
        } else {
            event.setResult(Event.Result.DENY);
            event.setCancelled(true);
        }

        int slot = event.getRawSlot();
        ItemClickCallback callback = window.getItemClickCallbackMap().get(slot);
        if(callback == null)
            return;

        callback.onClick(event.getWhoClicked(),
                new ItemClickAction(
                        holder,
                        click,
                        slot,
                        event.getCursor()
                )
        );
    }
}
