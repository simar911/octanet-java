import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
  private String id;
  private String password;
  private double balance;

  public BankAccount(String id, String password) {
    this.id = id;
    this.password = password;
    this.balance = 0;
  }

  public boolean login(String id, String password) {
    return this.id.equals(id) && this.password.equals(password);
  }

  public void withdraw(double amount) {
    if (balance >= amount) {
      balance -= amount;
      System.out.println("Withdrawal successful. New balance: " + balance);
    } else {
      System.out.println("Insufficient balance.");
    }
  }

  public void deposit(double amount) {
    balance += amount;
    System.out.println("Deposit successful. New balance: " + balance);
  }

  public void requestBalance() {
    System.out.println("Your current balance is: " + balance);
  }
}

public class Main {
  public static void main(String[] args) {
    ArrayList<BankAccount> accounts = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    int choice;

    while (true) {
      System.out.println("1) Create a bank account");
      System.out.println("2) Login");
      System.out.println("3) Quit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();

      if (choice == 1) {
        System.out.print("Enter your user id: ");
        String id = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        accounts.add(new BankAccount(id, password));
        System.out.println("Bank account created successfully.");
      } else if (choice == 2) {
        System.out.print("Enter your user id: ");
        String id = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        boolean loggedIn = false;
        for (BankAccount account : accounts) {
          if (account.login(id, password)) {
            loggedIn = true;
            while (loggedIn) {
              System.out.println("1) Withdraw money");
              System.out.println("2) Deposit money");
              System.out.println("3) Request balance");
              System.out.println("4) Logout");
              System.out.print("Enter your choice: ");
              int accountChoice = scanner.nextInt();

              if (accountChoice == 1) {
                System.out.print("Enter the amount to withdraw: ");
                double amount = scanner.nextDouble();
                account.withdraw(amount);
              } else if (accountChoice == 2) {
                System.out.print("Enter the amount to deposit: ");
                double amount = scanner.nextDouble();
                account.deposit(amount);
              } else if (accountChoice == 3) {
                account.requestBalance();
              } else if (accountChoice == 4) {
                loggedIn = false;
              } else {
                System.out.println("Invalid choice.");
              }
            }
            break;
          }
        }
        if (!loggedIn) {
          System.out.println("Invalid user id or password.");
        }
      } else if (choice == 3) {
        break;
      } else {
        System.out.println("Invalid choice.");
      }
    }

    scanner.close();
  }
}
