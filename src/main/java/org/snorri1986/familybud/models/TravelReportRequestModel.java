package org.snorri1986.familybud.models;

public class TravelReportRequestModel {
  String travelDestination;

  TravelReportRequestModel() {}

  public TravelReportRequestModel(String travelDestination) {
    this.travelDestination = travelDestination;
  }

  public String getTravelDestination() {
    return travelDestination;
  }

  public void setTravelDestination(String travelDestination) {
    this.travelDestination = travelDestination;
  }

  @Override
  public String toString() {
    return "TravelReportRequestModel{" +
            "travelDestination='" + travelDestination + '\'' +
            '}';
  }
}
