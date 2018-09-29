package net.mallnow.mallnowapp.models;

public class Mall {
    private String Address;
    private String Capacity;
    private String City;
    private String Latitude;
    private String Logitude;
    private String MallID;
    private String MallName;
    private String MobileIcon;
    private String ParkingSpace;

    public Mall() {

    }

    public Mall(String address, String capacity, String city, String latitude, String logitude, String mallID, String mallName, String mobileIcon, String parkingSpace) {
        Address = address;
        Capacity = capacity;
        City = city;
        Latitude = latitude;
        Logitude = logitude;
        MallID = mallID;
        MallName = mallName;
        MobileIcon = mobileIcon;
        ParkingSpace = parkingSpace;
    }

    public String getAddress() {
        return Address;
    }

    public String getCapacity() {
        return Capacity;
    }

    public String getCity() {
        return City;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLogitude() {
        return Logitude;
    }

    public String getMallID() {
        return MallID;
    }

    public String getMallName() {
        return MallName;
    }

    public String getMobileIcon() {
        return MobileIcon;
    }

    public String getParkingSpace() {
        return ParkingSpace;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public void setLogitude(String logitude) {
        Logitude = logitude;
    }

    public void setMallID(String mallID) {
        MallID = mallID;
    }

    public void setMallName(String mallName) {
        MallName = mallName;
    }

    public void setMobileIcon(String mobileIcon) {
        MobileIcon = mobileIcon;
    }

    public void setParkingSpace(String parkingSpace) {
        ParkingSpace = parkingSpace;
    }
}
