//Ng Wei Hao 
package control;
import adt.SingleLinkedList;
import adt.ListInterface;
import entity.Student;

import dao.StudentCollection;


public class StudentManagement {
    
    private ListInterface<Student> studentList;
    StudentCollection studentCollection = new StudentCollection();
    public StudentManagement() {}
    
    public String addNewStudent(String id, String name, String programme, String email, String phoneNumber) {
 
         ListInterface<Student> studentList = this.getAllStudents(); 

         // Loop through the list to check for duplicates
         for (int i = 0; i < studentList.size(); i++) {
             Student existingStudent = studentList.get(i);

             if (existingStudent.getId().equalsIgnoreCase(id)) {
                 return "Error: This Student ID already exists!"; 
             }
             if (existingStudent.getEmail().equalsIgnoreCase(email)) {
                 return "Error: This Email is already registered!"; 
             }
             if (existingStudent.getPhoneNumber().equals(phoneNumber)) {
                 return "Error: This Phone Number is already registered!"; 
             }
         }

         // If no duplicates are found, create and save the student
         try {
             Student newStudent = new Student(id, name, programme, email, phoneNumber);
             studentCollection.addStudent(newStudent);
             return "Student added successfully!";
         } catch (IllegalArgumentException e) {
             return "ERROR: " + e.getMessage(); // Catches format errors from Entity layer
         }
    }

    public ListInterface<Student> getAllStudents() {
        return studentCollection.getAllStudent();
    }

    public boolean removeStudent(String studentId) {
        return studentCollection.removeStudent(studentId);
    }

    public String updateStudent(String id, String name, String programme, String email, String phone) {

        ListInterface<Student> studentList = this.getAllStudents(); 
    
        // Loop through the list to check for duplicates
        for (int i = 0; i < studentList.size(); i++) {
            Student existingStudent = studentList.get(i);

            //  Skip the student info currently updating
            if (existingStudent.getId().equalsIgnoreCase(id)) {
                continue; 
            }

            // checking OTHER students' records, id no need check since it cannot edit
            if (existingStudent.getEmail().equalsIgnoreCase(email)) {
                return "Error: This Email is already registered to another student!"; 
            }
            if (existingStudent.getPhoneNumber().equals(phone)) {
                return "Error: This Phone Number is already registered to another student!"; 
            }
        }

        // update the student
        try {
            Student updatedStudent = new Student(id, name, programme, email, phone);
            studentCollection.updateStudentInfo(updatedStudent); 
            return updatedStudent.getName() + " with Student ID " + 
                   updatedStudent.getId() + " has been successfully updated!";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

//    public ListInterface<Student> searchStudent(String id) {
//        return studentCollection.getStudentById(id);
//    }
    
    public ListInterface<Student> searchStudentForTable(String idOrName,String programme, String sortBy, String sortDirection) {
        ListInterface<Student> searchResults = new SingleLinkedList<>();
        ListInterface<Student> allStudent = studentCollection.getAllStudent();
 
        for (int i = 0; i < allStudent.size(); i++) {
            Student student = allStudent.get(i);
 
            boolean matchesId   = student.getStudentId().toLowerCase().contains(idOrName.toLowerCase())
                                || student.getName().toLowerCase().contains(idOrName.toLowerCase());
            boolean matchesType = (programme == null || programme.equals("ALL") || student.getProgramme().equals(programme));
 
            if (matchesId && matchesType) {
                searchResults.add(student);
            }
        }
        // Sort the filtered list based on the combo box selection
        if (sortBy != null && !sortBy.trim().isEmpty()) {
            
            // Check if we want descending order
            boolean isAscending = sortDirection.equals("Ascending");
            
            searchResults.sort(new adt.Comparator<entity.Student>() {
                @Override
                public int compare(entity.Student s1, entity.Student s2) {
                    int result = 0; // Store the comparison result here first
                    switch (sortBy) {
                        case "Student ID":
                            result = s1.getStudentId().compareToIgnoreCase(s2.getStudentId());
                            break;
                        case "Name":
                            result = s1.getName().compareToIgnoreCase(s2.getName());
                            break;
                        case "Programme":
                            result = s1.getProgramme().compareToIgnoreCase(s2.getProgramme());
                            break;
                        case "Email":
                            result = s1.getEmail().compareToIgnoreCase(s2.getEmail());
                            break;
                        case "Phone Number":
                            result = s1.getPhoneNumber().compareToIgnoreCase(s2.getPhoneNumber());
                            break;
                    }
                    
                    // If descending, we just reverse the integer result (multiply by -1)
                    return isAscending ? result : (result * -1);
                }
            });
        }

        return searchResults;
    }
    
    public Student getStudentById(String studentId) {
            return studentCollection.getStudentById(studentId);
    }
    
    public boolean validateMember(String studentId, String studentName) {
        Student s = this.getStudentById(studentId);
        
        if (s != null && s.getName().equalsIgnoreCase(studentName.trim())) {
            return true; 
        }
        return false; 
    }
    
    public String generateStudentReport() {
        adt.ListInterface<entity.Student> allStudents = studentCollection.getAllStudent();

        int totalStudents = allStudents.size();
        
        int dataScienceCount = 0;
        int softwareEngCount = 0;
        int softwareDevCount = 0;

        for (int i = 0; i < totalStudents; i++) {
            String prog = allStudents.get(i).getProgramme();
            if (prog.equalsIgnoreCase("Data Science")) dataScienceCount++;
            else if (prog.equalsIgnoreCase("Software Engineering")) softwareEngCount++;
            else if (prog.equalsIgnoreCase("Software Developer")) softwareDevCount++;
        }

        StringBuilder report = new StringBuilder();
        report.append("==================================================\n");
        report.append("             STUDENT SUMMARY REPORT               \n");
        report.append("==================================================\n\n");

        report.append("--- OVERALL STATISTICS ---\n");
        report.append("Total Students Registered : ").append(totalStudents).append("\n\n");

        report.append("--- PROGRAMME BREAKDOWN ---\n");
        report.append("Data Science              : ").append(dataScienceCount).append("\n");
        report.append("Software Engineering      : ").append(softwareEngCount).append("\n");
        report.append("Software Developer        : ").append(softwareDevCount).append("\n\n");

        report.append("--- DETAILED STUDENT LIST ---\n");
        report.append(String.format("%-15s %-25s %-25s\n", "STUDENT ID", "NAME", "PROGRAMME"));
        report.append("-----------------------------------------------------------------\n");

        for (int i = 0; i < totalStudents; i++) {
            entity.Student s = allStudents.get(i);
            report.append(String.format("%-15s %-25s %-25s\n", 
                s.getStudentId(), s.getName(), s.getProgramme()));
        }

        report.append("\n=================== END OF REPORT ===================");

        return report.toString();
    }
    
}
