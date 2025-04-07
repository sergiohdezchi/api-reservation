package com.helier.api_reservations.connector.response;

public class CityDTO {
    private String city_name;
    private String slug;
    private String ascii_display;

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAscii_display() {
        return ascii_display;
    }

    public void setAscii_display(String ascii_display) {
        this.ascii_display = ascii_display;
    }
}
