package com.example.data;

public class ListItem {
	
	public String listItem; 
	public String listType;
	public String next;
	public String nextType;
	
	public String toString()
	{
		return String.format("List Item: %s; %s; %s; %s", listItem, listType, next, nextType);
	}
	
}
