package net.yoonaxes.inventory.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class ListenerHandler<E extends Event> implements Listener {

    public ListenerHandler(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(
                this,
                plugin
        );
    }

    protected abstract void onEvent(E event);
}