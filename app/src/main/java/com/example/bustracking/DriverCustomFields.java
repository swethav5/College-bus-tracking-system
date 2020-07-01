package com.example.bustracking;

public class DriverCustomFields {
    String driveremail,driverpassword,driverusername,Route;

    public DriverCustomFields(String driveremail, String driverpassword, String driverusername,String Route) {
        this.driveremail = driveremail;
        this.driverpassword = driverpassword;
        this.driverusername = driverusername;
        this.Route=Route;
    }

    public String getDriveremail() {
        return driveremail;
    }

    public void setDriveremail(String driveremail) {
        this.driveremail = driveremail;
    }

    public String getDriverpassword() {
        return driverpassword;
    }

    public void setDriverpassword(String driverpassword) {
        this.driverpassword = driverpassword;
    }

    public String getDriverusername() {
        return driverusername;
    }

    public void setDriverusername(String driverusername) {
        this.driverusername = driverusername;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String Route) {
        this.Route = Route;
    }

}
