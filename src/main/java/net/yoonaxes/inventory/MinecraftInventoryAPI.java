package net.yoonaxes.inventory;

import net.yoonaxes.inventory.listeners.InventoryListeners;
import net.yoonaxes.inventory.translators.ColorTranslator;
import net.yoonaxes.inventory.translators.defaults.DefaultColorTranslator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftInventoryAPI {

    public static MinecraftInventoryAPI INSTANCE;

    private Plugin plugin;

    private ColorTranslator colorTranslator;

    public void register(JavaPlugin plugin) {
        INSTANCE = this;
        this.plugin = plugin;
        this.colorTranslator = DefaultColorTranslator.DEFAULT_COLOR_TRANSLATOR;

        plugin.getServer().getPluginManager().registerEvents(
                new InventoryListeners(),
                plugin
        );
    }

    public MinecraftInventoryAPI withTranslator(ColorTranslator colorTranslator) {
        this.colorTranslator = colorTranslator;
        return this;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public ColorTranslator getColorTranslator() {
        return colorTranslator;
    }
}
