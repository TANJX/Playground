package app.scalePicker;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Mars on 7/15/2016.
 * Copyright ISOTOPE Studio
 */
class ScaleResult {
    final HashMap<ScaleType, List<String>> result;
    final HashMap<ScaleType, Integer> num;

    //Settings
    private int scale1 = 4;
    private int scale3 = 4;
    private int scale6 = 4;
    private int arpeggios = 4;

    ScaleResult() {
        result = new HashMap<>();
        num = new HashMap<>();
        for (ScaleType type : ScaleType.values()) {
            switch (type) {
                case SCALE1: {
                    num.put(type, scale1);
                    break;
                }
                case SCALE3: {
                    num.put(type, scale3);
                    break;
                }
                case SCALE6: {
                    num.put(type, scale6);
                    break;
                }
                case SCALEIN3: {
                    num.put(type, 4);
                    break;
                }
                case CHROMATICSCALESAPART: {
                    num.put(type, 1);
                    break;
                }
                case CHROMATICSCALES: {
                    num.put(type, 4);
                    break;
                }
                case WHOLETONE: {
                    num.put(type, 1);
                    break;
                }
                case ARPEGGIOS: {
                    num.put(type, arpeggios);
                    break;
                }
            }
        }
    }

    private static String genRandom(ScaleType type) {
        switch (type) {
            case SCALE1:
                break;
            case SCALE3:
                break;
            case SCALE6:
                break;
            case SCALEIN3:
                break;
            case CHROMATICSCALESAPART:
                break;
            case CHROMATICSCALES:
                break;
            case WHOLETONE:
                break;
            case ARPEGGIOS:
                break;
        }
        return null;
    }

}
