package net.yoonaxes.inventory.gui;

import net.yoonaxes.inventory.MinecraftInventoryAPI;
import net.yoonaxes.inventory.builders.ItemBuilder;
import net.yoonaxes.inventory.callback.*;
import net.yoonaxes.translator.ColorTranslator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Basic class to create interactive Inventory using MinecraftInventoryAPI.
 * @author yoonaxes
 */
public class MenuWindow {

    private static final ColorTranslator COLOR_TRANSLATOR = MinecraftInventoryAPI.getColorTranslator();
    private static final ItemStack DEFAULT_FILL_EMPTY_ITEMSTACK = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).withName("&8#").build();

    private final MenuHolder holder;
    private final Inventory inventory;

    private final List<WindowCallback> windowCallbackList = new ArrayList<>();
    private final HashMap<Integer, ItemClickCallback> itemClickCallbackMap = new HashMap<>();

    /**
     * Create a instance of MenuWindow.
     * @param title Inventory title
     * @param size Inventory size
     */
    public MenuWindow (String title, int size) {
        this.holder = new MenuHolder(this);
        this.inventory = this.createInventory(title, size);
        this.holder.setInventory(this.inventory);
    }

    /**
     * Create a instance of MenuWindow.
     * @param title Inventory title
     * @param rows Inventory rows (Rows Enum)
     */
    public MenuWindow (String title, Rows rows) {
        this (title, rows.getSize());
    }

    /**
     * Add a callback to window.
     * @param windowCallback WindowOpenCallback or WindowCloseCallback
     * @return MenuWindow
     */
    public MenuWindow withWindowCallback(WindowOpenCallback windowCallback) {
        this.windowCallbackList.add(windowCallback);
        return this;
    }

    /**
     * Add a callback to window.
     * @param windowCallback WindowOpenCallback or WindowCloseCallback
     * @return MenuWindow
     */
    public MenuWindow withWindowCallback(WindowCloseCallback windowCallback) {
        this.windowCallbackList.add(windowCallback);
        return this;
    }

    /**
     * Get a callback list of window.
     * @return WindowCallback List
     */
    public List<WindowCallback> getWindowCallbackList() {
        return windowCallbackList;
    }

    /**
     * Get a slot with ItemClickCallback map of window.
     * @return Slot / ItemClickCallback - Map
     */
    public Map<Integer, ItemClickCallback> getItemClickCallbackMap() {
        return itemClickCallbackMap;
    }

    /**
     * Add a ItemStack to inventory.
     * @param slot slot
     * @param itemStack ItemStack
     * @return MenuWindow
     */
    public MenuWindow withItem(int slot, ItemStack itemStack) {
        if(slot >= 0 && this.inventory.getSize() > slot)
            this.inventory.setItem(slot, itemStack);
        return this;
    }

    /**
     * Add a ItemStack to inventory.
     * @param itemStack ItemStack
     * @return MenuWindow
     */
    public MenuWindow withItem(ItemStack itemStack) {
        return this.withItem(this.findFirstFreeSlot(), itemStack);
    }

    /**
     * Add a ItemStack to inventory with ItemClickCallback action.
     * @param slot slot
     * @param itemStack ItemStack
     * @param itemClickCallback ItemClickCallback
     * @return MenuWindow
     */
    public MenuWindow withItem(int slot, ItemStack itemStack, ItemClickCallback itemClickCallback) {
        if(slot >= 0 && this.inventory.getSize() > slot)
            this.itemClickCallbackMap.put(slot, itemClickCallback);
        return this.withItem(slot, itemStack);
    }

    /**
     * Add a ItemStack to inventory with ItemClickCallback action.
     * @param itemStack ItemStack
     * @param itemClickCallback ItemClickCallback
     * @return MenuWindow
     */
    public MenuWindow withItem(ItemStack itemStack, ItemClickCallback itemClickCallback) {
        return this.withItem(this.findFirstFreeSlot(), itemStack, itemClickCallback);
    }

    /**
     * Add a ItemClickCallback action to slot.
     * @param slot slot
     * @param itemClickCallback ItemClickCallback
     * @return MenuWindow
     */
    public MenuWindow withItemAction(int slot, ItemClickCallback itemClickCallback) {
        if(slot >= 0 && this.inventory.getSize() > slot)
            this.itemClickCallbackMap.put(slot, itemClickCallback);
        return this;
    }

    /**
     * Add a ItemClickCallback action to item.
     * @param itemStack Item
     * @param itemClickCallback ItemClickCallback
     * @return MenuWindow
     */
    public MenuWindow withItemAction(ItemStack itemStack, ItemClickCallback itemClickCallback) {
        return this.withItemAction(this.findItemSlot(itemStack), itemClickCallback);
    }

    /**
     * Fill a inventory empty slots with ItemStack.
     * @param start Filling start slot
     * @param end Filling end slot
     * @param itemStack Filling ItemStack
     * @return MenuWindow
     */
    public MenuWindow fillEmptySlots(int start, int end, ItemStack itemStack) {
        if(start > this.inventory.getSize())
            start = 0;

        if(end > this.inventory.getSize())
            end = this.inventory.getSize();

        for(int i = start; i < end; ++i)
            if(this.inventory.getItem(i) == null)
                this.inventory.setItem(i, itemStack);

        return this;
    }

    /**
     * Fill a inventory empty slots with ItemStack.
     * @param start Filling start slot
     * @param itemStack Filling ItemStack
     * @return MenuWindow
     */
    public MenuWindow fillEmptySlots(int start, ItemStack itemStack) {
        return this.fillEmptySlots(start, this.inventory.getSize(), itemStack);
    }

    /**
     * Fill all inventory empty slots with ItemStack.
     * @param itemStack Filling ItemStack
     * @return MenuWindow
     */
    public MenuWindow fillEmptySlots(ItemStack itemStack) {
        return this.fillEmptySlots(0, itemStack);
    }

    /**
     * Fill all inventory empty slots with default ItemStack.
     * @return MenuWindow
     */
    public MenuWindow fillEmptySlots() {
        return this.fillEmptySlots(DEFAULT_FILL_EMPTY_ITEMSTACK);
    }

    /**
     * Open a inventory for entity.
     * @param humanEntity HumanEntity target
     */
    public void open(HumanEntity humanEntity) {
        humanEntity.openInventory(this.inventory);
    }

    /**
     * Find a ItemStack and get his slot.
     * @param itemStack ItemStack to find
     * @return ItemStack slot
     */
    public int findItemSlot(ItemStack itemStack) {
        for(int i = 0; i < this.inventory.getSize(); ++i)
            if(this.inventory.getItem(i).equals(itemStack))
                return i;
        return -1;
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

    /**
     * Private method for create a Bukkit Inventory.
     * @param title Inventory title
     * @param size Inventory size
     * @return Created Bukkit Inventory
     */
    private Inventory createInventory(String title, int size) {
        return size != 5
                ? Bukkit.createInventory(this.holder, size, COLOR_TRANSLATOR.translateString(title))
                : Bukkit.createInventory(this.holder, InventoryType.HOPPER, COLOR_TRANSLATOR.translateString(title));
    }
}
