package net.yoonaxes.inventory.translators.defaults;

import net.yoonaxes.inventory.translators.ColorTranslator;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultColorTranslator implements ColorTranslator {

    public static final DefaultColorTranslator DEFAULT_COLOR_TRANSLATOR = new DefaultColorTranslator();

    @Override
    public String translateString(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    @Override
    public List<String> translateList(List<String> stringList) {
        return stringList.stream()
                .map(this::translateString)
                .collect(Collectors.toList());
    }
}
