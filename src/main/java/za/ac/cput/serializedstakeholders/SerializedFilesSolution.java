/**
 * SerializedFilesSolution.java
 * version 1.0
 * This Program reads from .ser file and writes objects to customer and supplier files
 * @author Guy De La Cruz (218336969)
 * Date: 02 June 2021
 */

package za.ac.cput.serializedstakeholders;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class SerializedFilesSolution {
   private ObjectInputStream input;
   ArrayList<Customer> cust = new ArrayList <Customer>();
   ArrayList<Supplier> supp = new ArrayList <Supplier>();
  
// Opens .ser file for objects to be read from the file  
    public void openFile() {
        try{
            input = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            System.out.println("...Ser file opened for reading..."); 
        
    }   catch (IOException ioe) {
            System.out.println("Error opening the file..." + ioe.getMessage());
        }
  }
 // Closes the .ser file
    public void closeFile(){
        try{
            input.close();
        } catch (IOException ex) {
            System.out.println("File coud not close..." + ex.getMessage());
        }
    }
 // Reads customer objects from the file and adds them to two separate array lists 
    public void readFromFile(){
        try{ 
        Object obj = input.readObject();
        String s = input.readLine();
                
        while(s!=null){
            if(obj instanceof Customer){
                 s = input.readLine();
                 cust.add((Customer)obj);
            }else if (obj instanceof Supplier){
                 s = input.readLine();
                 supp.add((Supplier)obj);
         }
        }
      }
        catch (IOException ex) {
            System.out.println("Class error reading file..." + ex);
        } catch (ClassNotFoundException ex) { 
            System.out.println("File has not been found..");
       } finally{
            closeFile();
            System.out.println("File has been closed...");
        }
    }
    // Sorts the customer array objects in ascending order of the stakeholder ID
      public void sortCustomerArray(){
          for(int i = 0; i < cust.size(); i++)
             {
                for(int j = 0; j < cust.size(); j++)
              {
                 if(cust.get(i).getStHolderId().equals(cust.get(j).getStHolderId())){
                    Customer c = cust.get(i);
                    cust.set(i,cust.get(j));
                    cust.set(j,c);
                        }
                    }
            }
      }
     // Calculates the age of each customer object
      public int customerAge(){
          int age[] = new int[cust.size()];
              
             for(int i = 0; i < cust.size(); i++){
                   
                   LocalDate id =LocalDate.parse(cust.get(i).getDateOfBirth());
                   int year = id.getYear();
                   age[i] = 2021-year;
                   System.out.println(age[i]);
                }
       return 0;
      }
     // Re-formats the date of birth  
      public void dateFormat(){
          String p = "dd MMM yyyy";
          SimpleDateFormat sdf = new SimpleDateFormat(p, new Locale("da", "DK"));
          
          for(int i = 0; i < cust.size(); i++){
          cust.get(i).setDateOfBirth(sdf.format(cust.get(i).getDateOfBirth()));
        }
      }
      
     // Writes the customer objects to the customerOutFile.txt file
      public void writeCustomerDetails(){
       try (BufferedWriter bw = new BufferedWriter( new FileWriter("customerOutFile.txt"))) {
           String s = "===============CUSTOMERS================";
           bw.write(s);
           bw.newLine();
           s = "ID\tName\tSurname\tDate of birth\tAge";
           bw.write(s);
           bw.newLine();
           s = "===========================================";
           bw.write(s);
           bw.newLine();
           
           for(int i = 0; i < cust.size(); i++){
               s = cust.get(i).getStHolderId()+"\t" + cust.get(i).getFirstName() + "\t"
                       + cust.get(i).getSurName()+"\t" +
                       cust.get(i).getDateOfBirth()+"\t" + customerAge();
               bw.write(s);
               bw.newLine();
           }

            s = "Number of customers who can rent:" + rent();
            bw.write(s);
            bw.newLine();
            s = "Number of customers who cannot rent:" + rent();
            bw.write(s);
            bw.newLine();
            
       } catch (IOException ex) {
           System.out.println("File not found..." + ex);
       }
          
      }
     
     // Iterates through array and determines which custom can and cannot rent
      public int rent(){
            int r = 0;
            int nr = 0;
            
          for(int i = 0; i < cust.size(); i++){
            if(cust.get(i).getCanRent()){
                r += 1;
            }else{
                nr += 1;
            }
        }
       return 0;
      }
     
     // Sorts the supplier array in ascending order alphabetically by name 
      public void sortSupplierArray(){
            for(int i = 0; i < supp.size(); i++){
                for(int j = 1; j < supp.size(); j++){
                   if((supp.get(j).getName()).equals(supp.get(i).getName())){
                       Supplier s = supp.get(i);
                       supp.set(i,supp.get(j));
                       supp.set(j,s);
                   } else {
                   }
                }
            }
      }
     
     // Writes the supplier objects to the supplierOutFile.txt file
      public void writeSupplierDetails(){
         try(BufferedWriter bw = new BufferedWriter( new FileWriter("supplierOutFile.txt"))){
          String s = "===============SUPPLIERS================";
          bw.write(s);
          bw.newLine();
          s = "ID\tName\tProd Type\tDescription";
          bw.write(s);
          bw.newLine();
          s =" ===========================================";
          bw.write(s);
          bw.newLine();
          
          for(int i=0; i < supp.size() ;i++){
                s = supp.get(i).getStHolderId() + "\t"+supp.get(i).getName() + 
                        "\t"+supp.get(i).getProductType() + 
                        "\t"+supp.get(i).getProductDescription();
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException ex) {
             System.out.println("File not found..." + ex);
       }
      }
        
    } 
    
    

