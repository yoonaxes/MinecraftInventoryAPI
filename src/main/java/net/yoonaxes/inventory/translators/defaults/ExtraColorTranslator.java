package net.yoonaxes.inventory.translators.defaults;

import net.yoonaxes.inventory.translators.ColorTranslator;

/**
 * Extra color translator to bukkit translate alternate codes.
 * Its additional advantage is the changeable characters specially.
 * @author yoonaxes
 */
public class ExtraColorTranslator implements ColorTranslator {

    @Override
    public String translateString(String textToTranslate) {
        return ColorTranslator.super.translateString(
                textToTranslate
                        .replace("{*}", "\u00B7")       /* · */
                        .replace("{>}", "\u00BB")       /* » */
                        .replace("{<}", "\u00AB")       /* « */
                        .replace("{->}", "\u2192")      /* → */
                        .replace("{<-}", "\u2190")      /* ← */
        );
    }
}
