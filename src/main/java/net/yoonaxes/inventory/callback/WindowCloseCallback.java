package net.yoonaxes.inventory.callback;

import net.yoonaxes.inventory.gui.MenuHolder;
import org.bukkit.entity.HumanEntity;

public interface WindowCloseCallback extends WindowCallback {

    void onWindowClose(HumanEntity humanEntity, MenuHolder holder);

}
