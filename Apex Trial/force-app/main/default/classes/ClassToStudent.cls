public class classToStudent{
    public static Map<Id, Set<Id>> getClassToStudentMap() {

        Map<Id, Set<Id>> resultMap = new Map<Id, Set<Id>>();
        List<Student__c> students = [SELECT Id, Class__c FROM Student__c];
        for (Student__c stu : students) {
            if (!resultMap.containsKey(stu.Class__c)) {
                resultMap.put(stu.Class__c, new Set<Id>());
            }
            resultMap.get(stu.Class__c).add(stu.Id);
        }
        
        return resultMap;
    }
}

