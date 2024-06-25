package com.booking.service;

import java.util.List;
import java.util.Scanner;

import com.booking.models.*;

public class ValidationService {
    // Buatlah function sesuai dengan kebutuhan
    public static void validateInput(){

    }

    public static String validateReservationId(String resvId, List<Reservation> reservationList) {
        Scanner input = new Scanner(System.in);
        boolean isValidate;
        do {
            isValidate = false;
            for (Reservation reservation : reservationList) {
                if (resvId.equals(reservation.getReservationId())) {
                    isValidate = true;
                    break;
                }
            }
            if (isValidate) {
                System.out.println("Reservation harus bersifat unique\nInput ulang Reservation ID yang bersifat unique : ");
                resvId = input.nextLine();
            }
        } while (isValidate);
        return resvId;
    }

    public static String validateCustomerId(String custId, List<Person> personList){
        Scanner input = new Scanner(System.in);
        boolean isValidate = false;
        do {
            for (Person person : personList) {
                if (person instanceof Customer) {
                    Customer customer = (Customer) person;
                    if (custId.equals(customer.getId())) {
                        isValidate = true;
                        break;
                    }
                }
            }
            if (!isValidate) {
                System.out.println("Customer tidak tersedia\nInput ulang Customer ID yang tersedia : ");
                custId = input.nextLine();
            }
        } while (!isValidate);
        return custId;
    }

    public static String validateEmployeeId(String empId, List<Person> personList) {
        Scanner input = new Scanner(System.in);
        boolean isValidate = false;
        do {
            for (Person person : personList) {
                if (person instanceof Employee) {
                    Employee employee = (Employee) person;
                    if (empId.equals(employee.getId())) {
                        isValidate = true;
                        break;
                    }
                }
            }
            if (!isValidate) {
                System.out.println("Employee tidak tersedia\nInput ulang Employee ID yang tersedia : ");
                empId = input.nextLine();
            }
        } while (!isValidate);
        return empId;
    }

    public static String CheckServiceReservation(String servId, List<Service> pilihService){
        Scanner input = new Scanner(System.in);
        boolean isValidate;
        do {
            isValidate = false;
            for (Service service : pilihService) {
                if (servId.equals(service.getServiceId())) {
                    isValidate = true;
                    break;
                }
            }
            if (isValidate) {
                System.out.println("service sudah di input\nInput ulang Service yang ingin digunakan : ");
                servId = input.nextLine();
            }
        } while (isValidate);
        return servId;
    }
}
