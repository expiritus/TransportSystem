package com.belhard.misha.entity;

import com.belhard.misha.customAnnotations.ClassMapping;
import com.belhard.misha.customAnnotations.FieldMapping;
import com.belhard.misha.customAnnotations.IgnoreForInsert;

import java.io.Serializable;


@ClassMapping(name = "ticket")
public class Ticket extends AbstractEntity implements Serializable {

    @IgnoreForInsert
    private static final long serialVersionUID = 1L;

    @FieldMapping(name = "user_id")
    private int userId;

    @FieldMapping(name = "route_id")
    private int routeId;

    @FieldMapping(name = "reservation_status")
    private boolean reservationStatus;

    @FieldMapping(name = "date_reservation")
    private String dateReservation;

    @FieldMapping(name = "pay_status")
    private boolean payStatus;

    @FieldMapping(name = "date_pay")
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
        if (routeId != ticket.routeId) return false;
        if (reservationStatus != ticket.reservationStatus) return false;
        if (payStatus != ticket.payStatus) return false;
        if (dateReservation != null ? !dateReservation.equals(ticket.dateReservation) : ticket.dateReservation != null)
            return false;
        return datePay != null ? datePay.equals(ticket.datePay) : ticket.datePay == null;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + routeId;
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
                ", routeId=" + routeId +
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

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
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
