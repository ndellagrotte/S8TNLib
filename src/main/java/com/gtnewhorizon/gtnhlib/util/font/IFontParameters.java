package com.gtnewhorizon.gtnhlib.util.font;

public interface IFontParameters {
    default float demonica$getGlyphScaleX() { return 1.0f; }
    default float demonica$getGlyphScaleY() { return 1.0f; }
    default float demonica$getGlyphSpacing() { return 0.0f; }
    default float demonica$getWhitespaceScale() { return 1.0f; }
    default float demonica$getShadowOffset() { return 1.0f; }
    default float demonica$getCharWidthFine(char chr) { return 0.0f; }
}
