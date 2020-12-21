package com.SAP.main;
import com.SAP.exceptions.NoSuchIDException;
import com.SAP.model.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;
public class Main {

    static Scanner scan = new Scanner(System.in);
    static String choice;
    static List<Admin> admins;
    static List<Catalog> catalogs;
    static List<Clients> clients;
    static List<Predstavitel> predstaviteli;
    static List<Products> products;
    static int i;
   static Otchet otchet;
    public static void main(String[] args){
      try {
            load();
        } catch (IOException e) {
            System.out.println("Couldn't load database!!!!");
        } catch (ClassNotFoundException e) {
            System.out.println("Program error");
        }
        String id;
        String password;
        for (i = 0; i < 3; i++) {
            System.out.println("Enter ID");
            id = scan.nextLine();
            System.out.println("Enter password");
            password = scan.nextLine();
            for (Admin a : admins) {
                if (id.equalsIgnoreCase(a.getId()) && password.equalsIgnoreCase(a.getPassword())) {
                    adminmenu(a);
                }
            }
            for (Predstavitel p : predstaviteli) {
                if (id.equalsIgnoreCase(p.getId()) && password.equalsIgnoreCase(p.getPassword())) {
                    predstavitel(p);
                }
            }if(i<2)
            System.out.println("Try again");
        }if (i == 3) {
            System.out.println("No more attempts");
        }
        else
            System.out.println("Goodbye");
        try {
            save();
        } catch (IOException e) {
            System.out.println("Couldn't save data!!!!");
        }

    }

    static void adminmenu(Admin a){
        do {
            System.out.println("Izberete opciq: ");
            System.out.println("1:Dobavi product\n2:Dobavi kolichestvo\n3:Promeni cena\n" +
                    "4:Vij produkti\n5:Iztrii product\n6:Vij predstaviteli\n7:Dobavi predstavitel\n8:Iztrii predstavitel\n9:promeni zaplata" +
                    "\n10:otchet na smetki\n11:otchet za predstavitel\n12:za daden period\n13:dobavi administrator\n0: logout");
                choice = scan.nextLine();


            switch (choice) {
                case "1" -> products.add(a.addProduct(products,otchet));
                case "2" -> {
                    try {
                        a.addQuantity(products,otchet);
                    } catch (NoSuchIDException e) {
                        System.out.println("A problem occurred:" + e);
                    }
                }
                case "3" -> {
                    try {
                        a.changePrice(products);
                    } catch (NoSuchIDException e) {
                        System.out.println("A problem occurred:" + e);
                    }
                }
                case "4" -> a.viewProducts(products);
                case "5" -> {
                    try {
                        a.deleteProduct(products);
                    } catch (NoSuchIDException e) {
                        System.out.println("A problem occurred:" + e);
                    }
                }case "6" -> a.viewAllPred(predstaviteli);
                case "7" -> predstaviteli.add(a.addPredstavitel(predstaviteli,otchet));
                case "8" -> {
                    try {
                        a.deletePredstavitel(predstaviteli,otchet);
                    } catch (NoSuchIDException e) {
                        System.out.println("A problem occurred:" + e);
                    }
                }
                case "9" -> {
                    try {
                        a.changeZaplata(predstaviteli,otchet);
                    } catch (NoSuchIDException e) {
                        System.out.println("A problem occurred:" + e);
                    }
                }
                case "10" -> a.getOtchetZaVsichki(otchet);
                case "11" -> {
                    try {
                        a.getOtchetZa1(predstaviteli, catalogs);
                    } catch (NoSuchIDException e) {
                        System.out.println("A problem occurred:" + e);
                    }
                }
                case "12" -> {
                    try {
                        a.viewall(catalogs);
                    } catch (NoSuchIDException e) {
                        System.out.println("A problem occurred:" + e);
                    }
                }
                case "13" -> admins.add(a.addAdmin(admins));
                case "0" -> i = 4;
                default -> System.out.println("wrong choice");
            }

        } while (!choice.equals("0"));
    }

    static void predstavitel(Predstavitel p){
        do{
            System.out.println("Izberete opciq:\n1 Dobavi klient\n2 registrirai prodajba\n3" +
                    " iztrii klient\n4 promeni email na klient\n5 Vij klienti\n6 vij produkti\n0 logout");
            choice = scan.nextLine();
            switch (choice) {
                case "1" -> clients.add(p.addClients(clients));
                case "2" -> {
                    try {
                        catalogs.add(p.sell(products, clients,otchet,admins));
                    } catch (NoSuchIDException e) {
                        System.out.println("A problem occurred:" + e);
                    }
                }
                case "3" -> {
                    try {
                        p.deleteClient(clients);
                    } catch (NoSuchIDException e) {
                        System.out.println("A problem occurred:" + e);
                    }
                }
                case "4" -> {
                    try {
                        p.changeMail(clients);
                    } catch (NoSuchIDException e) {
                        System.out.println("A problem occurred:" + e);
                    }
                }
                case "5" -> p.viewClients(clients);
                case "6" -> p.viewProducts(products);
                case "0" -> i = 4;
                default -> System.out.println("wrong choice");
            }
        }while (!choice.equals("0"));
    }

    static void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Admins"));
        admins = (List<Admin>) ois.readObject();
        ois.close();
        ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("Catalogs"));
        catalogs = (List<Catalog>) ois1.readObject();
        ois1.close();
        ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream("clients"));
        clients = (List<Clients>) ois2.readObject();
        ois2.close();
        ObjectInputStream ois3 = new ObjectInputStream(new FileInputStream("Predstaviteli"));
        predstaviteli = (List<Predstavitel>) ois3.readObject();
        ois3.close();
        ObjectInputStream ois4 = new ObjectInputStream(new FileInputStream("Products"));
        products = (List<Products>) ois4.readObject();
        ois4.close();
        ObjectInputStream ois5 = new ObjectInputStream(new FileInputStream("Otchet"));
        otchet = (Otchet) ois5.readObject();
        ois5.close();
    }

    static void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Admins"));
        oos.writeObject(admins);
        oos.close();
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("Predstaviteli"));
        oos1.writeObject(predstaviteli);
        oos1.close();
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("Catalogs"));
        oos2.writeObject(catalogs);
        oos2.close();
        ObjectOutputStream oos3 = new ObjectOutputStream(new FileOutputStream("Products"));
        oos3.writeObject(products);
        oos3.close();
        ObjectOutputStream oos4 = new ObjectOutputStream(new FileOutputStream("Clients"));
        oos4.writeObject(clients);
        oos4.close();
        ObjectOutputStream oos5 = new ObjectOutputStream(new FileOutputStream("Otchet"));
        oos5.writeObject(otchet);
        oos5.close();
    }
}
