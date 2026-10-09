package com.rn.hms;
import java.util.InputMismatchException;
import java.util.Scanner;
/*
 * Class: MainDriver
 * Description: Demonstrates all required laboratory concepts:
 *   1. Classes and Objects instantiation via interactive loop
 *   2. Exception Handling (InputMismatchException, InvalidPersonException, InvalidSalaryException)
 *   3. Calling specialized methods and interface methods
 *   4. Method Overriding via performDuties()
 *   5. Polymorphism using a Person reference and arrays
 */
public class MainDriver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Array of Person references (Demonstrating Polymorphic Collection)
        Person[] hospitalStaff = new Person[4];
        int count = 0;

        System.out.println("=========================================");
        System.out.println("       HOSPITAL MANAGEMENT SYSTEM        ");
        System.out.println("=========================================");

        // ---------------------------------------------------------------------
        // REQUIREMENT 1: CREATE OBJECT OF EACH MAJOR CLASS WITH EXCEPTION HANDLING
        // ---------------------------------------------------------------------
        while (count < hospitalStaff.length) {
            try {
                System.out.println("\nStaff Member " + (count + 1));
                System.out.println("1. Doctor");
                System.out.println("2. Nurse");
                System.out.println("3. Surgeon");
                System.out.println("4. Emergency Doctor");
                System.out.print("Enter type: ");
                int type = scanner.nextInt();

                // Common Person attributes input
                System.out.print("Enter Person ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                System.out.print("Enter Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter Age: ");
                int age = scanner.nextInt();

                System.out.print("Enter Salary: ");
                double salary = scanner.nextDouble();

                // Instantiate specific subclass based on user selection
                switch (type) {
                    case 1: // Single Inheritance: Doctor
                        scanner.nextLine();
                        System.out.print("Enter Specialization: ");
                        String spec = scanner.nextLine();
                        System.out.print("Enter Consultation Fee: ");
                        double fee = scanner.nextDouble();
                        hospitalStaff[count] = new Doctor(id, name, age, salary, spec, fee);
                        break;

                    case 2: // Hierarchical Inheritance: Nurse
                        scanner.nextLine();
                        System.out.print("Enter Department: ");
                        String dept = scanner.nextLine();
                        System.out.print("Enter Shift: ");
                        String shift = scanner.nextLine();
                        hospitalStaff[count] = new Nurse(id, name, age, salary, dept, shift);
                        break;

                    case 3: // Multilevel Inheritance: Surgeon
                        scanner.nextLine();
                        System.out.print("Enter Specialization: ");
                        String sSpec = scanner.nextLine();
                        System.out.print("Enter Consultation Fee: ");
                        double sFee = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Surgery Type: ");
                        String sType = scanner.nextLine();
                        System.out.print("Enter Operating Room: ");
                        String room = scanner.nextLine();
                        hospitalStaff[count] = new Surgeon(id, name, age, salary, sSpec, sFee, sType, room);
                        break;

                    case 4: // Multiple Inheritance via Interfaces: EmergencyDoctor
                        scanner.nextLine();
                        System.out.print("Enter Specialization: ");
                        String eSpec = scanner.nextLine();
                        System.out.print("Enter Consultation Fee: ");
                        double eFee = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Emergency Unit: ");
                        String unit = scanner.nextLine();
                        hospitalStaff[count] = new EmergencyDoctor(id, name, age, salary, eSpec, eFee, unit);
                        break;

                    default:
                        throw new InvalidPersonException("Invalid staff type.");
                }

                // Increment counter only if object was instantiated successfully
                count++;
                System.out.println("Staff member created successfully!");

            } catch (InputMismatchException e) {
                // Catches wrong data type entered in scanner (e.g. text for int/double)
                System.out.println("ERROR: Please enter the correct data type.");
                scanner.nextLine(); // Clear scanner buffer
            } catch (InvalidPersonException e) {
                // Catches custom validation failures (empty strings, invalid ID/age/fees)
                System.out.println("ERROR: " + e.getMessage());
            } catch (InvalidSalaryException e) {
                // Catches negative salary validation
                System.out.println("ERROR: " + e.getMessage());
            } finally {
                // Executes regardless of whether an exception occurred
                System.out.println("Input processing completed.");
            }
        }

        // -----------------------------------------------------------------------------------------------
        // REQUIREMENTS 2, 3, 4 & 6: DISPLAY DETAILS, OVERRIDDEN DUTIES, SPECIALIZED METHODS, & INTERFACES
        // -----------------------------------------------------------------------------------------------
        for (Person person : hospitalStaff) {
            if (person instanceof EmergencyDoctor) {
                System.out.println("\n--- EMERGENCY DOCTOR ---");
                person.displayDetails(); // Requirement 2: Display details
                person.performDuties();  // Requirement 4: Method Overriding
                
                // Downcasting to call specialized and interface methods
                EmergencyDoctor ed = (EmergencyDoctor) person;
                ed.handleEmergency();      // Requirement 3: Specialized method
                ed.prescribeMedication();  // Requirement 6: MedicalProfessional interface
                ed.generateBill();         // Requirement 6: Billable interface
                
            } else if (person instanceof Surgeon) {
                System.out.println("\n--- SURGEON ---");
                person.displayDetails(); // Requirement 2: Display details
                person.performDuties();  // Requirement 4: Method Overriding
                
                // Downcasting to Surgeon
                Surgeon s = (Surgeon) person;
                s.diagnosePatient(); // Inherited from Doctor
                s.performSurgery();  // Requirement 3: Specialized method
                
            } else if (person instanceof Doctor) {
                System.out.println("\n--- DOCTOR ---");
                person.displayDetails(); // Requirement 2: Display details
                person.performDuties();  // Requirement 4: Method Overriding
                
                // Downcasting to Doctor
                ((Doctor) person).diagnosePatient(); // Requirement 3: Specialized method
                
            } else if (person instanceof Nurse) {
                System.out.println("\n--- NURSE ---");
                person.displayDetails(); // Requirement 2: Display details
                person.performDuties();  // Requirement 4: Method Overriding
                
                // Downcasting to Nurse
                ((Nurse) person).assistPatient(); // Requirement 3: Specialized method
            }
        }

        // ---------------------------------------------------------------------
        // REQUIREMENT 5: DEMONSTRATE POLYMORPHISM USING A Person REFERENCE
        // Single Person reference pointing to different subclass objects sequentially
        // ---------------------------------------------------------------------
        System.out.println("\n--- POLYMORPHISM ---");
        Person person;

        // Polymorphism with Doctor object
        person = hospitalStaff[0];
        person.performDuties();

        // Polymorphism with Nurse object
        person = hospitalStaff[1];
        person.performDuties();

        // Polymorphism with Surgeon object
        person = hospitalStaff[2];
        person.performDuties();

        // Polymorphism with EmergencyDoctor object
        person = hospitalStaff[3];
        person.performDuties();

        // ---------------------------------------------------------------------
        // DEMONSTRATE POLYMORPHISM USING ARRAY ITERATION
        // Dynamic method dispatch through a polymorphic collection loop
        // ---------------------------------------------------------------------
        System.out.println("\n--- POLYMORPHISM USING ARRAY ---");
        for (Person p : hospitalStaff) {
            p.performDuties();
        }

        System.out.println("\n=========================================");
        System.out.println("          END OF DEMONSTRATION           ");
        System.out.println("=========================================");

        scanner.close();
    }
}