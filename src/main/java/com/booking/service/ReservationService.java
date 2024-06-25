package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.InputMap;

import com.booking.models.*;
import com.booking.repositories.PersonRepository;

public class ReservationService {
    public static void createReservation(List<Reservation> reservationList, List<Person> personList, List<Service> serviceList){
        Scanner input = new Scanner(System.in);
        boolean condition = false;
        boolean isValidate;
        // double harga = 0;
        List<Service> pilihService = new ArrayList<>();


        System.out.print("Masukkan Reservation Id : ");
        String revId = ValidationService.validateReservationId(input.nextLine(), reservationList);
        PrintService.showAllCustomer(personList);
        System.out.println("Masukkan Customer Id : ");
        String custId = ValidationService.validateCustomerId(input.nextLine(), personList);
        PrintService.showAllEmployee(personList);
        System.out.println("Masukkan Employee Id : ");
        String empId = ValidationService.validateEmployeeId(input.nextLine(), personList);
        PrintService.showAllServices(serviceList);

        do {
            isValidate = false;
            System.out.println("Pilih service yang diinginkan dengan menginput Id Service : ");
            do {
                String servId = input.nextLine();
                Service service = getServiceByServiceId(serviceList, ValidationService.CheckServiceReservation(servId, pilihService));

                if (service != null) {
                    pilihService.add(service);
                    isValidate = true;
                    break; 
                }else {
                    System.out.println("Service tidak ditemukan");
                    System.out.println("input Service ID kembali : ");
                }

            } while (!isValidate);
            System.out.println("Tambah Service? (Y / N)");
            String penambahanService = input.nextLine();
            if (penambahanService.equalsIgnoreCase("N")) {
                condition = true;
            }else if(pilihService.size() >=5){
                System.out.println("List Service sudah terpilih semua"); 
                condition = true;

            }
        } while (!condition);
        //customer
        Customer customerResv = getCustomerByCustomerId(personList, custId);
        //employee
        Employee employeeResv = getEmployeeByEmployeeId(personList, empId);

        Reservation reservation = new Reservation();

        reservation.setReservationId(revId);
        reservation.setCustomer(customerResv);
        reservation.setEmployee(employeeResv);
        reservation.setServices(pilihService);
        reservation.setWorkstage("In Process");
        reservation.setReservationPrice(reservation.calculateReservationPrice(customerResv.getMember().getMembershipName()));

        reservationList.add(reservation);

        System.out.println("Penambahan Suksess!");
        double totalHarga = reservation.getReservationPrice();
        System.out.println("Total Harga : "+ PrintService.FormatCurrency(totalHarga));
    }

    public static Customer getCustomerByCustomerId(List<Person> personList, String custId){
        for (Person person : personList) {
            if (person instanceof Customer) {
                if (person.getId().equals(custId)) {
                    // Customer customer = (Customer) person;
                    return (Customer) person;
                }
            }
        }
        return null;
    }

    public static Employee getEmployeeByEmployeeId(List<Person> personList, String empId){
        for (Person person : personList) {
            if (person instanceof Employee) {
                if (person.getId().equals(empId)) {
                    return (Employee) person;
                }
            }
        }
        return null;
    }

    public static Service getServiceByServiceId(List<Service> serviceList, String servId){
        for(Service service : serviceList){
            if (service.getServiceId().equals(servId)) {
                return service;
            }
        }
        return null;
    }

    public static Reservation getReservationByReservationId(List<Reservation> reservationList, String resvId) {
        for(Reservation reservation : reservationList){
            if (reservation.getReservationId().equals(resvId)) {
                return reservation;
            }
        }
        return null;
    }

    public static void editReservationWorkstage(List<Reservation> reservationList){
        PrintService.showRecentReservation(reservationList);

        Scanner input = new Scanner(System.in);
        System.out.println("Masukan Reservation ID : ");
        String ResvIDedit = input.nextLine();
        System.out.println("Ketik Finish untuk menyelesaikan transaksi, atau Cancel untuk menghapus nya");
        String status = input.nextLine();

        Reservation reservationEdit = getReservationByReservationId(reservationList, ResvIDedit);
        if (status.equalsIgnoreCase("finish")) {
            reservationEdit.setWorkstage("finish");
            System.out.println("transaksi "+ResvIDedit+" Success!!");
        }else if(status.equalsIgnoreCase("cancel")){
            reservationEdit.setWorkstage("cancel");
            System.out.println("transaksi "+ResvIDedit+" Cancelled!!");
        }

    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
