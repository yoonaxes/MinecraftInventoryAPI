package net.yoonaxes.inventory.callback;

import net.yoonaxes.inventory.actions.ItemClickAction;
import org.bukkit.entity.HumanEntity;

public interface ItemClickCallback {

    void onClick(HumanEntity humanEntity, ItemClickAction action);

}
