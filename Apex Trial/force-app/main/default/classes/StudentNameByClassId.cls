public class StudentNameByClassId {
    public static List<String> getStudentNameByClassId(Id classID){
        List<Student__c> students = [
            SELECT Name 
            FROM student__c
            WHERE class__c = :classID
        ];
        
        List<String> studentNames = new List<String>();
        for(Student__c student: students){
            StudentName.add(Student.Name);
        }
        
        return studentName;
    }
}