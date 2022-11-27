package com.greatorator.tolkienmobs.utils;

import com.brandon3055.brandonscore.utils.MathUtils;
import com.google.common.primitives.UnsignedInts;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

@SuppressWarnings({"unused", "MagicNumber", "SpellCheckingInspection"})
public class ColorUtility {
    private static final Map<String, ColorUtility> NAMED_MAP = new HashMap<>();
    private static final Pattern PATTERN_LEADING_JUNK = Pattern.compile("(#|0x)", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_HEX_CODE = Pattern.compile("(#|0x)?[0-9a-f]{1,8}", Pattern.CASE_INSENSITIVE);

    public static final int VALUE_WHITE = 0xFFFFFF;

    //region CSS Colors
    public static final ColorUtility ALICEBLUE = named("AliceBlue", 0xF0F8FF);
    public static final ColorUtility ANTIQUEWHITE = named("AntiqueWhite", 0xFAEBD7);
    public static final ColorUtility AQUA = named("Aqua", 0x00FFFF);
    public static final ColorUtility AQUAMARINE = named("Aquamarine", 0x7FFFD4);
    public static final ColorUtility AZURE = named("Azure", 0xF0FFFF);
    public static final ColorUtility BEIGE = named("Beige", 0xF5F5DC);
    public static final ColorUtility BISQUE = named("Bisque", 0xFFE4C4);
    public static final ColorUtility BLACK = named("Black", 0x000000);
    public static final ColorUtility BLANCHEDALMOND = named("BlanchedAlmond", 0xFFEBCD);
    public static final ColorUtility BLUE = named("Blue", 0x0000FF);
    public static final ColorUtility BLUEVIOLET = named("BlueViolet", 0x8A2BE2);
    public static final ColorUtility BROWN = named("Brown", 0xA52A2A);
    public static final ColorUtility BURLYWOOD = named("BurlyWood", 0xDEB887);
    public static final ColorUtility CADETBLUE = named("CadetBlue", 0x5F9EA0);
    public static final ColorUtility CHARTREUSE = named("Chartreuse", 0x7FFF00);
    public static final ColorUtility CHOCOLATE = named("Chocolate", 0xD2691E);
    public static final ColorUtility CORAL = named("Coral", 0xFF7F50);
    public static final ColorUtility CORNFLOWERBLUE = named("CornflowerBlue", 0x6495ED);
    public static final ColorUtility CORNSILK = named("Cornsilk", 0xFFF8DC);
    public static final ColorUtility CRIMSON = named("Crimson", 0xDC143C);
    public static final ColorUtility CYAN = named("Cyan", 0x00FFFF);
    public static final ColorUtility DARKBLUE = named("DarkBlue", 0x00008B);
    public static final ColorUtility DARKCYAN = named("DarkCyan", 0x008B8B);
    public static final ColorUtility DARKGOLDENROD = named("DarkGoldenRod", 0xB8860B);
    public static final ColorUtility DARKGRAY = named("DarkGray", 0xA9A9A9);
    public static final ColorUtility DARKGREY = named("DarkGrey", 0xA9A9A9);
    public static final ColorUtility DARKGREEN = named("DarkGreen", 0x006400);
    public static final ColorUtility DARKKHAKI = named("DarkKhaki", 0xBDB76B);
    public static final ColorUtility DARKMAGENTA = named("DarkMagenta", 0x8B008B);
    public static final ColorUtility DARKOLIVEGREEN = named("DarkOliveGreen", 0x556B2F);
    public static final ColorUtility DARKORANGE = named("DarkOrange", 0xFF8C00);
    public static final ColorUtility DARKORCHID = named("DarkOrchid", 0x9932CC);
    public static final ColorUtility DARKRED = named("DarkRed", 0x8B0000);
    public static final ColorUtility DARKSALMON = named("DarkSalmon", 0xE9967A);
    public static final ColorUtility DARKSEAGREEN = named("DarkSeaGreen", 0x8FBC8F);
    public static final ColorUtility DARKSLATEBLUE = named("DarkSlateBlue", 0x483D8B);
    public static final ColorUtility DARKSLATEGRAY = named("DarkSlateGray", 0x2F4F4F);
    public static final ColorUtility DARKSLATEGREY = named("DarkSlateGrey", 0x2F4F4F);
    public static final ColorUtility DARKTURQUOISE = named("DarkTurquoise", 0x00CED1);
    public static final ColorUtility DARKVIOLET = named("DarkViolet", 0x9400D3);
    public static final ColorUtility DEEPPINK = named("DeepPink", 0xFF1493);
    public static final ColorUtility DEEPSKYBLUE = named("DeepSkyBlue", 0x00BFFF);
    public static final ColorUtility DIMGRAY = named("DimGray", 0x696969);
    public static final ColorUtility DIMGREY = named("DimGrey", 0x696969);
    public static final ColorUtility DODGERBLUE = named("DodgerBlue", 0x1E90FF);
    public static final ColorUtility FIREBRICK = named("FireBrick", 0xB22222);
    public static final ColorUtility FLORALWHITE = named("FloralWhite", 0xFFFAF0);
    public static final ColorUtility FORESTGREEN = named("ForestGreen", 0x228B22);
    public static final ColorUtility FUCHSIA = named("Fuchsia", 0xFF00FF);
    public static final ColorUtility GAINSBORO = named("Gainsboro", 0xDCDCDC);
    public static final ColorUtility GHOSTWHITE = named("GhostWhite", 0xF8F8FF);
    public static final ColorUtility GOLD = named("Gold", 0xFFD700);
    public static final ColorUtility GOLDENROD = named("GoldenRod", 0xDAA520);
    public static final ColorUtility GRAY = named("Gray", 0x808080);
    public static final ColorUtility GREY = named("Grey", 0x808080);
    public static final ColorUtility GREEN = named("Green", 0x008000);
    public static final ColorUtility GREENYELLOW = named("GreenYellow", 0xADFF2F);
    public static final ColorUtility HONEYDEW = named("HoneyDew", 0xF0FFF0);
    public static final ColorUtility HOTPINK = named("HotPink", 0xFF69B4);
    public static final ColorUtility INDIANRED = named("IndianRed", 0xCD5C5C);
    public static final ColorUtility INDIGO = named("Indigo", 0x4B0082);
    public static final ColorUtility IVORY = named("Ivory", 0xFFFFF0);
    public static final ColorUtility KHAKI = named("Khaki", 0xF0E68C);
    public static final ColorUtility LAVENDER = named("Lavender", 0xE6E6FA);
    public static final ColorUtility LAVENDERBLUSH = named("LavenderBlush", 0xFFF0F5);
    public static final ColorUtility LAWNGREEN = named("LawnGreen", 0x7CFC00);
    public static final ColorUtility LEMONCHIFFON = named("LemonChiffon", 0xFFFACD);
    public static final ColorUtility LIGHTBLUE = named("LightBlue", 0xADD8E6);
    public static final ColorUtility LIGHTCORAL = named("LightCoral", 0xF08080);
    public static final ColorUtility LIGHTCYAN = named("LightCyan", 0xE0FFFF);
    public static final ColorUtility LIGHTGOLDENRODYELLOW = named("LightGoldenRodYellow", 0xFAFAD2);
    public static final ColorUtility LIGHTGRAY = named("LightGray", 0xD3D3D3);
    public static final ColorUtility LIGHTGREY = named("LightGrey", 0xD3D3D3);
    public static final ColorUtility LIGHTGREEN = named("LightGreen", 0x90EE90);
    public static final ColorUtility LIGHTPINK = named("LightPink", 0xFFB6C1);
    public static final ColorUtility LIGHTSALMON = named("LightSalmon", 0xFFA07A);
    public static final ColorUtility LIGHTSEAGREEN = named("LightSeaGreen", 0x20B2AA);
    public static final ColorUtility LIGHTSKYBLUE = named("LightSkyBlue", 0x87CEFA);
    public static final ColorUtility LIGHTSLATEGRAY = named("LightSlateGray", 0x778899);
    public static final ColorUtility LIGHTSLATEGREY = named("LightSlateGrey", 0x778899);
    public static final ColorUtility LIGHTSTEELBLUE = named("LightSteelBlue", 0xB0C4DE);
    public static final ColorUtility LIGHTYELLOW = named("LightYellow", 0xFFFFE0);
    public static final ColorUtility LIME = named("Lime", 0x00FF00);
    public static final ColorUtility LIMEGREEN = named("LimeGreen", 0x32CD32);
    public static final ColorUtility LINEN = named("Linen", 0xFAF0E6);
    public static final ColorUtility MAGENTA = named("Magenta", 0xFF00FF);
    public static final ColorUtility MAROON = named("Maroon", 0x800000);
    public static final ColorUtility MEDIUMAQUAMARINE = named("MediumAquaMarine", 0x66CDAA);
    public static final ColorUtility MEDIUMBLUE = named("MediumBlue", 0x0000CD);
    public static final ColorUtility MEDIUMORCHID = named("MediumOrchid", 0xBA55D3);
    public static final ColorUtility MEDIUMPURPLE = named("MediumPurple", 0x9370DB);
    public static final ColorUtility MEDIUMSEAGREEN = named("MediumSeaGreen", 0x3CB371);
    public static final ColorUtility MEDIUMSLATEBLUE = named("MediumSlateBlue", 0x7B68EE);
    public static final ColorUtility MEDIUMSPRINGGREEN = named("MediumSpringGreen", 0x00FA9A);
    public static final ColorUtility MEDIUMTURQUOISE = named("MediumTurquoise", 0x48D1CC);
    public static final ColorUtility MEDIUMVIOLETRED = named("MediumVioletRed", 0xC71585);
    public static final ColorUtility MIDNIGHTBLUE = named("MidnightBlue", 0x191970);
    public static final ColorUtility MINTCREAM = named("MintCream", 0xF5FFFA);
    public static final ColorUtility MISTYROSE = named("MistyRose", 0xFFE4E1);
    public static final ColorUtility MOCCASIN = named("Moccasin", 0xFFE4B5);
    public static final ColorUtility NAVAJOWHITE = named("NavajoWhite", 0xFFDEAD);
    public static final ColorUtility NAVY = named("Navy", 0x000080);
    public static final ColorUtility OLDLACE = named("OldLace", 0xFDF5E6);
    public static final ColorUtility OLIVE = named("Olive", 0x808000);
    public static final ColorUtility OLIVEDRAB = named("OliveDrab", 0x6B8E23);
    public static final ColorUtility ORANGE = named("Orange", 0xFFA500);
    public static final ColorUtility ORANGERED = named("OrangeRed", 0xFF4500);
    public static final ColorUtility ORCHID = named("Orchid", 0xDA70D6);
    public static final ColorUtility PALEGOLDENROD = named("PaleGoldenRod", 0xEEE8AA);
    public static final ColorUtility PALEGREEN = named("PaleGreen", 0x98FB98);
    public static final ColorUtility PALETURQUOISE = named("PaleTurquoise", 0xAFEEEE);
    public static final ColorUtility PALEVIOLETRED = named("PaleVioletRed", 0xDB7093);
    public static final ColorUtility PAPAYAWHIP = named("PapayaWhip", 0xFFEFD5);
    public static final ColorUtility PEACHPUFF = named("PeachPuff", 0xFFDAB9);
    public static final ColorUtility PERU = named("Peru", 0xCD853F);
    public static final ColorUtility PINK = named("Pink", 0xFFC0CB);
    public static final ColorUtility PLUM = named("Plum", 0xDDA0DD);
    public static final ColorUtility POWDERBLUE = named("PowderBlue", 0xB0E0E6);
    public static final ColorUtility PURPLE = named("Purple", 0x800080);
    public static final ColorUtility REBECCAPURPLE = named("RebeccaPurple", 0x663399);
    public static final ColorUtility RED = named("Red", 0xFF0000);
    public static final ColorUtility ROSYBROWN = named("RosyBrown", 0xBC8F8F);
    public static final ColorUtility ROYALBLUE = named("RoyalBlue", 0x4169E1);
    public static final ColorUtility SADDLEBROWN = named("SaddleBrown", 0x8B4513);
    public static final ColorUtility SALMON = named("Salmon", 0xFA8072);
    public static final ColorUtility SANDYBROWN = named("SandyBrown", 0xF4A460);
    public static final ColorUtility SEAGREEN = named("SeaGreen", 0x2E8B57);
    public static final ColorUtility SEASHELL = named("SeaShell", 0xFFF5EE);
    public static final ColorUtility SIENNA = named("Sienna", 0xA0522D);
    public static final ColorUtility SILVER = named("Silver", 0xC0C0C0);
    public static final ColorUtility SKYBLUE = named("SkyBlue", 0x87CEEB);
    public static final ColorUtility SLATEBLUE = named("SlateBlue", 0x6A5ACD);
    public static final ColorUtility SLATEGRAY = named("SlateGray", 0x708090);
    public static final ColorUtility SLATEGREY = named("SlateGrey", 0x708090);
    public static final ColorUtility SNOW = named("Snow", 0xFFFAFA);
    public static final ColorUtility SPRINGGREEN = named("SpringGreen", 0x00FF7F);
    public static final ColorUtility STEELBLUE = named("SteelBlue", 0x4682B4);
    public static final ColorUtility TAN = named("Tan", 0xD2B48C);
    public static final ColorUtility TEAL = named("Teal", 0x008080);
    public static final ColorUtility THISTLE = named("Thistle", 0xD8BFD8);
    public static final ColorUtility TOMATO = named("Tomato", 0xFF6347);
    public static final ColorUtility TURQUOISE = named("Turquoise", 0x40E0D0);
    public static final ColorUtility VIOLET = named("Violet", 0xEE82EE);
    public static final ColorUtility WHEAT = named("Wheat", 0xF5DEB3);
    public static final ColorUtility WHITE = named("White", 0xFFFFFF);
    public static final ColorUtility WHITESMOKE = named("WhiteSmoke", 0xF5F5F5);
    public static final ColorUtility YELLOW = named("Yellow", 0xFFFF00);
    public static final ColorUtility YELLOWGREEN = named("YellowGreen", 0x9ACD32);
    //endregion

    private final int color;
    private final int red;
    private final int green;
    private final int blue;
    private final int alpha;

    public ColorUtility(int color) {
        this.red = (color >> 16) & 0xFF;
        this.green = (color >> 8) & 0xFF;
        this.blue = color & 0xFF;
        int a = (color >> 24) & 0xFF;
        this.alpha = a > 0 ? a : 0xFF;
        this.color = (this.alpha << 24) | (color & 0xFFFFFF);
    }

    public ColorUtility(int red, int green, int blue) {
        this(red, green, blue, 255);
    }

    public ColorUtility(int red, int green, int blue, int alpha) {
        this.alpha = alpha > 0 ? alpha : 0xFF;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.color = (this.alpha << 24) | (this.red << 16) | (this.green << 8) | this.blue;
    }

    public ColorUtility(float red, float green, float blue) {
        this(red, green, blue, 1f);
    }

    public ColorUtility(float red, float green, float blue, float alpha) {
        this((int) (red * 255), (int) (green * 255), (int) (blue * 255), (int) (alpha * 255));
    }

    private static ColorUtility named(String name, int color) {
        ColorUtility c = new ColorUtility(color);
        NAMED_MAP.put(name.toLowerCase(Locale.ROOT), c);
        return c;
    }

    //region Forge Config

    /**
     * Validator for ForgeConfigSpec. Accepts CSS color names or hex codes with optional leading '#'
     * or '0x'.
     *
     * @param str The string to validate
     * @return True if and only if the string can be parsed
     */
    public static boolean validate(String str) {
        return NAMED_MAP.containsKey(str.toLowerCase(Locale.ROOT))
                || PATTERN_HEX_CODE.matcher(str).matches();
    }

    //endregion

    //region Parsing and Formatting

    /**
     * Format the color as it would appear in a config file.
     *
     * @param color The color
     * @return A string in the form "#XXXXXX" or "#XXXXXXXX", where 'X' is a hex digit.
     */
    public static String format(int color) {
        return String.format(color > 0xFFFFFF ? "#%08X" : "#%06X", color);
    }

    /**
     * Attempt to parse the string as a color. May be either a common CSS color name or a hex code
     * with optional leading '#' or '0x'. Consider using {@code define} or {@code defineInt}
     * instead, which handles validation.
     *
     * @param str The string to try to parse
     * @return A TolkienColor object based on str
     * @throws NumberFormatException If the string cannot be parsed
     * @throws NullPointerException  If the string is null
     * @implNote Uses {@link UnsignedInts#parseUnsignedInt(String, int)} for parsing
     */
    public static ColorUtility parse(String str) {
        return new ColorUtility(parseInt(str));
    }

    /**
     * Attempt to parse a color, returning the default if it is not valid.
     *
     * @param str          The string to parse
     * @param defaultValue The fallback value
     * @return A color parsed from str, or from defaultValue if str is invalid
     */
    public static ColorUtility tryParse(String str, int defaultValue) {
        if (!validate(str)) return new ColorUtility(defaultValue);
        return parse(str);
    }

    /**
     * Attempt to parse the string as a color, but returns the integer representation instead of
     * creating a TolkienColor object. May be either a common CSS color name or a hex code with optional
     * leading '#' or '0x'.
     *
     * @param str The string to try to parse
     * @return A TolkienColor object based on str
     * @throws NumberFormatException If the string cannot be parsed
     * @throws NullPointerException  If the string is null
     * @implNote Uses {@link UnsignedInts#parseUnsignedInt(String, int)} for parsing
     */
    public static int parseInt(String str) {
        // Named color?
        str = str.toLowerCase(Locale.ROOT);
        if (NAMED_MAP.containsKey(str)) return NAMED_MAP.get(str).getColor();

        // Hex code
        str = PATTERN_LEADING_JUNK.matcher(str).replaceFirst("");
        return UnsignedInts.parseUnsignedInt(str, 16);
    }

    /**
     * Read a color from JSON. The property must be a string. If the JsonObject does not contain a
     * property with the given name, a TolkienColor is created from defaultValue. If the valid from JSON is
     * invalid, an exception will be thrown.
     *
     * @param json         The JSON object
     * @param propertyName The property to read from json
     * @param defaultValue A default value to use if json does not have the given property
     * @return A color parsed from the value read from json
     * @throws NumberFormatException If the value from json cannot be parsed
     */
    public static ColorUtility from(JsonObject json, String propertyName, int defaultValue) {
        String defaultStr = Integer.toHexString(defaultValue);
        JsonElement element = json.get(propertyName);
        if (element == null || !element.isJsonPrimitive())
            return ColorUtility.parse(defaultStr);
        return ColorUtility.parse(element.getAsString());
    }

    //endregion

    public ColorUtility blendWith(ColorUtility other) {
        return blend(this, other);
    }

    public static ColorUtility blend(ColorUtility color1, ColorUtility color2) {
        return blend(color1, color2, 0.5f);
    }

    public static ColorUtility blend(ColorUtility color1, ColorUtility color2, float ratio) {
        int i1 = color1.color;
        int i2 = color2.color;

        int color = blend(i1, i2, ratio);
        return new ColorUtility(color);
    }

    public static int blend(int color1, int color2) {
        return blend(color1, color2, 0.5f);
    }

    public static int blend(int color1, int color2, float ratio) {
        ratio = MathUtils.clamp(ratio, 0f, 1f);
        float iRatio = 1f - ratio;

        int a1 = (color1 >> 24 & 0xff);
        int r1 = ((color1 & 0xff0000) >> 16);
        int g1 = ((color1 & 0xff00) >> 8);
        int b1 = (color1 & 0xff);

        int a2 = (color2 >> 24 & 0xff);
        int r2 = ((color2 & 0xff0000) >> 16);
        int g2 = ((color2 & 0xff00) >> 8);
        int b2 = (color2 & 0xff);

        int a = (int) ((a1 * iRatio) + (a2 * ratio));
        int r = (int) ((r1 * iRatio) + (r2 * ratio));
        int g = (int) ((g1 * iRatio) + (g2 * ratio));
        int b = (int) ((b1 * iRatio) + (b2 * ratio));

        return a << 24 | r << 16 | g << 8 | b;
    }

    public int getColor() {
        return color;
    }

    public float getRed() {
        return red / 255f;
    }

    public float getGreen() {
        return green / 255f;
    }

    public float getBlue() {
        return blue / 255f;
    }

    public float getAlpha() {
        return alpha / 255f;
    }

    public int getRedInt() {
        return red;
    }

    public int getGreenInt() {
        return green;
    }

    public int getBlueInt() {
        return blue;
    }

    public int getAlphaInt() {
        return alpha;
    }
}
