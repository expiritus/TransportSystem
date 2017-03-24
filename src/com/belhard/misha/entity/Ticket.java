package com.belhard.misha.entity;

import com.belhard.misha.customAnnotations.ClassMapping;
import com.belhard.misha.customAnnotations.IgnoreForInsert;

import java.io.Serializable;


@ClassMapping(name = "ticket")
public class Ticket extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;
    private int routId;
    private boolean reservationStatus;
    private String dateReservation;
    private boolean payStatus;
    private String datePay;

    @IgnoreForInsert
    private User user;

    @IgnoreForInsert
    private Route route;


    public Ticket() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (userId != ticket.userId) return false;
        if (routId != ticket.routId) return false;
        if (reservationStatus != ticket.reservationStatus) return false;
        if (payStatus != ticket.payStatus) return false;
        if (dateReservation != null ? !dateReservation.equals(ticket.dateReservation) : ticket.dateReservation != null)
            return false;
        return datePay != null ? datePay.equals(ticket.datePay) : ticket.datePay == null;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + routId;
        result = 31 * result + (reservationStatus ? 1 : 0);
        result = 31 * result + (dateReservation != null ? dateReservation.hashCode() : 0);
        result = 31 * result + (payStatus ? 1 : 0);
        result = 31 * result + (datePay != null ? datePay.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "userId=" + userId +
                ", routId=" + routId +
                ", reservationStatus=" + reservationStatus +
                ", dateReservation='" + dateReservation + '\'' +
                ", payStatus=" + payStatus +
                ", datePay='" + datePay + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoutId() {
        return routId;
    }

    public void setRoutId(int routId) {
        this.routId = routId;
    }

    public boolean isReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(boolean reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public boolean isPayStatus() {
        return payStatus;
    }

    public void setPayStatus(boolean payStatus) {
        this.payStatus = payStatus;
    }

    public String getDatePay() {
        return datePay;
    }

    public void setDatePay(String datePay) {
        this.datePay = datePay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
