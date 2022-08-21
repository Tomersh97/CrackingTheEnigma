package utils;

public class Utilities {
    public static boolean isRomanFrom1To5(String input){
        return  input.equalsIgnoreCase("I") ||
                input.equalsIgnoreCase("II") ||
                input.equalsIgnoreCase("III") ||
                input.equalsIgnoreCase("IV") ||
                input.equalsIgnoreCase("V");
    }

    public static int convertRomanItoVToInt(String input){
        int converted;
        if(input.equalsIgnoreCase("I")){
            converted = 1;
        }
        else if(input.equalsIgnoreCase("II")){
            converted = 2;
        }
        else if(input.equalsIgnoreCase("III")){
            converted = 3;
        }
        else if(input.equalsIgnoreCase("IV")){
            converted = 4;
        }
        else {
            converted = 5;
        }
        return converted;
    }

    public static String convertInteger1to5ToRoman(int input){
        String converted = "I";
        if(input == 2){
            converted = "II";
        }
        else if(input == 3){
            converted = "III";
        }
        else if(input == 4){
            converted = "IV";
        }
        else if(input == 5){
            converted = "V";
        }
        return converted;
    }
}
