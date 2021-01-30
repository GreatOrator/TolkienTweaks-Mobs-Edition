package com.greatorator.tolkienmobs.utils;


import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;


import java.util.IllegalFormatException;
import java.util.Locale;

@Deprecated //You probably dont need this
public class TTMTranslator {
    private TTMTranslator() {
    }

    public static String translateToLocal(String key) {
        return I18n.format(key);

//        if (I18n.hasKey(key)) {
//            return I18n.format(key);
//        } else {
//            return I18n.translateToFallback(key);
//        }
    }

    public static String translateToLocalFormatted(String key, Object... format) {
        String s = translateToLocal(key);
        try {
            return String.format(s, format);
        } catch (IllegalFormatException e) {
//            Log.get().error("Format error: {}", s, e);
            return "Format error: " + s;
        }
    }

    public static String toLowercaseWithLocale(String string) {

        return string.toLowerCase(getLocale());
    }

    @SuppressWarnings("ConstantConditions")
    private static Locale getLocale() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft != null) {
            LanguageManager languageManager = minecraft.getLanguageManager();
            if (languageManager != null) {
                Language currentLanguage = languageManager.getCurrentLanguage();
                if (currentLanguage != null) {
                    return currentLanguage.getJavaLocale();
                }
            }
        }
        return Locale.getDefault();
    }
}
