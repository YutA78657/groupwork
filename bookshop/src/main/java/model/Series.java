package model;

public class Series {
	
	private int id;
    private String name;

    public Series(int id , String name) {
    	this.id = id;
    	this.name = name;
    }
    
    public Series(String name) {
    	this.name = name;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }	

}
