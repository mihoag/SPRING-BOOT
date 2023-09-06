package com.demoweb.demoweb;

import java.util.ArrayList;

public class listContact {

	public static ArrayList<contact> getList()
	{
		ArrayList<contact> arr = new ArrayList<contact>();
		
		arr.add(new contact("Marry John", "marry.john@gmail.com", "USA"));
		arr.add(new contact("Tom Smith", "tomsmith@outlook.com", "England"));
		arr.add(new contact("John Purcell", "john123@yahoo.com", "Australia"));
		arr.add(new contact("Siva Krishna", "sivakrishna@gmail.com", "India"));
		return arr;
	}
}
