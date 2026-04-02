/**
 * @author Ng Wei Hao
 */


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
            
            studentList.add(new Student("25WMR00001", "Ali bin Abu", "Software Engineering", "ali001@student.tarc.edu.my", "012-8457392"));
            studentList.add(new Student("25WMR00002", "Ahmad bin Ali", "Software Developer", "ahmad002@student.tarc.edu.my", "017-3928475"));
            studentList.add(new Student("25WMR00003", "Siti Aisyah", "Software Engineering", "siti003@student.tarc.edu.my", "011-6748293"));
            studentList.add(new Student("25WMR00004", "Nurul Izzah", "Software Developer", "nurul004@student.tarc.edu.my", "019-5839201"));
            studentList.add(new Student("25WMR00005", "Tan Wei Ming", "Software Engineering", "tan005@student.tarc.edu.my", "016-9203847"));

            studentList.add(new Student("25WMR00006", "Lim Jia Hao", "Software Engineering", "lim006@student.tarc.edu.my", "013-7483920"));
            studentList.add(new Student("25WMR00007", "Wong Jun Yi", "Software Developer", "wong007@student.tarc.edu.my", "018-2938475"));
            studentList.add(new Student("25WMR00008", "Lee Kai Wen", "Software Engineering", "lee008@student.tarc.edu.my", "014-8573920"));
            studentList.add(new Student("25WMR00009", "Ng Zi Xuan", "Software Engineering", "ng009@student.tarc.edu.my", "010-3948572"));
            studentList.add(new Student("25WMR00010", "Goh Yi Xuan", "Data Science", "goh010@student.tarc.edu.my", "017-4829305"));

            studentList.add(new Student("25WMR00011", "Chia Wei Ling", "Software Engineering", "chia011@student.tarc.edu.my", "012-9384750"));
            studentList.add(new Student("25WMR00012", "Ong Hui Min", "Software Developer", "ong012@student.tarc.edu.my", "019-2847503"));
            studentList.add(new Student("25WMR00013", "Teo Jun Hao", "Software Engineering", "teo013@student.tarc.edu.my", "016-5938472"));
            studentList.add(new Student("25WMR00014", "Low Pei Shan", "Software Engineering", "low014@student.tarc.edu.my", "018-4739205"));
            studentList.add(new Student("25WMR00015", "Koh Xin Yi", "Software Engineering", "koh015@student.tarc.edu.my", "011-8457293"));

            studentList.add(new Student("25WMR00016", "Chan Jia Wei", "Data Science", "chan016@student.tarc.edu.my", "013-7293845"));
            studentList.add(new Student("25WMR00017", "Ho Mei Ling", "Software Engineering", "ho017@student.tarc.edu.my", "017-5948203"));
            studentList.add(new Student("25WMR00018", "Yap Jun Wei", "Software Engineering", "yap018@student.tarc.edu.my", "014-8392057"));
            studentList.add(new Student("25WMR00019", "Sim Wen Jie", "Data Science", "sim019@student.tarc.edu.my", "019-4738291"));
            studentList.add(new Student("25WMR00020", "Tan Jia Xin", "Data Science", "tan020@student.tarc.edu.my", "012-7592038"));

            studentList.add(new Student("25WMR00021", "Lim Wei Jie", "Software Engineering", "lim021@student.tarc.edu.my", "010-3847295"));
            studentList.add(new Student("25WMR00022", "Tan Jia Hao", "Software Developer", "tan022@student.tarc.edu.my", "018-9472035"));
            studentList.add(new Student("25WMR00023", "Ng Hui Wen", "Data Science", "ng023@student.tarc.edu.my", "016-3849205"));
            studentList.add(new Student("25WMR00024", "Lee Zi Xuan", "Software Engineering", "lee024@student.tarc.edu.my", "017-5820394"));
            studentList.add(new Student("25WMR00025", "Wong Kai Jie", "Software Developer", "wong025@student.tarc.edu.my", "013-8492037"));

            studentList.add(new Student("25WMR00026", "Goh Jia Min", "Software Engineering", "goh026@student.tarc.edu.my", "019-3029485"));
            studentList.add(new Student("25WMR00027", "Chong Wei Lun", "Data Science", "chong027@student.tarc.edu.my", "011-9482037"));
            studentList.add(new Student("25WMR00028", "Ooi Jun Hao", "Software Engineering", "ooi028@student.tarc.edu.my", "014-5938204"));
            studentList.add(new Student("25WMR00029", "Kok Yi Chen", "Software Developer", "kok029@student.tarc.edu.my", "012-8392057"));
            studentList.add(new Student("25WMR00030", "Teh Pei Ying", "Software Engineering", "teh030@student.tarc.edu.my", "018-4029385"));

            studentList.add(new Student("25WMR00031", "Lim Jia Xin", "Data Science", "lim031@student.tarc.edu.my", "016-2839475"));
            studentList.add(new Student("25WMR00032", "Tan Wei Xuan", "Software Engineering", "tan032@student.tarc.edu.my", "017-9203847"));
            studentList.add(new Student("25WMR00033", "Ng Jun Hao", "Software Developer", "ng033@student.tarc.edu.my", "013-4839205"));
            studentList.add(new Student("25WMR00034", "Lee Hui Ting", "Software Engineering", "lee034@student.tarc.edu.my", "019-8472035"));
            studentList.add(new Student("25WMR00035", "Wong Zi Hao", "Data Science", "wong035@student.tarc.edu.my", "011-3849205"));

            studentList.add(new Student("25WMR00036", "Goh Wei Ling", "Software Engineering", "goh036@student.tarc.edu.my", "014-9384750"));
            studentList.add(new Student("25WMR00037", "Chong Jia Hao", "Software Developer", "chong037@student.tarc.edu.my", "012-5938472"));
            studentList.add(new Student("25WMR00038", "Ooi Zi Xuan", "Software Engineering", "ooi038@student.tarc.edu.my", "018-4739205"));
            studentList.add(new Student("25WMR00039", "Kok Jun Yi", "Data Science", "kok039@student.tarc.edu.my", "017-8457293"));
            studentList.add(new Student("25WMR00040", "Teh Wei Jie", "Software Engineering", "teh040@student.tarc.edu.my", "016-7293845"));

            studentList.add(new Student("25WMR00041", "Lim Zi Wen", "Software Developer", "lim041@student.tarc.edu.my", "019-5948203"));
            studentList.add(new Student("25WMR00042", "Tan Hui Min", "Software Engineering", "tan042@student.tarc.edu.my", "013-8392057"));
            studentList.add(new Student("25WMR00043", "Ng Jia Hao", "Data Science", "ng043@student.tarc.edu.my", "012-4738291"));
            studentList.add(new Student("25WMR00044", "Lee Wei Ting", "Software Engineering", "lee044@student.tarc.edu.my", "010-7592038"));
            studentList.add(new Student("25WMR00045", "Wong Jun Hao", "Software Developer", "wong045@student.tarc.edu.my", "018-3847295"));

            studentList.add(new Student("25WMR00046", "Goh Zi Xuan", "Software Engineering", "goh046@student.tarc.edu.my", "014-9472035"));
            studentList.add(new Student("25WMR00047", "Chong Wei Jie", "Data Science", "chong047@student.tarc.edu.my", "017-3849205"));
            studentList.add(new Student("25WMR00048", "Ooi Jia Min", "Software Engineering", "ooi048@student.tarc.edu.my", "013-5820394"));
            studentList.add(new Student("25WMR00049", "Kok Hui Ling", "Software Developer", "kok049@student.tarc.edu.my", "019-8492037"));
            studentList.add(new Student("25WMR00050", "Teh Jun Hao", "Software Engineering", "teh050@student.tarc.edu.my", "011-3029485"));
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
 
    public boolean addStudent(Student student) {
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
 
    public boolean removeStudent(String studentId) {
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
    public boolean updateStudentInfo(Student element) {  
         for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId().equalsIgnoreCase(element.getStudentId())) {
                studentList.set(i, element);
                saveData();      
                return true;
            }
        }
         
        return false;
    }
 
}