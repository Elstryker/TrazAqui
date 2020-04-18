package TrazAqui;

import java.util.Objects;
import java.lang.Math;

public class GPS {
    private double latitude;
    private double longitude;

    public GPS () {
        this.latitude = 0;
        this.longitude = 0;
    }

    public GPS(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GPS (GPS a) {
        this.longitude = a.getLongitude();
        this.latitude = a.getLatitude();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPS gps = (GPS) o;
        return Double.compare(gps.latitude, latitude) == 0 &&
                Double.compare(gps.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GPS {");
        sb.append("latitude = ").append(latitude);
        sb.append(", longitude = ").append(longitude);
        sb.append('}');
        return sb.toString();
    }

    public double distancia(GPS a) {
        double x,y,ret;
        x = Math.pow(this.latitude - a.getLatitude(),2);
        y = Math.pow(this.longitude - a.getLongitude(),2);
        ret = Math.sqrt(x + y);
        return ret;
    }

    public boolean pertenceAoRaio(GPS a, double raio) {
        boolean ret = false;
        double dist;
        dist = this.distancia(a);
        if(dist <= raio) ret = true;
        return ret;
    }

    public GPS clone() {
        return this;
    }
}
