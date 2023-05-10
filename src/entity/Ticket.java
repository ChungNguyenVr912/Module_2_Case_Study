package entity;

import services.FlightService;
import utils.MyDateTime;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket implements Serializable {
    private static long ID = 1;
    private String ticketCode;
    private String flightCode;
    private LocalDateTime bookingDay;
    private double price;
    private String passengerName;
    private LocalDate passengerDoB;
    private String passengerGender;
    private long passengerPhoneNumber;
    private String seatCode;
    private String baggageInfo;
    private String ticketClass;

    private Ticket(TicketBuilder builder) {
        this.ticketCode = "T" + MyDateTime.toDayAndMonth(builder.bookingDay)+ID;
        this.flightCode = builder.flightCode;
        this.bookingDay = builder.bookingDay;
        this.price = builder.price;
        this.passengerName = builder.passengerName;
        this.seatCode = builder.seatCode;
        this.baggageInfo = builder.baggageInfo;
        this.ticketClass = builder.ticketClass;
        this.passengerDoB = builder.passengerDoB;
        this.passengerGender = builder.passengerGender;
        this.passengerPhoneNumber = builder.passengerPhoneNumber;
        ID++;
    }

    public static class TicketBuilder {
        private String flightCode;
        private LocalDateTime bookingDay;
        private double price;
        private String passengerName;
        private LocalDate passengerDoB;
        private String passengerGender;
        private long passengerPhoneNumber;
        private String seatCode;
        private String baggageInfo;
        private String ticketClass;

        public TicketBuilder setPassengerDoB(LocalDate passengerDoB) {
            this.passengerDoB = passengerDoB;
            return this;
        }

        public TicketBuilder setPassengerGender(String passengerGender) {
            this.passengerGender = passengerGender;
            return this;
        }

        public TicketBuilder setPassengerPhoneNumber(long passengerPhoneNumber) {
            this.passengerPhoneNumber = passengerPhoneNumber;
            return this;
        }

        public TicketBuilder setFlightCode(String flightCode) {
            this.flightCode = flightCode;
            return this;
        }

        public TicketBuilder setBookingDay(LocalDateTime bookingDay) {
            this.bookingDay = bookingDay;
            return this;
        }

        public TicketBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public TicketBuilder setPassengerName(String passengerName) {
            this.passengerName = passengerName;
            return this;
        }

        public TicketBuilder setSeatCode(String seatCode) {
            this.seatCode = seatCode;
            return this;
        }

        public TicketBuilder setBaggageInfo(String baggageInfo) {
            this.baggageInfo = baggageInfo;
            return this;
        }

        public TicketBuilder setTicketClass(String ticketClass) {
            this.ticketClass = ticketClass;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public LocalDateTime getBookingDay() {
        return bookingDay;
    }

    public void setBookingDay(LocalDateTime bookingDay) {
        this.bookingDay = bookingDay;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public LocalDate getPassengerDoB() {
        return passengerDoB;
    }

    public void setPassengerDoB(LocalDate passengerDoB) {
        this.passengerDoB = passengerDoB;
    }

    public String getPassengerGender() {
        return passengerGender;
    }

    public void setPassengerGender(String passengerGender) {
        this.passengerGender = passengerGender;
    }

    public long getPassengerPhoneNumber() {
        return passengerPhoneNumber;
    }

    public void setPassengerPhoneNumber(long passengerPhoneNumber) {
        this.passengerPhoneNumber = passengerPhoneNumber;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getBaggageInfo() {
        return baggageInfo;
    }

    public void setBaggageInfo(String baggageInfo) {
        this.baggageInfo = baggageInfo;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    @Override
    public String toString() {
        return "YOUR TICKET:\n" +
                "Flight: " + FlightService.getFlightInfoByFlightCode(flightCode) + '\n'
                + String.format("%-15s%s\n", "BookingDay:", MyDateTime.toDayMonthYear(bookingDay))
                + String.format("%-15s%f\n", "Price:", price)
                + String.format("%-15s%s\n", "PassengerName:", passengerName)
                + String.format("%-15s", "Day of birth:") + passengerDoB + '\n'
                + String.format("%-15s%s\n", "Gender:", passengerGender)
                + String.format("%-15s%d\n", "PhoneNumber:", passengerPhoneNumber)
                + String.format("%-15s%s\n", "SeatCode:", seatCode)
                + String.format("%-15s%s\n", "Baggage info:", baggageInfo)
                + String.format("%-15s%s", "Ticket:", ticketClass);
    }
}
