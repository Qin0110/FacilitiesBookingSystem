/**
 * @author Ng Wei Hao
 */


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
            
            adminList.add(new Admin("A1001", "Tan Lai Kin", "Facility Assistant", "tan099@tarc.edu.my", "017-3573716"));
            adminList.add(new Admin("A1002", "Khoo Jun Rong", "Facility Manager", "khoo666@tarc.edu.my", "019-6695214"));
            adminList.add(new Admin("A1003", "Lee Ka Wei", "Facility Assistant", "lee888@tarc.edu.my", "013-9876543"));
            adminList.add(new Admin("A1004", "Wong Jun Kai", "Facility Manager", "wong777@tarc.edu.my", "016-2233445"));
            adminList.add(new Admin("A1005", "Lim Jin Hui", "Facility Assistant", "lim123@tarc.edu.my", "012-3456789"));
            
            adminList.add(new Admin("A1006", "Goh Li Xiang", "Facility Assistant", "goh111@tarc.edu.my", "018-5566778"));
            adminList.add(new Admin("A1007", "Chia Seng Hee", "Facility Manager", "chia999@tarc.edu.my", "014-6677889"));
            adminList.add(new Admin("A1008", "Ong Sin Rou", "Facility Assistant", "ong555@tarc.edu.my", "015-1122334"));
            adminList.add(new Admin("A1009", "Tan Mei Li", "Facility Assistant", "tan222@tarc.edu.my", "017-9988776"));
            adminList.add(new Admin("A1010", "Ng Zhi Xiang", "Facility Assistant", "ng333@tarc.edu.my", "019-4433221"));
            
            adminList.add(new Admin("A1011", "Low Wei Jie", "Facility Assistant", "low111@tarc.edu.my", "016-7788990"));
            adminList.add(new Admin("A1012", "Chan Yu Xuan", "Facility Assistant", "chan222@tarc.edu.my", "013-5566443"));
            adminList.add(new Admin("A1013", "Teo Kai Sheng", "Facility Assistant", "teo333@tarc.edu.my", "018-3344556"));
            adminList.add(new Admin("A1014", "Lau Jia Yi", "Facility Assistant", "lau444@tarc.edu.my", "017-2233110"));
            adminList.add(new Admin("A1015", "Chew Wen Hao", "Facility Assistant", "chew555@tarc.edu.my", "012-6677881"));
            
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
 
    public boolean addAdmin(Admin admin) {
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
 
    public boolean removeAdmin(String adminId) {
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
    public boolean updateAdminInfo(Admin element) {  
         for (int i = 0; i < adminList.size(); i++) {
            if (adminList.get(i).getAdminId().equalsIgnoreCase(element.getAdminId())) {
                adminList.set(i, element);
                saveData();      
                return true;
            }
        }
         
        return false;
    }
 
}