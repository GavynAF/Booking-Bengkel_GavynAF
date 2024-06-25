package com.booking.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    //   workStage (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services,
            String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.reservationPrice = reservationPrice;
        this.workstage = workstage;
    };


    public double calculateReservationPrice(String membershipname){
        double diskon = 0;
        double totalHarga = 0;
        for (Service service : services) {
            totalHarga += service.getPrice();
        }

        if (membershipname.equalsIgnoreCase("gold")) {
            diskon = 0.1;
        } else if(membershipname.equalsIgnoreCase("silver")){
            diskon = 0.05;
        } 
        return totalHarga*(1-diskon);
    }
}
