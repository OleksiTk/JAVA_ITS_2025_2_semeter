package com.example;

import javafx.beans.property.*;

public class Site {
    private final IntegerProperty id;
    private final StringProperty name;
    private final DoubleProperty latitude;
    private final DoubleProperty longitude;
    private final StringProperty region;
    private final StringProperty image;

    // Конструктор
    public Site(int id, String name, double latitude, double longitude, String region, String image) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.latitude = new SimpleDoubleProperty(latitude);
        this.longitude = new SimpleDoubleProperty(longitude);
        this.region = new SimpleStringProperty(region);
        this.image = new SimpleStringProperty(image);
    }

    // Getters для значень
    public int getId() { return id.get(); }
    public String getName() { return name.get(); }
    public double getLatitude() { return latitude.get(); }
    public double getLongitude() { return longitude.get(); }
    public String getRegion() { return region.get(); }
    public String getImage() { return image.get(); }

    // Properties (потрібні для TableView)
    public IntegerProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public DoubleProperty latitudeProperty() { return latitude; }
    public DoubleProperty longitudeProperty() { return longitude; }
    public StringProperty regionProperty() { return region; }
    public StringProperty imageProperty() { return image; }
}