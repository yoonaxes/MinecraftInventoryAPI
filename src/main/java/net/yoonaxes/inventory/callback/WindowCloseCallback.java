package net.yoonaxes.inventory.callback;

import net.yoonaxes.inventory.gui.MenuHolder;
import org.bukkit.entity.HumanEntity;

@WindowCallback(type = WindowCallback.Type.CLOSE)
public interface WindowCloseCallback {

    void onWindowClose(HumanEntity humanEntity, MenuHolder holder);

}
