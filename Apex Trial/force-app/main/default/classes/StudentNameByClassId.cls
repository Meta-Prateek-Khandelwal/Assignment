public class StudentNameByClassId {
    // Create a class and a static function which takes classId as argument and returns all names of the students in that class as a list.
    public static List<String> getStudentNameByClassId(Id classID){
        List<Student__c> students = [
            SELECT Name 
            FROM student__c
            WHERE class__c = :classID
        ];
        
        List<String> studentName = new List<String>();
        for(Student__c student: students){
            StudentName.add(Student.Name);
        }
        
        return studentName;
    }
}