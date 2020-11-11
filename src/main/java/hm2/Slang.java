import java.util.Arrays;

public class Slang {

    public static String fixAbbreviations(String str) {
        String[] replaceWith = {"please", "for your information"};
        replaceWith = Arrays.copyOf(replaceWith, replaceWith.length + 2);
        replaceWith[2] = "please, leave me alone";
        replaceWith[3] = "as soon as possible";

        String[] abbreviations = {"PLZ", "FYI", "GTFO", "ASAP"};

        for (int i = 0; i <= abbreviations.length - 1; i++) {
            str = str.replaceAll(abbreviations[i], replaceWith[i]);
        }
        return str;
    }

    public static String fixSmiles(String str) {
        String[] smiles = {":\\)", ":\\(", "¯\\\\_\\(ツ\\)\\_/¯"};
        String[] replaceWith = {"[smiling]", "[sad]", "[such is life]"};

        for (int i = 0; i <= smiles.length - 1; i++) {
            str = str.replaceAll(smiles[i], replaceWith[i]);
        }
        return str;
    }

    public static void main(String[] args) {
        String str = "FYI my dog is very :(. ¯\\_(ツ)_/¯. GTFO :) :)";
        String strFixedAbb = fixAbbreviations(str);
        String strFixedSmiles = fixSmiles(strFixedAbb);
        System.out.println("Input String: " + str);
        System.out.println("Fixed String: " + strFixedSmiles);
    }
}
