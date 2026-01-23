package model;

public class Series {
	
	private int id;
    private String name;

    public Series() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {   // ← 必須
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {   // ← 必須
        this.name = name;
    }
}
