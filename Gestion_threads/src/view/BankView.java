package view;

import Controlleur.BankControlleur;

import java.util.Scanner;

public class BankView {
    private static BankControlleur controlleur;
    private static void displayMainMenu(){
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("----Menu principal---");
            System.out.println("1.Creeer un compte");
            System.out.println("2.afficher un compte");
            System.out.println("3.Mettre a jour une compte");
            System.out.println("4.Supprimer un compte");
            System.out.println("5.Effectuer une transaction");
            System.out.println("6.afficher une transaction");
            System.out.println("7.Quittez");
            System.out.println("Choissez une option:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    displayMainMenu();
                    break;
                case 2 :
                    displayMainMenu();
                    break;
                case 3 :
                    displayMainMenu();
                    break;
                case 0:
                    System.out.println("Au revoir");
                    System.exit(0);
                default:
                    System.out.println("Option non valide,Veuiller reessayer");
            }

        }
    }
    void createAccount(){};
    void displayAccount(){};
    void updateAccount(){};
    void deleteAccount(){};
    void performTransaction(){};
    void displayTransaction(){};


}
