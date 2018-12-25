package admin;

import java.io.Serializable;
import java.util.Vector;

public class Message implements Serializable{
	 private static final long serialVersionUID = 1L;
	private String type;
	private Vector content;
	
	
	Message(String s,Vector content){
		this.type = s;
		this.content = content;
	}
	
	

	public Vector getContent() {
		return this.content;
		
	}

	public Object getType() {
		return this.type;
	}

}
