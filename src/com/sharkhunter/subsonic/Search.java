package com.sharkhunter.subsonic;

import net.pms.dlna.virtual.*;

public class Search extends VirtualFolder {
	private SearchObj sobj;
	private String name;
	private StringBuilder sb;
	private boolean searched; 
	
	
	public Search(SearchObj obj) {
		this(obj,"");
	}
	
	public Search(SearchObj obj,String str) {
		super(str,null);
		this.sobj=obj;
		this.sb=new StringBuilder(str);
		searched=false;
	}
	
	
	public SearchObj getSearchObj() {
		return sobj;
	}
	
	public String getName() {
		return sb.toString();
	}
	
	public String getSystemName() {
		return getName();
	}
	
	public void resolve() {
		discovered=false;
	}
	
	public synchronized void append(char ch) {
		if(ch=='\0') 
			sb=new StringBuilder();
		else if(ch=='\b') {
			if(sb.length()!=0)
				sb.deleteCharAt(sb.length()-1);
		}
		else
			this.sb.append(ch);
	}
	
	public void discoverChildren() {
		if(searched) {
			children.clear();
		}
		this.sobj.search(sb.toString(),this);
		searched=true;
	}

	
	public void refreshChildern() {
		discovered=false;
	}
	
	
}
