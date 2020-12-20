package com.SAP.model;
import com.SAP.exceptions.NoSuchIDException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Admin implements Serializable {

    private final String id;
    private final String password;
    private String email;


    public String getId() {
            return id;
        }
        public String getPassword() {
            return password;
        }


    public Admin(String id, String password,String email){
        this.id = id;
        this.email = email;
        this.password = password;
    }

   public Products addProduct(List<Products> arr,Otchet otchet){
       Scanner scan = new Scanner(System.in);
       String id;
            System.out.println("Enter ID: ");
           id=scan.nextLine();
       for(int i = 0; i<arr.size();i++)
       { if (id.equalsIgnoreCase(arr.get(i).getId())){
           System.out.println("Id exists\nEnter new id");
           id=scan.next();
           i=0;
       } }
       System.out.println("Enter product name: ");
       String name=scan.nextLine();
       System.out.println("Enter quantity of product: ");
       int quantity = scan.nextInt();
       System.out.println("Enter price of Product: ");
       double price = scan.nextDouble();
        return new Products(id, name, quantity, price,otchet);

   }
    public void addQuantity(List<Products> arr,Otchet otchet) throws NoSuchIDException {
        Scanner scan = new Scanner(System.in);
        System.out.println("id: ");
        String id = scan.next();
        boolean exists = false;
        for (Products p : arr) {
        if (id.equalsIgnoreCase(p.getId())){
            System.out.println("Choose how much to add");
                int add = scan.nextInt();
                p.setQuantity(p.getQuantity() + add);
                otchet.setTotalMoneySpent(otchet.getTotalMoneySpent()+(p.getPrice()*0.7)*add);
                exists = true;
                break;
            }
        }
        if(!exists){
            throw new NoSuchIDException("No such product!");
        }
    }

    public void changePrice(List<Products> arr) throws NoSuchIDException {
        Scanner scan = new Scanner(System.in);
        System.out.println("ID :");
        String id = scan.next();
        boolean exists = false;
        for(Products p : arr){
            if(id.equalsIgnoreCase(p.getId()))
            {
                System.out.println("Set new price");
                double price = scan.nextDouble();
                p.setPrice(price);
                exists = true;
                break;
            }

        }
        if(!exists){
            throw new NoSuchIDException("No such product!");
        }
    }

    public void viewProducts(List<Products> arr)
    {
        for(Products p : arr){
            System.out.println(p.toString());
    }
    }

    public void deleteProduct(List<Products> arr) throws NoSuchIDException {
        Scanner scan = new Scanner(System.in);
        System.out.println("enter id: ");
      String id = scan.next();
      boolean exists = false;
        for(Products p : arr){
            if(id.equalsIgnoreCase(p.getId()))
            {
                arr.remove(p);
                exists= true;
                break;
            }
        }
        if(!exists){
            throw new NoSuchIDException("No such Predstavitel");
        }
    }
public Predstavitel addPredstavitel(List<Predstavitel> arr,Otchet otchet){
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter ID: ");
    String id=scan.next();
    for(int i = 0; i<arr.size();i++)
    { if (id.equalsIgnoreCase(arr.get(i).getId())){
        System.out.println("Id exists\nEnter new id");
        id=scan.next();
        i=0;
    } }
    System.out.println("Enter password: ");
    String password=scan.next();
    System.out.println("Enter zaplata: ");
    double zaplata = scan.nextDouble();
    otchet.setTotalPayment(otchet.getTotalPayment() + zaplata);
    return new Predstavitel(id, password, zaplata);
}
    public Admin addAdmin(List<Admin> arr){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter ID: ");
        String id=scan.next();
        for(int i = 0; i<arr.size();i++)
        { if (id.equalsIgnoreCase(arr.get(i).getId())){
            System.out.println("Id exists\nEnter new id");
            id=scan.next();
            i=0;
        } }
        System.out.println("Enter password: ");
        String password=scan.next();
        System.out.println("Enter email: ");
        String email = scan.nextLine();
        return new Admin(id, password, email);
    }


    public String getEmail() {
        return email;
    }

    public void deletePredstavitel(List<Predstavitel> arr, Otchet otchet) throws NoSuchIDException {
        Scanner scan = new Scanner(System.in);
        System.out.println("ID");
        String id = scan.next();
        boolean exists = false;
        for(Predstavitel p : arr){
            if(id.equalsIgnoreCase(p.getId()))
            {

                  otchet.setTotalPayment(otchet.getTotalPayment() - p.getZaplata());
                  arr.remove(p);
                  exists = true;

              break;
            }
        }
        if(!exists){
            throw new NoSuchIDException("No such predstavitel!");
        }
    }

    public void changeZaplata(List<Predstavitel> arr, Otchet otchet) throws NoSuchIDException {
        Scanner scan = new Scanner(System.in);
        String id = scan.next();
        boolean exists = false;
        for(Predstavitel p : arr){
            if(id.equalsIgnoreCase(p.getId()))
            {
                double zaplata = scan.nextDouble();
                p.setZaplata(zaplata, otchet);
                exists = true;
                break;
            }
        }
        if(!exists)
            throw new NoSuchIDException("No such predstavitel!");
    }
    public void getOtchetZaVsichki(Otchet otchet){
        System.out.println("Total: " + (otchet.getTotalPrihodi() - (otchet.getTotalMoneySpent() + otchet.getTotalPayment())));
    }


    public void getOtchetZa1(List<Predstavitel> arr, List<Catalog> catalogs) throws NoSuchIDException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter ID");
            String id = scan.next();
        boolean predstavitelexists = false;
        boolean catalogsexists = false;
        for(Predstavitel p : arr){
            if(id.equalsIgnoreCase(p.getId()))
            { predstavitelexists= true;
               for(Catalog c : catalogs)
               {
                   if (id.equalsIgnoreCase(c.getId())){
                       System.out.println(c);
                       catalogsexists = true;
                   }
               }
               if (!catalogsexists)
                   System.out.println("Predstavitel hasn't sold anything!");
            }
        }
        if(!predstavitelexists)
            throw new NoSuchIDException("predstavitel!");
    }

public void viewall(List<Catalog> catalogs) throws NoSuchIDException {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter starting month");
    String month = scan.next();
    System.out.println("Enter starting date");
    String date = scan.next();
    int i = Integer.parseInt(month + date);
    System.out.println("Enter ending month");
    String month2 = scan.next();
    System.out.println("Enter ending date");
    String date2 = scan.next();
    int p = Integer.parseInt(month2 + date2);
    boolean notEmpty = false;
    for (Catalog c : catalogs) {
        if (c.getDate() >= i && c.getDate() <= p) {
            System.out.println(c);
            notEmpty = true;
        }
    }
    if (!notEmpty)
        throw new NoSuchIDException("Nothing to show!");
}
}
