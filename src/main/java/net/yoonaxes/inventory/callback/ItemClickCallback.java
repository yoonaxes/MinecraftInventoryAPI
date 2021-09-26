package net.yoonaxes.inventory.callback;

import net.yoonaxes.inventory.gui.MenuHolder;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;

public interface ItemClickCallback {

    void onClick(HumanEntity humanEntity, ClickType clickType, MenuHolder menuHolder);

}
