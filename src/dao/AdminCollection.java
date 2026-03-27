package dao;

import adt.ListInterface;
import UtilitiesClass.DATFile;
import entity.Admin;
import java.io.Serializable;
 
public class AdminCollection implements Serializable {
    
    private static final String FILE_NAME = "src/Data/admin.dat";

    private ListInterface<Admin> adminList;
 
    public AdminCollection() {
        adminList = DATFile.loadFromFile(FILE_NAME);
        
        if (adminList.isEmpty()) {
            
            adminList.add(new Admin("A001", "Mr. Tan", "Facility Manager", "tan099@tarc.edu.my", "017-3573716"));
            adminList.add(new Admin("A002", "Mr. Khoo", "Facility Manager", "khoo666@tarc.edu.my", "019-6695214"));
            adminList.add(new Admin("A005", "Ms. Lim", "Facility Assistant", "lim123@tarc.edu.my", "012-3456789"));
            adminList.add(new Admin("A003", "Mr. Lee", "Facility Assistant", "lee888@tarc.edu.my", "013-9876543"));
            adminList.add(new Admin("A004", "Ms. Wong", "Facility Manager", "wong777@tarc.edu.my", "016-2233445"));
            adminList.add(new Admin("A006", "Mr. Goh", "Facility Assistant", "goh111@tarc.edu.my", "018-5566778"));
            adminList.add(new Admin("A007", "Ms. Chia", "Facility Manager", "chia999@tarc.edu.my", "014-6677889"));
            adminList.add(new Admin("A008", "Mr. Ong", "Facility Assistant", "ong555@tarc.edu.my", "015-1122334"));
            adminList.add(new Admin("A009", "Ms. Tan", "Facility Assistant", "tan222@tarc.edu.my", "017-9988776"));
            adminList.add(new Admin("A010", "Mr. Ng", "Facility Manager", "ng333@tarc.edu.my", "019-4433221"));
            
            saveData(); 
        }
    }
    
    private void saveData() {
        java.io.File directory = new java.io.File("src/Data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        DATFile.saveToFile(FILE_NAME, adminList);
    }
 
    public boolean add(Admin admin) {
        if (isAdminExists(admin.getAdminId())) 
            return false;
        
        boolean result = adminList.add(admin);
        
        if (result) 
            saveData();  
        
        return result;
    }
 
    public ListInterface<Admin> getAllAdmin() {
        return adminList;
    }
 
    public Admin getAdminById(String adminId) {
        for (int i = 0; i < adminList.size(); i++) {
            Admin admin = adminList.get(i);
            if (admin.getAdminId().equalsIgnoreCase(adminId)) {  //small and big character both accept when compare
                return admin;
            }
        }
        
        return null;
    }
 
    public boolean isAdminExists(String adminId) {
        return getAdminById(adminId) != null;
    }
 
    public boolean remove(String adminId) {
        for (int i = 0; i < adminList.size(); i++) {
            if (adminList.get(i).getAdminId().equalsIgnoreCase(adminId)) {
                adminList.remove(i);
                saveData();      
                return true;
            }
        }
        
        return false;
    }
 
    //Update the info
    public boolean set(Admin element) {  
         for (int i = 0; i < adminList.size(); i++) {
            if (adminList.get(i).getAdminId().equalsIgnoreCase(element.getAdminId())) {
                adminList.set(i, element);
                saveData();      
                return true;
            }
        }
         
        return false;
    }
 
    public int getTotalNumberAdmin() {
        return adminList.size();
    }
}