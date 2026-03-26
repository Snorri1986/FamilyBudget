package org.snorri1986.familybud.models;

public class LocationModel {
  String country;
  String city;
  int vat;

  public LocationModel() {}

  public LocationModel(String country, String city, int vat) {
    this.country = country;
    this.city = city;
    this.vat = vat;
  }

  public LocationModel(String country, String city) {
    this.country = country;
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public int getVat() {
    return vat;
  }

  public void setVat(int vat) {
    this.vat = vat;
  }

  @Override
  public String toString() {
    return "LocationModel{" +
            "country='" + country + '\'' +
            ", city='" + city + '\'' +
            ", vat=" + vat +
            '}';
  }
}
