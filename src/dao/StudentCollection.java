package dao;

import adt.ListInterface;
import UtilitiesClass.DATFile;
import entity.Student;
import java.io.Serializable;
 
public class StudentCollection implements Serializable {
    
    private static final String FILE_NAME = "src/Data/student.dat";

    private ListInterface<Student> studentList;
 
    public StudentCollection() {
        studentList = DATFile.loadFromFile(FILE_NAME);
        
        if (studentList.isEmpty()) {
            
            studentList.add(new Student("23WMR00001", "Ali bin Abu", "Software Engineering", "ali001@student.tarc.edu.my", "017-6315001"));
            studentList.add(new Student("23WMR00002", "Ahmad bin Ali", "Software Developer", "ahmad002@student.tarc.edu.my", "017-6315002"));
            studentList.add(new Student("23WMR00003", "Siti Aisyah", "Software Engineering", "siti003@student.tarc.edu.my", "017-6315003"));
            studentList.add(new Student("23WMR00004", "Nurul Izzah", "Software Developer", "nurul004@student.tarc.edu.my", "017-6315004"));
            studentList.add(new Student("23WMR00005", "Tan Wei Ming", "Software Engineering", "tan005@student.tarc.edu.my", "017-6315005"));
            studentList.add(new Student("23WMR00006", "Lim Jia Hao", "Software Engineering", "lim006@student.tarc.edu.my", "017-6315006"));
            studentList.add(new Student("23WMR00007", "Wong Jun Yi", "Software Developer", "wong007@student.tarc.edu.my", "017-6315007"));
            studentList.add(new Student("23WMR00008", "Lee Kai Wen", "Software Engineering", "lee008@student.tarc.edu.my", "017-6315008"));
            studentList.add(new Student("23WMR00009", "Ng Zi Xuan", "Software Engineering", "ng009@student.tarc.edu.my", "017-6315009"));
            studentList.add(new Student("23WMR00010", "Goh Yi Xuan", "Data Science", "goh010@student.tarc.edu.my", "017-6315010"));
            
            studentList.add(new Student("23WMR00011", "Chia Wei Ling", "Software Engineering", "chia011@student.tarc.edu.my", "017-6315011"));
            studentList.add(new Student("23WMR00012", "Ong Hui Min", "Software Developer", "ong012@student.tarc.edu.my", "017-6315012"));
            studentList.add(new Student("23WMR00013", "Teo Jun Hao", "Software Engineering", "teo013@student.tarc.edu.my", "017-6315013"));
            studentList.add(new Student("23WMR00014", "Low Pei Shan", "Software Engineering", "low014@student.tarc.edu.my", "017-6315014"));
            studentList.add(new Student("23WMR00015", "Koh Xin Yi", "Software Engineering", "koh015@student.tarc.edu.my", "017-6315015"));
            studentList.add(new Student("23WMR00016", "Chan Jia Wei", "Data Science", "chan016@student.tarc.edu.my", "017-6315016"));
            studentList.add(new Student("23WMR00017", "Ho Mei Ling", "Software Engineering", "ho017@student.tarc.edu.my", "017-6315017"));
            studentList.add(new Student("23WMR00018", "Yap Jun Wei", "Software Engineering", "yap018@student.tarc.edu.my", "017-6315018"));
            studentList.add(new Student("23WMR00019", "Sim Wen Jie", "Data Science", "sim019@student.tarc.edu.my", "017-6315019"));
            studentList.add(new Student("23WMR00020", "Tan Jia Xin", "Data Science", "tan020@student.tarc.edu.my", "017-6315020"));
            
            studentList.add(new Student("23WMR00021", "Lim Wei Jie", "Software Engineering", "lim021@student.tarc.edu.my", "017-6315021"));
            studentList.add(new Student("23WMR00022", "Tan Jia Hao", "Software Developer", "tan022@student.tarc.edu.my", "017-6315022"));
            studentList.add(new Student("23WMR00023", "Ng Hui Wen", "Data Science", "ng023@student.tarc.edu.my", "017-6315023"));
            studentList.add(new Student("23WMR00024", "Lee Zi Xuan", "Software Engineering", "lee024@student.tarc.edu.my", "017-6315024"));
            studentList.add(new Student("23WMR00025", "Wong Kai Jie", "Software Developer", "wong025@student.tarc.edu.my", "017-6315025"));
            studentList.add(new Student("23WMR00026", "Goh Jia Min", "Software Engineering", "goh026@student.tarc.edu.my", "017-6315026"));
            studentList.add(new Student("23WMR00027", "Chong Wei Lun", "Data Science", "chong027@student.tarc.edu.my", "017-6315027"));
            studentList.add(new Student("23WMR00028", "Ooi Jun Hao", "Software Engineering", "ooi028@student.tarc.edu.my", "017-6315028"));
            studentList.add(new Student("23WMR00029", "Kok Yi Chen", "Software Developer", "kok029@student.tarc.edu.my", "017-6315029"));
            studentList.add(new Student("23WMR00030", "Teh Pei Ying", "Software Engineering", "teh030@student.tarc.edu.my", "017-6315030"));

            studentList.add(new Student("23WMR00031", "Lim Jia Xin", "Data Science", "lim031@student.tarc.edu.my", "017-6315031"));
            studentList.add(new Student("23WMR00032", "Tan Wei Xuan", "Software Engineering", "tan032@student.tarc.edu.my", "017-6315032"));
            studentList.add(new Student("23WMR00033", "Ng Jun Hao", "Software Developer", "ng033@student.tarc.edu.my", "017-6315033"));
            studentList.add(new Student("23WMR00034", "Lee Hui Ting", "Software Engineering", "lee034@student.tarc.edu.my", "017-6315034"));
            studentList.add(new Student("23WMR00035", "Wong Zi Hao", "Data Science", "wong035@student.tarc.edu.my", "017-6315035"));
            studentList.add(new Student("23WMR00036", "Goh Wei Ling", "Software Engineering", "goh036@student.tarc.edu.my", "017-6315036"));
            studentList.add(new Student("23WMR00037", "Chong Jia Hao", "Software Developer", "chong037@student.tarc.edu.my", "017-6315037"));
            studentList.add(new Student("23WMR00038", "Ooi Zi Xuan", "Software Engineering", "ooi038@student.tarc.edu.my", "017-6315038"));
            studentList.add(new Student("23WMR00039", "Kok Jun Yi", "Data Science", "kok039@student.tarc.edu.my", "017-6315039"));
            studentList.add(new Student("23WMR00040", "Teh Wei Jie", "Software Engineering", "teh040@student.tarc.edu.my", "017-6315040"));

            studentList.add(new Student("23WMR00041", "Lim Zi Wen", "Software Developer", "lim041@student.tarc.edu.my", "017-6315041"));
            studentList.add(new Student("23WMR00042", "Tan Hui Min", "Software Engineering", "tan042@student.tarc.edu.my", "017-6315042"));
            studentList.add(new Student("23WMR00043", "Ng Jia Hao", "Data Science", "ng043@student.tarc.edu.my", "017-6315043"));
            studentList.add(new Student("23WMR00044", "Lee Wei Ting", "Software Engineering", "lee044@student.tarc.edu.my", "017-6315044"));
            studentList.add(new Student("23WMR00045", "Wong Jun Hao", "Software Developer", "wong045@student.tarc.edu.my", "017-6315045"));
            studentList.add(new Student("23WMR00046", "Goh Zi Xuan", "Software Engineering", "goh046@student.tarc.edu.my", "017-6315046"));
            studentList.add(new Student("23WMR00047", "Chong Wei Jie", "Data Science", "chong047@student.tarc.edu.my", "017-6315047"));
            studentList.add(new Student("23WMR00048", "Ooi Jia Min", "Software Engineering", "ooi048@student.tarc.edu.my", "017-6315048"));
            studentList.add(new Student("23WMR00049", "Kok Hui Ling", "Software Developer", "kok049@student.tarc.edu.my", "017-6315049"));
            studentList.add(new Student("23WMR00050", "Teh Jun Hao", "Software Engineering", "teh050@student.tarc.edu.my", "017-6315050"));

            saveData(); 
        }
    }
    
    private void saveData() {
        java.io.File directory = new java.io.File("src/Data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        DATFile.saveToFile(FILE_NAME, studentList);
    }
 
    public boolean add(Student student) {
        if (isStudentExists(student.getStudentId())) 
            return false;
        
        boolean result = studentList.add(student);
        
        if (result) 
            saveData();  
        
        return result;
    }
 
    public ListInterface<Student> getAllStudent() {
        return studentList;
    }
 
    public Student getStudentById(String studentId) {
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getStudentId().equalsIgnoreCase(studentId)) {  //small and big character both accept when compare
                return student;
            }
        }
        
        return null;
    }
 
    public boolean isStudentExists(String studentId) {
        return getStudentById(studentId) != null;
    }
 
    public boolean remove(String studentId) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId().equalsIgnoreCase(studentId)) {
                studentList.remove(i);
                saveData();      
                return true;
            }
        }
        
        return false;
    }
 
    //Update the info
    public boolean set(Student element) {  
         for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId().equalsIgnoreCase(element.getStudentId())) {
                studentList.set(i, element);
                saveData();      
                return true;
            }
        }
         
        return false;
    }
 
    public int getTotalNumberStudent() {
        return studentList.size();
    }
}