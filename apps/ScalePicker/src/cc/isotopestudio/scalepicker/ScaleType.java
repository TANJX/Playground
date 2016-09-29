package cc.isotopestudio.scalepicker;

/**
 * Created by Mars on 7/15/2016.
 * Copyright ISOTOPE Studio
 */
enum ScaleType {
    SCALE1("Scales"),
    SCALE3("Scales a third apart"),
    SCALE6("Scales a sixth apart"),
    SCALEIN3("Legato scales in thirds"),
    CHROMATICSCALESAPART("Chromatic Scales a minor third apart"),
    CHROMATICSCALES("Chromatic Scales in minor thirds"),
    WHOLETONE("Whole-tone scales"),
    ARPEGGIOS("Arpeggios"),
    DOMINANT("Dominant sevenths"),
    DIMINISHED("Diminished sevenths");

    final String name;

    ScaleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
