package net.yoonaxes.inventory.gui;

import net.yoonaxes.inventory.MinecraftInventoryAPI;
import net.yoonaxes.inventory.callback.ItemClickCallback;
import net.yoonaxes.inventory.callback.WindowCallback;
import net.yoonaxes.inventory.callback.WindowCloseCallback;
import net.yoonaxes.inventory.callback.WindowOpenCallback;
import net.yoonaxes.translator.ColorTranslator;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuWindow {

    private static final ColorTranslator COLOR_TRANSLATOR = MinecraftInventoryAPI.getColorTranslator();

    private final MenuHolder holder;
    private final Inventory inventory;

    private final List<WindowCallback> windowCallbackList = new ArrayList<>();
    private final HashMap<Integer, ItemClickCallback> itemClickCallbackMap = new HashMap<>();

    public MenuWindow (String title, int size) {
        this.holder = new MenuHolder(this);
        this.inventory = this.createInventory(title, size);
        this.holder.setInventory(this.inventory);
    }

    public MenuWindow (String title, Rows rows) {
        this (title, rows.getSize());
    }

    private Inventory createInventory(String title, int size) {
        if(size == 5)
            return Bukkit.createInventory(this.holder, InventoryType.HOPPER, COLOR_TRANSLATOR.translateString(title));

        return Bukkit.createInventory(this.holder, size, COLOR_TRANSLATOR.translateString(title));
    }

    public MenuWindow withWindowCallback(WindowOpenCallback windowCallback) {
        this.windowCallbackList.add(windowCallback);
        return this;
    }

    public MenuWindow withWindowCallback(WindowCloseCallback windowCallback) {
        this.windowCallbackList.add(windowCallback);
        return this;
    }

    public List<WindowCallback> getWindowCallbackList() {
        return windowCallbackList;
    }

    public HashMap<Integer, ItemClickCallback> getItemClickCallbackMap() {
        return itemClickCallbackMap;
    }

    public MenuWindow withItem(ItemStack itemStack) {
        return this.withItem(this.findFirstFreeSlot(), itemStack);
    }

    public MenuWindow withItem(ItemStack itemStack, ItemClickCallback itemClickCallback) {
        return this.withItem(this.findFirstFreeSlot(), itemStack, itemClickCallback);
    }

    public MenuWindow withItem(int slot, ItemStack itemStack) {
        if(slot >= 0 && this.inventory.getSize() >= slot)
            this.inventory.setItem(slot, itemStack);
        return this;
    }

    public MenuWindow withItem(int slot, ItemStack itemStack, ItemClickCallback itemClickCallback) {
        if(slot >= 0 && this.inventory.getSize() >= slot)
            this.itemClickCallbackMap.put(slot, itemClickCallback);
        return this.withItem(slot, itemStack);
    }

    public MenuWindow fillEmptySlots(int start, int end, ItemStack itemStack) {
        if(end > this.inventory.getSize())
            end = this.inventory.getSize();

        for(int i = start; i < end; ++i)
            if(this.inventory.getItem(i) == null)
                this.inventory.setItem(i, itemStack);

        return this;
    }

    public MenuWindow fillEmptySlots(int start, ItemStack itemStack) {
        return this.fillEmptySlots(start, this.inventory.getSize(), itemStack);
    }

    public MenuWindow fillEmptySlots(ItemStack itemStack) {
        return this.fillEmptySlots(0, this.inventory.getSize(), itemStack);
    }

    /**
     * Open a inventory for entity.
     * @param humanEntity HumanEntity target
     */
    public void open(HumanEntity humanEntity) {
        humanEntity.openInventory(this.inventory);
    }

    /**
     * Private method for find first free slot in inventory.
     * @return First free slot number
     */
    private int findFirstFreeSlot() {
        for(int i = 0; i < this.inventory.getSize(); ++i)
            if(this.inventory.getItem(i) == null)
                return i;
        return -1;
    }
}
