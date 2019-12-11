package com.googlehack.battlemind;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileParse {
	
	private static HashMap<Integer, ArrayList<String>> numeric;
	private static HashMap<Integer, ArrayList<String>> alphabet;
	private static HashMap<Integer, ArrayList<String>> both;
	
	public FileParse(){
        alphabet = new HashMap<Integer, ArrayList<String>>();
        numeric = new HashMap<Integer, ArrayList<String>>();
        both = new HashMap<Integer, ArrayList<String>>();
	}

	public void parser(){
       // System.out.print("started\n");
		String startName = "C:\\Sites\\Battlemind-Project\\Battlemind\\";
        String fileName = startName + "passWithFreq.txt";
        File file = new File(fileName);
		String line = null;
		String[] passes = new String[2];
        //alphabet = new HashMap<Integer, ArrayList<String>>();
        //numeric = new HashMap<Integer, ArrayList<String>>();
        //both = new HashMap<Integer, ArrayList<String>>();
		
		try {
			BufferedReader br = new BufferedReader (new FileReader(file));
			line = br.readLine();
			if (line!=null){
				passes = line.split(",");
                System.out.print(passes[0]+"\n");
				checker(passes[0]);
			}
			
            while ((line = br.readLine()) != null){
				passes = line.split(",");
				checker(passes[0]);
             }
            
            br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	//checks to see if the password is alphabetical, numerical, or both, and adds
	//to the appropriate hashmap
	public void checker (String pass) {
		char[] characters = pass.toCharArray();
		boolean number = false;
		boolean alpha = false;
		ArrayList<String> list = new ArrayList<String>();
		
		for(char c : characters) {
			if (Character.isDigit(c)){
              //  System.out.print("Character: "+ c + "\n");
				number = true;
			}
			
			if (Character.isLetter(c)){
               // System.out.print("Character: "+ c + "\n");
                alpha = true;
			}
		}

        //System.out.print("Number: "+number+"\n");

		if (number && !alpha){
			int length = pass.length();
			if (numeric.containsKey(length)){
				numeric.get(length).add(pass);
			}
			else {
				list.add(pass);
				numeric.put(length, list);
			}
		}
		
		else if (alpha && !number){
			int length = pass.length();
			if (alphabet.containsKey(length)){
				alphabet.get(length).add(pass);
			}
			else {
				list.add(pass);
				alphabet.put(length, list);
			}
		}
		
		else if (alpha && number){
			int length = pass.length();
			if (both.containsKey(length)){
				both.get(length).add(pass);
			}
			else {
				list.add(pass);
				both.put(length, list);
			}
		}
	}

	public HashMap<Integer, ArrayList<String>> getNumeric() {
		return numeric;
	}

	public HashMap<Integer, ArrayList<String>> getAlphabet() {
		return alphabet;
	}

	public HashMap<Integer, ArrayList<String>> getBoth() {
		return both;
	}
}
