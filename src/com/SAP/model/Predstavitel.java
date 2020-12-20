package com.SAP.model;

import com.SAP.exceptions.NoSuchIDException;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Predstavitel implements Serializable{
private double zaplata;
private final String id;
private final String password;

    public String getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    public Predstavitel(String id, String password,double zaplata){
        this.id = id;
        this.password = password;
        this.zaplata=zaplata;
    }
    public void setZaplata(double zaplata,Otchet otchet) {
        otchet.setTotalPayment(otchet.getTotalPayment() - this.zaplata + zaplata);
        this.zaplata = zaplata;
    }

    public Clients addClients(List<Clients> arr){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter client's name: ");
        String name = scan.nextLine();
        for(int i = 0; i<arr.size();i++)
        { if (name.equalsIgnoreCase(arr.get(i).getName())){
            System.out.println("Client exists\nEnter new name");
            name=scan.nextLine();
            i=0;
        } }
        System.out.println("Enter client's email: ");
        String email = scan.nextLine();
        return new Clients(name, email);
    }
//eto tuk trqbwa da vkaram programata za email
    public Catalog sell(List<Products> arr, List<Clients> cl,Otchet otchet,List<Admin> admins) throws NoSuchIDException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter product ID");
        String id = scan.nextLine();
        boolean existsproduct = false;
        boolean existsclient = false;
        double price;
        for(Products p : arr){
            if(id.equalsIgnoreCase(p.getId()))
            {
                System.out.println("To whom?");
                String name = scan.nextLine();
                existsproduct = true;
                for(Clients c : cl){
                    if(name.equalsIgnoreCase(c.getName())){
                        System.out.println("how much?");
                        int sell = scan.nextInt();
                        p.sell(sell);
                        if(sell>2) {
                            price = p.getPrice()*0.9*sell;
                        }
                        else {
                            price = p.getPrice()*sell;

                        }
                        otchet.setTotalPrihodi(otchet.getTotalPrihodi()+price);
                        existsclient = true;
                        System.out.println("Enter month");
                        String month = scan.next();
                        System.out.println("Enter date");
                        String date = scan.next();
                        int i = Integer.parseInt(month+date);
                        if(p.getQuantity()<5){
                            System.out.println("Quantity is bellow 5\nEnter your email:");
                            String email = scan.next();
                            System.out.println("Enter email password");
                            String password = scan.next();
                            for(Admin a : admins){
                                SendMail.sendMail(email,password,a.getEmail(),p);
                            }
                        }
                        return new Catalog(this.id,id, price, name,c.getEmail(),i,p.getName(),sell);
                    }
                }if(!existsclient)
                throw new NoSuchIDException("No such client!");
            }
        }if(!existsproduct)
            throw new NoSuchIDException("No such product!");
        return null;
    }
    public void deleteClient(List<Clients> arr) throws NoSuchIDException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter client's name");
        String name = scan.nextLine();
        boolean existsclient = false;
        for(Clients p : arr){
            if(name.equalsIgnoreCase(p.getName()))
            {
                arr.remove(p);
                existsclient = true;
                System.out.println("Client deleted");
                break;
            }
        }
        if(!existsclient)
            throw new NoSuchIDException("No such client!");
    }

    public double getZaplata() {
        return zaplata;
    }

    public void viewClients(List<Clients> arr)
    {
        for(Clients p : arr){
            System.out.println(p.toString());
        }
    }

    public void changeMail(List<Clients> arr) throws NoSuchIDException {
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        boolean existsclient = false;
        for(Clients p : arr){
            if(name.equalsIgnoreCase(p.getName()))
            {
                p.setEmail(scan.nextLine());
                existsclient = true;
                break;
            }
        }
        if(!existsclient)
            throw new NoSuchIDException("No such client!");
    }
    @Override
    public String toString() {
        return "Predstavitel{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", zaplata=" + zaplata +
                '}';
    }
}
