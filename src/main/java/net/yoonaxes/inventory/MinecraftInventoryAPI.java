package net.yoonaxes.inventory;

import net.yoonaxes.inventory.listener.impl.InventoryClickListener;
import net.yoonaxes.inventory.listener.impl.InventoryCloseListener;
import net.yoonaxes.inventory.listener.impl.InventoryInteractListener;
import net.yoonaxes.inventory.listener.impl.InventoryOpenListener;
import net.yoonaxes.translator.ColorTranslator;
import net.yoonaxes.translator.impl.DefaultColorTranslator;
import org.bukkit.plugin.Plugin;

/**
 * An open source Bukkit util for Inventory API.
 * This API is used to facilitate the creation of interactive inventory.
 * TODO: Clickable Item System
 * @author yoonaxes
 */
public final class MinecraftInventoryAPI {

    public static MinecraftInventoryAPI INSTANCE;

    private Plugin plugin;

    private ColorTranslator colorTranslator;

    /**
     * Create a new instance
     */
    public MinecraftInventoryAPI() {
        INSTANCE = this;
    }

    /**
     * Register this API in your Plugin.
     * @param plugin Plugin that uses the API
     */
    public void register(Plugin plugin) {
        this.plugin = plugin;

        if(this.colorTranslator == null)
            this.colorTranslator = DefaultColorTranslator.DEFAULT_COLOR_TRANSLATOR;

        new InventoryOpenListener(plugin);
        new InventoryCloseListener(plugin);
        new InventoryClickListener(plugin);
        new InventoryInteractListener(plugin);
    }

    /**
     * Select a ColorTranslator method.
     * @param translator ColorTranslator
     * @return MinecraftMessageAPI
     */
    public MinecraftInventoryAPI withTranslator(ColorTranslator translator) {
        this.colorTranslator = translator;
        return this;
    }

    /**
     * Get a registerd Plugin method.
     * @return Registred Plugin
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Get a ColorTranslator method.
     * @return Selected ColorTranslator
     */
    public ColorTranslator getTranslator() {
        return colorTranslator;
    }

    /**
     * Get a ColorTranslator static method.
     * @return Selected ColorTranslator
     */
    public static ColorTranslator getColorTranslator() {
        return INSTANCE == null
                ? DefaultColorTranslator.DEFAULT_COLOR_TRANSLATOR
                : INSTANCE.getTranslator();
    }
}
