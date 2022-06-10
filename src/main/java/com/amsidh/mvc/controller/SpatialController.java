package com.amsidh.mvc.controller;

import com.amsidh.mvc.enums.DistanceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spatial")
@Slf4j
public class SpatialController {

    @GetMapping("/meters")
    public Double distanceBetweenTwoPointsInMeter(@RequestParam("latitude1") double lat1, @RequestParam("longitude1") double long1, @RequestParam("latitude2") double lat2, @RequestParam("longitude2") double long2) {
        double dist = org.apache.lucene.util.SloppyMath.haversinMeters(lat1, long1, lat2, long2);
        return dist;
    }

    @GetMapping("/kms")
    public Double distanceBetweenTwoPointsInKms(@RequestParam("latitude1") double lat1, @RequestParam("longitude1") double long1, @RequestParam("latitude2") double lat2, @RequestParam("longitude2") double long2) {
        double dist = org.apache.lucene.util.SloppyMath.haversinKilometers(lat1, long1, lat2, long2);
        return dist;
    }

    @GetMapping("/java")
    public Double distanceBetweenTwoPointsInKmsJava(@RequestParam("latitude1") double lat1, @RequestParam("longitude1") double long1, @RequestParam("latitude2") double lat2, @RequestParam("longitude2") double long2, @RequestParam(value = "distanceUnit", defaultValue = "KILOMETER") DistanceUnit distanceUnit) {
        double dist = distance(lat1, long1, lat2, long2, distanceUnit);
        return dist;
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, DistanceUnit distanceUnit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            switch (distanceUnit) {
                case METER:
                    dist = dist * 1.609344 * 1000;
                    break;
                case KILOMETER:
                    dist = dist * 1.609344;
                    break;
                case NAUTICALMILES:
                    dist = dist * 0.8684;
                    break;
                default:
                    return 0.0;

            }
            return (dist);
        }
    }
}
