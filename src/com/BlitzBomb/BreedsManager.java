package com.BlitzBomb;

import java.util.ArrayList;

public class BreedsManager
{
	
	private static ArrayList<String> CheckedBreedsList;
	private static String image;
	public static ArrayList<String> getCheckedItem() {
		return CheckedBreedsList;
	}
	public static void setCheckedItemNumbers(ArrayList<String> selectedBreeds) {
		CheckedBreedsList = selectedBreeds;
	}
	public static String getItem(){
		return image;
	}
	public static void setImage(String imageselect) {
		image = imageselect;
	}
	
}