package com.example.jameel.everica.UsersApp.Helpers;

/**
 * Created by jameel on 5/4/2020.
 */

public class LocationTimeDate_helper_UsersApp {

    String startDate,endDate,startTime,endTime,pickupLocation,destinationLocation,hoursForRent;

    public LocationTimeDate_helper_UsersApp()
    {

    }

    public LocationTimeDate_helper_UsersApp(String startDate, String endDate, String startTime,
                                            String endTime, String pickupLocation, String destinationLocation,
                                            String hoursForRent) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pickupLocation = pickupLocation;
        this.destinationLocation = destinationLocation;
        this.hoursForRent = hoursForRent;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getHoursForRent() {
        return hoursForRent;
    }

    public void setHoursForRent(String hoursForRent) {
        this.hoursForRent = hoursForRent;
    }
}
