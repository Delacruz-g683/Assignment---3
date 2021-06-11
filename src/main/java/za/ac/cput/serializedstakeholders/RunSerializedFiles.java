
package za.ac.cput.serializedstakeholders;

public class RunSerializedFiles {

    
    public static void main(String[] args) {
     SerializedFilesSolution f =  new SerializedFilesSolution();
     f.openFile();
     f.readFromFile();
     f.sortCustomerArray();
     f.dateFormat();
     f.writeCustomerDetails();
     f.closeFile();
     
     System.out.println("");
        
     f.openFile();
     f.readFromFile();
     f.sortSupplierArray();
     f.writeSupplierDetails();
     f.closeFile();
     
     
    }
    
}
