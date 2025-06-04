public class abc {
    public List<String> getStudentNameByClassId(Id classID){
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

    // Create a function which returns a map where key is class and value is set of studentId in it.
    public Map<Id, Set<Id>> getStudentId(){
        Map<Id, Set<Id>> classToStudentIds = new Map<Id, Set<Id>>();

        List<Student__c> students = [
            SELECT Id, Class__c
            FROM Student__c
            WHERE Class__c != null
        ];

        for(Student student: students){
            Id classId = student.class;

            if (!classToStudentIds.containsKey(classId)) {
                classToStudentIds.put(classId, new Set<Id>());  
            }

            classToStudentIds.get(classId).add(student.Id);
        }

        return classToStudentIds;
    }

    //     Create a function which take two dates as argument and return number of working days between two
    // dates excluding both days. (Saturday and Sunday is non-working days)

    public Integer getWorkingDays(Date startDate, Date endDate){
        if(startDate >= endDate){
            return 0;
        }

        Integer workingDays = 0;
        Date currentDate = startDate.addDays(1);
        while (startDate < endDate){
            Integer dayOfWeek = currentDate.toStartOfWeek().daysBetween(currentDate);

            if(dayOfWeek  != 6 && dayOfWeek != 0){
                workingDays += 1;
            }

            currentDate = currentDate.addDays(1);
        }

        return workingDays;
    }
}