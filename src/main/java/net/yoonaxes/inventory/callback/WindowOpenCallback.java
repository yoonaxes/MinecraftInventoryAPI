package net.yoonaxes.inventory.callback;

import net.yoonaxes.inventory.actions.WindowAction;
import org.bukkit.entity.HumanEntity;

public interface WindowOpenCallback extends WindowCallback {

    void onWindowOpen(HumanEntity humanEntity, WindowAction action);

}
