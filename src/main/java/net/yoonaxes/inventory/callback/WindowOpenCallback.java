package net.yoonaxes.inventory.callback;

import net.yoonaxes.inventory.gui.MenuHolder;
import org.bukkit.entity.HumanEntity;

public interface WindowOpenCallback extends WindowCallback {

    void onWindowOpen(HumanEntity humanEntity, MenuHolder holder);

}
