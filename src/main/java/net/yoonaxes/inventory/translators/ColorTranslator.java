package net.yoonaxes.inventory.translators;

import net.md_5.bungee.api.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Translate your text to use Bukkit alternate color codes.
 * @author yoonaxes
 */
public interface ColorTranslator {

    /**
     * Method for translate colors in a single text.
     * @param string Text to translate
     * @return Translated String
     */
    default String translateString(String string) {
        if(string == null)
            return "";

        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Method for translate List<String> text.
     * @param stringList List<String> to translate.
     * @return Translated List<String>
     */
    default List<String> translateList(List<String> stringList) {
        return stringList.stream()
                .map(this::translateString)
                .collect(Collectors.toList());
    }

    /**
     * Method for translate String Array text.
     * @param strings String Array to translate.
     * @return Translated String Array.
     */
    default String[] translateArray(String... strings) {
        return Arrays.stream(strings)
                .map(this::translateString)
                .toArray(String[]::new);
    }
}
