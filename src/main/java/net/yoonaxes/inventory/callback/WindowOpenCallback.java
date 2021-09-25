package net.yoonaxes.inventory.callback;

import net.yoonaxes.inventory.gui.MenuHolder;
import org.bukkit.entity.HumanEntity;

@WindowCallback(type = WindowCallback.Type.OPEN)
public interface WindowOpenCallback {

    void onWindowOpen(HumanEntity humanEntity, MenuHolder holder);

}
