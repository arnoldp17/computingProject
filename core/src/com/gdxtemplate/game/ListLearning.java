package com.gdxtemplate.game;

import java.util.*;

public class ListLearning {
	public static void main(String[] args) {
		List<String> listStrings = new ArrayList<String>(); // defines the list as an array type
		listStrings.add("wat"); // adds an element to the list
		listStrings.add("na");
		listStrings.add("BATMAN!");
		int no = getRND(2);
		System.out.println(no);
		listStrings.remove(no);
		listStrings.add("ASS");
		System.out.println(listStrings); // prints the entire list in a stream of data

	}

	public static int getRND(int i) {
		int rnd = new Random().nextInt(i) + 1;
		System.out.println(i);
		return rnd;
	}
}
