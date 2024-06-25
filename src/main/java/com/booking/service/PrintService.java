package com.booking.service;

import java.util.List;
import java.util.stream.Collectors;

import com.booking.models.*;
import com.booking.repositories.PersonRepository;

public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public static String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    public static void showAllServices(List<Service> serviceList){
        int num = 1;
        // Bisa disesuaikan kembali
        System.out.println("Service");
        System.out.printf("| %-4s | %-10s | %-20s | %-15s |\n",
                "No.", "ID", "Nama Service", "Harga");
        System.out.println("+============================================================+");
        for (Service service : serviceList) {
            System.out.printf("| %-4s | %-10s | %-20s | %-15s |\n",
                num, service.getServiceId(), service.getServiceName(), FormatCurrency(service.getPrice()));
                num++;
        }
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public static void showRecentReservation(List<Reservation> reservationList){
        int num = 1;
        System.out.println("Reservation");
        System.out.printf("| %-4s | %-10s | %-16s | %-35s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+=============================================================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-10s | %-16s | %-35s | %-15s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), FormatCurrency(reservation.getReservationPrice()), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
    }

    public static void showAllCustomer(List<Person> personList){
        int num = 1;
        System.out.println("Customer");
        System.out.printf("| %-4s | %-10s | %-11s | %-15s | %-15s | %-15s |\n",
                "No.", "ID", "Nama ", "Alamat", "Membership", "Uang");
        System.out.println("+=======================================================================================+");
        for (Person person : personList) {
            if (person instanceof Customer) {
                Customer customer = (Customer) person;
                System.out.printf("| %-4s | %-10s | %-11s | %-15s | %-15s | %-15s |\n",
                    num, customer.getId(), customer.getName(), customer.getAddress(), customer.getMember().getMembershipName(), FormatCurrency(customer.getWallet()));
                    num++;
            }
        }
    }

    public static void showAllEmployee(List<Person> personList){
        int num = 1;
        System.out.println("Employee");
        System.out.printf("| %-4s | %-10s | %-11s | %-15s | %-15s |\n",
                "No.", "ID", "Nama ", "Alamat", "Pengalaman");
        System.out.println("+=====================================================================+");
        for (Person person : personList) {
            if (person instanceof Employee) {
                Employee employee = (Employee) person;
                System.out.printf("| %-4s | %-10s | %-11s | %-15s | %-15s |\n",
                    num, employee.getId(), employee.getName(), employee.getAddress(), employee.getExperience());
                    num++;
            }
        }
    }

    public static void showHistoryReservation(List<Reservation> reservationList){
        int num = 1, totalKeuntungan = 0;
        System.out.println("History Reservation");
        System.out.printf("| %-4s | %-10s | %-16s | %-35s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+=============================================================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Finish")) {
                System.out.printf("| %-4s | %-10s | %-16s | %-35s | %-15s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), FormatCurrency(reservation.getReservationPrice()), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
                totalKeuntungan += reservation.getReservationPrice();
            }
        }
        System.out.println("+=============================================================================================================================+");
        System.out.printf("| %92s | %28s |\n", "Total Keuntungan", FormatCurrency(totalKeuntungan));
        System.out.println("+=============================================================================================================================+");
    }

    public static String FormatCurrency(double harga) {
        StringBuilder format = new StringBuilder();

        String angka = String.format("%.2f", harga).replace('.',',');

        int z = 0,pangjang;
        pangjang = angka.length();
        format.append("Rp");
        for (int i = (pangjang-3)-1; i >= 0 ; i--) {
            boolean koma=true;
            format.append(angka.charAt(z));
            if (i%3==0&&i!=0) {
                format.append(".");
            }
            z++;
        }
        for (int j = pangjang-1; j > (pangjang-3)-1; j--) {
            format.append(angka.charAt(z));
            z++;
        }
        return format.toString();
    }
}
