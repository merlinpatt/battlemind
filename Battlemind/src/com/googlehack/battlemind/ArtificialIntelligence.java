package com.googlehack.battlemind;

import java.util.ArrayList;

/**
 * Created by mikepatt on 2/7/14.
 */
public class ArtificialIntelligence {

    private static final int NUMERIC = 0,
        ALPHABETIC = 1,
        ALPHANUMERIC = 2;

    private String possibleChars = "";
    private ArrayList<String> passwordList;


    private String knownPassword;

    public ArtificialIntelligence(){}

    public ArtificialIntelligence(ArrayList<String> passwordList, int type){
        this.passwordList = passwordList;
        int passwordLength = passwordList.get(0).length();
        initPassword(passwordLength);
        initPossibleChars(type);
    }

    public String guess(){
        String guess = pop(passwordList); // picks most frequent
        System.out.println("Is: "+ guess + " a valid guess? " +isValidGuess(guess));
        while(!isValidGuess(guess)){
            if (!passwordList.isEmpty()){
            	System.out.println("Is: "+ guess + " a valid guess? " +isValidGuess(guess));
                guess = pop(passwordList);
            } else /* passwordList.isEmpty() */ {
                System.out.println("empty");
                break;
                //genListByBruteForce();
            }
        }
        return guess;
    }

    private void genListByBruteForce(){
        int numStars = countStars(knownPassword);
        genCombos("", numStars);
    }

    private void genCombos(String prefix, int length){
        for (int i = 0; i < possibleChars.length(); i++){
            String ch = String.valueOf(possibleChars.charAt(i));
            String combo = prefix + ch;
            if (combo.length() == length){
                passwordList.add(addComboToPass(combo));
            }
        }
        for (int i = 0; i < possibleChars.length(); i++){
            if (prefix.length() < length){
                String ch = String.valueOf(possibleChars.charAt(i));
                genCombos(prefix + ch, length);
            }
        }
    }

    private String addComboToPass(String combo){
        String comboWithPass = knownPassword;
        for(int i = 0; i < combo.length(); i++){
            comboWithPass = comboWithPass.replaceFirst("\\*", String.valueOf(combo.charAt(i)));
        }
        return comboWithPass;
    }

    private int countStars(String s){
        int count = 0;
        for (int i = 0; i < s.length(); i++){
            if("*".equals(String.valueOf(s.charAt(i)))){
                count++;
            }
        }
        return count;
    }

    private boolean isValidGuess(String guess){
    	//just added, check if guess is false
    	if(guess == null){
    		return false;
    	}
        for (int i = 0; i < guess.length(); i++){
            String passwordLetter = String.valueOf(knownPassword.charAt(i));
            String guessLetter = String.valueOf(guess.charAt(i));
            if (!"*".equals(passwordLetter)){
                if (!passwordLetter.equals(guessLetter)){
                    return false;
                }
            }
        }
        return true;
    }

    public String getResult(String guess, String pass){
        String result = "";
        for (int i = 0; i < guess.length(); i++){
            String passLetter = String.valueOf(pass.charAt(i));
            String guessLetter = String.valueOf(guess.charAt(i));
            if (!passLetter.equals(guessLetter)){
                result += "*";
            } else /* passLetter.equals(guessLetter) */ {
                result += passLetter;
            }
        }
        return result;
    }

    public void updatePassword(String result){
        knownPassword = result;
    }

    private String pop(ArrayList<String> arrayList){
    	int j = 1;
    	if(j == 1){
    		System.out.println(arrayList);
    		j++;
    	}
    	System.out.println("ArrayList size is: " +arrayList.size());
    	//just added
		if (!(arrayList.size() == 0)) {
			String item = arrayList.get(0);
			arrayList.remove(0);

			return item;
		}
    	else
    		return null;
    }

    private void initPossibleChars(int type){
        if (type == ALPHABETIC || type == ALPHANUMERIC){
            possibleChars += "abcdefghijklmnopqrstuvwxyz";
            possibleChars += possibleChars.toUpperCase();
        }
        if (type == NUMERIC || type == ALPHANUMERIC){
            possibleChars += "0123456789";
        }
    }

    private void initPassword(int length){
        knownPassword = "";
        for (int i = 0; i < length; i++){
            knownPassword += "*";
        }
    }

    //main method for testing purposes
/*    public static void main(String[] args){
        String pass = "doom";

        ArrayList<String> passList = new ArrayList<String>();
        passList.add("monk");
        passList.add("been");
        passList.add("bear");
        passList.add("chat");
        passList.add("beat");
        passList.add("meat");
        ArtificialIntelligence ai = new ArtificialIntelligence(passList, ArtificialIntelligence.ALPHABETIC);

        System.out.println("kPass=" + ai.knownPassword);
        String guess = ai.guess();
        System.out.println("guess=" + guess); // monk
        ai.updatePassword(ai.getResult(guess, pass));
        System.out.println("kPass=" + ai.knownPassword);
        guess = ai.guess();
        System.out.println("guess=" + guess); // been
        ai.updatePassword(ai.getResult(guess, pass));
        System.out.println("kPass=" + ai.knownPassword);
        guess = ai.guess();
        System.out.println("guess=" + guess); // bear
        ai.updatePassword(ai.getResult(guess, pass));
        System.out.println("kPass=" + ai.knownPassword);
        guess = ai.guess();
        System.out.println("guess=" + guess); // beat, skips chat
        ai.updatePassword(ai.getResult(guess, pass));

        ai.updatePassword("*!*!*");
        long oldTime = System.currentTimeMillis();
        ai.genCombos("",3);
        long newTime = System.currentTimeMillis();
        float total = (float)(newTime - oldTime);
        float seconds = total / 1000;
        System.out.println(seconds);

        String password = "*b*d*";
        password = password.replaceFirst("\\*", "x");
        System.out.println(password);
    }*/
}
