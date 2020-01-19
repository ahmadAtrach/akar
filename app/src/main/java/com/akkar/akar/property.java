package com.akkar.akar;

public class property {
    private int id;
    private String title;
    private int price;
    private String type;
    private int bedrooms_nb;
    private int bathrooms_nb;
    private String furnishings;
    private double area;
    private String images_url;
    private String user_id;



    public property(int id, String title, int price, String type, int bedrooms_nb, int bathrooms_nb, String furnishings, double area, String images_url, String user_id) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.type = type;
        this.bedrooms_nb = bedrooms_nb;
        this.bathrooms_nb = bathrooms_nb;
        this.furnishings = furnishings;
        this.area = area;
        this.images_url = images_url;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getBedrooms_nb() {
        return bedrooms_nb;
    }

    public int getBathrooms_nb() {
        return bathrooms_nb;
    }

    public String getFurnishings() {
        return furnishings;
    }

    public double getArea() {
        return area;
    }

    public String getImages_url() {
        return images_url;
    }

    public String getUser_id() {
        return user_id;
    }
}
