package net.yoonaxes.inventory.callback;

import net.yoonaxes.inventory.actions.WindowAction;
import org.bukkit.entity.HumanEntity;

public interface WindowCloseCallback extends WindowCallback {

    void onWindowClose(HumanEntity humanEntity, WindowAction action);

}
