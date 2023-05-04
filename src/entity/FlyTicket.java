package entity;

import java.time.LocalDateTime;

public class FlyTicket {
    private String departure;
    private String destination;
    private LocalDateTime departTime;
    private LocalDateTime arrivalTime;
    private LocalDateTime bookingDay;
    private double price;
    private String passengerName;
    private String luggageInfo;
    private String ticketClass;
    private FlyTicket (){

    }
    public static class FlyticketBuilder{

    }
}
