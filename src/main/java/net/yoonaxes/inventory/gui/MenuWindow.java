package net.yoonaxes.inventory.gui;

import com.google.common.collect.Lists;
import net.yoonaxes.inventory.MinecraftInventoryAPI;
import net.yoonaxes.inventory.callback.WindowCallback;
import net.yoonaxes.inventory.callback.WindowCloseCallback;
import net.yoonaxes.inventory.callback.WindowOpenCallback;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class MenuWindow {

    private MenuHolder holder;
    private Inventory inventory;

    private final List<WindowCallback> windowCallbackList = Lists.newArrayList();

    public MenuWindow(String title, Rows rows) {
        this.holder = new MenuHolder(this);
        this.inventory = Bukkit.createInventory(
                this.holder,
                rows.getSize(),
                MinecraftInventoryAPI.getColorTranslator().translateString(title)
        );
        this.holder.setInventory(this.inventory);
    }

    public MenuWindow(String title, int size) {
        this(
                title,
                Rows.getRowBySize(size)
        );
    }

    public MenuWindow withWindowCallback(WindowCallback windowCallback) {
        if(windowCallback instanceof WindowOpenCallback
                || windowCallback instanceof WindowCloseCallback)
            this.windowCallbackList.add(windowCallback);

        return this;
    }

    public List<WindowCallback> getWindowCallbackList() {
        return windowCallbackList;
    }

    /**
     * Open a inventory for entity.
     * @param humanEntity HumanEntity target
     */
    public void open(HumanEntity humanEntity) {
        humanEntity.openInventory(this.inventory);
    }
}
