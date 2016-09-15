package cc.isotopestudio.scalepicker;

/**
 * Created by Mars on 7/15/2016.
 * Copyright ISOTOPE Studio
 */
enum ScaleType {
    SCALE1("Scales", false),
    SCALE3("Scales a third apart", false),
    SCALE6("Scales a sixth apart", false),
    SCALEIN3("Legato scales in thirds", true),
    CHROMATICSCALESAPART("Chromatic Scales a minor third apart", true),
    CHROMATICSCALES("Chromatic Scales in minor thirds", true),
    WHOLETONE("Whole-tone scales", true),
    ARPEGGIOS("Arpeggios", false);

    final String name;
    final boolean fixed;

    ScaleType(String name, boolean fixed) {
        this.name = name;
        this.fixed = fixed;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
