import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Customer {
    String name;
    String tier;

    public Customer(String name, String tier) {
        this.name = name;
        this.tier = tier;
    }
}

public class QueueManagementSystem {
    public static void main(String[] args) {
        Queue<Customer> queue = new LinkedList<>();
        Queue<Customer> tempQ = new LinkedList<>();
        Queue<Customer> tempQ2 = new LinkedList<>();
        Queue<Customer> platinumQueue = new LinkedList<>();
        

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("============ MENU ============");
            System.out.println("1) Register new customer");
            System.out.println("2) Call customer for service");
            System.out.println("3) Check remaining customers in the queue");
            System.out.println("4) Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                	do {
                        System.out.print("Enter customer name: ");
                        String name = scanner.next();

                        System.out.print("Customer tier:\nS - Silver\nG - Gold\nP - Platinum \nPlease enter the customer tier: ");
                        String tier = scanner.next();

                        Customer customer = new Customer(name, tier);

                        if (tier.equalsIgnoreCase("G")) {
                            // For Gold customers, check the list to be skipped to first 5 customers
                        	for(int i = queue.size(); i >5 && !queue.isEmpty(); i--) {
                        	
                        		tempQ.add(queue.poll()); //add the top of 5 people into first temporary queue

                        	}

                        	tempQ2.addAll(queue); //add the remaining queue which is the bottom list of queue in another temporary queue (avoid any errors)
                        	queue.clear(); //clear the main queue to avoid redundant list name
                        	tempQ.add(customer); //add the Gold tier of customer to the first temporary queue
                        	queue.addAll(tempQ); //adding back the first temp queue to the main queue, included the gold tier customer on bottom of the list
                        	queue.addAll(tempQ2); //adding back another temp queue to the main queue
                        	tempQ.clear();
                        	tempQ2.clear(); //clear the list
                        }

                     // Add the customer to the appropriate queue
                         else if (tier.equalsIgnoreCase("P")) {
                            platinumQueue.add(customer);
                        } else if (tier.equalsIgnoreCase("S")) {
                            queue.add(customer);
                        } else {
                            System.out.println("Wrong input !!");
                            continue; // Continue the loop to prompt the user again
                        }

                        System.out.print("Register another person? (Y/N)");
                    } while (scanner.next().equalsIgnoreCase("Y"));

                    System.out.println("Customer registration complete.");
                    break;

                case 2:
                	if (!platinumQueue.isEmpty()) {
                        Customer nextPlatinumCustomer = platinumQueue.poll();
                        System.out.println("Calling Platinum customer for service: " + nextPlatinumCustomer.name);
                    } else if (!queue.isEmpty()) {
                        Customer nextCustomer = queue.poll();
                        System.out.println("Calling customer for service: " + nextCustomer.name);
                    } else {
                        System.out.println("No customers in the queue.");
                    }
                    break;

                case 3:
                	System.out.println("Remaining customers in the queue:");
                    for (Customer c : platinumQueue) {
                        System.out.println(c.name + " - " + c.tier);
                    }
                    for (Customer c : queue) {
                        System.out.println(c.name + " - " + c.tier);
                    }
                    break;

                case 4:
                    System.out.println("Exiting Queue Management System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 4);

        scanner.close();
    }
}
