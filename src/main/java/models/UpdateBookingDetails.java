package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties
public class UpdateBookingDetails {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    public void displayData() {
        System.out.println("firstname: " + firstname);
        System.out.println("lastname: " + lastname);
        System.out.println("totalprice: " + totalprice);
        System.out.println("depositpaid: " + depositpaid);
        bookingdates.displayData();
        System.out.println("additionalneeds: " + additionalneeds);
    }
}
