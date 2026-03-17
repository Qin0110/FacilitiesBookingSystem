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