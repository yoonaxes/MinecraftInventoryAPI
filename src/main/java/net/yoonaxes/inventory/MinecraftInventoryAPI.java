package net.yoonaxes.inventory;

import net.yoonaxes.inventory.listeners.InventoryListeners;
import net.yoonaxes.inventory.translators.ColorTranslator;
import net.yoonaxes.inventory.translators.defaults.DefaultColorTranslator;
import org.bukkit.plugin.Plugin;

/**
 * An open source Bukkit plugin for Inventory API.
 * This plugin is used to facilitate the creation of interactive inventory.
 * @author yoonaxes
 */
public final class MinecraftInventoryAPI {

    public static MinecraftInventoryAPI INSTANCE;

    private Plugin plugin;

    private ColorTranslator colorTranslator;

    public MinecraftInventoryAPI() {
        INSTANCE = this;
    }

    public void register(Plugin plugin) {
        this.plugin = plugin;

        if(this.colorTranslator == null)
            this.colorTranslator = DefaultColorTranslator.DEFAULT_COLOR_TRANSLATOR;

        plugin.getServer().getPluginManager().registerEvents(
                new InventoryListeners(),
                plugin
        );
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public MinecraftInventoryAPI withTranslator(ColorTranslator colorTranslator) {
        this.colorTranslator = colorTranslator;
        return this;
    }

    public ColorTranslator getColorTranslator() {
        return colorTranslator;
    }
}
