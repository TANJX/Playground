package cc.isotopestudio.scalepicker;

/**
 * Created by Mars on 7/15/2016.
 * Copyright ISOTOPE Studio
 */
class ScaleResult {

    private static String getRandomPerform() {
        switch (randomFromTo(0, 1)) {
            case 0:
                return "Legato";
        }
        return "Staccato";
    }

    /**
     * @param i 0 no both hand, 1 with both hand,
     * @return both hand,, right hand,, or left hand,
     */
    private static String getRandomHand(int i) {
        if (i == 0) {
            switch (randomFromTo(0, 1)) {
                case 0:
                    return "Left Hand";
            }
            return "Right Hand";
        }
        if (i == 1) {
            switch (randomFromTo(0, 2)) {
                case 0:
                    return "Left Hand";
                case 1:
                    return "Right Hand";
            }
            return "Both Hand";
        }
        return null;
    }

    /**
     * @param i 0 no melodic, 1 with melodic
     * @return Random Major, Minor Harmonic, or Minor Harmonic
     */
    private static String getRandomForm(int i) {
        if (i == 0) {
            switch (randomFromTo(0, 1)) {
                case 0:
                    return "Major";
            }
            return "Minor Harmonic";
        }
        if (i == 1) {
            switch (randomFromTo(0, 2)) {
                case 0:
                    return "Major";
                case 1:
                    return "Minor Melodic";
            }
            return "Minor Harmonic";
        }
        return null;
    }

    private static final String[] mainKeys = {"C", "D", "B", "#F", "F", "bE", "#G", "#C"};
    private static final String[] keys = {"C", "#C", "D", "#D", "E", "F", "#F", "G", "#G", "A", "#A", "B"};

    private static String getRandomKey() {
        return keys[randomFromTo(0, keys.length - 1)];
    }

    private static String getRandomMainKey() {
        return mainKeys[randomFromTo(0, mainKeys.length - 1)];
    }

    /**
     * @param form can ONLY be Major, Minor Harmonic, or Minor Harmonic
     * @return Random mainKeys
     */
    private static String getRandomMainKey(String form) {
        String key = getRandomMainKey();
        if (form.equals("Major")) {
            if (key.equals("#G"))
                return "bA";
            if (key.equals("#C"))
                return "bD";
        }
        return key;
    }

    private static String getRandomPosition() {
        switch (randomFromTo(0, 2)) {
            case 0:
                return "root position";
            case 1:
                return "first inversion";
        }
        return "second inversion";
    }

    public static String genRandom(ScaleType type) {
        String result = "";
        switch (type) {
            case SCALE1:
                result += type.getName() + " - " + getRandomPerform() + ", " + getRandomHand(1) + ", ";
                String form = getRandomForm(0);
                result += " " + form + " " + getRandomMainKey(form);
                break;
            case SCALE3:
                result += type.getName() + " - " + getRandomPerform() + ", ";
                form = getRandomForm(1);
                result += " " + form + " " + getRandomMainKey(form);
                break;
            case SCALE6:
                result += type.getName() + " - " + getRandomPerform() + ", ";
                form = getRandomForm(1);
                result += " " + form + " " + getRandomMainKey(form);
                break;
            case SCALEIN3:
                result += type.getName() + " - " + getRandomHand(0);
                switch (randomFromTo(0, 1)) {
                    case 0:
                        result += " C";
                        break;
                    case 1:
                        result += " bB";
                        break;
                }
                break;
            case CHROMATICSCALESAPART:
                result += type.getName() + " - "
                        + getRandomPerform() + ", " + getRandomHand(0) + ", beginning on " + getRandomKey();
                break;
            case CHROMATICSCALES:
                result += type.getName() + " - " + getRandomHand(0) + ", beginning on #A and #C";
                break;
            case WHOLETONE:
                result += type.getName();
                break;
            case ARPEGGIOS:
                result += type.getName() + " - " + getRandomHand(1) + ", ";
                form = getRandomForm(0);
                result += " " + form + " " + getRandomMainKey(form) + ", beginning on " + getRandomPosition();
                break;
        }
        return result;
    }

    private static int randomFromTo(int a, int b) {
        return (int) (Math.random() * (b - a + 1) + a);
    }

}
