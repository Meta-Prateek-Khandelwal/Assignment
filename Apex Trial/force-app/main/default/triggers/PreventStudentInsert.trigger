trigger PreventStudentInsert on Student__c (before insert) {
    Set<Id> classIds = new Set<Id>();
    for (Student__c s : Trigger.new) {
        if (s.Class__c != null) {
            classIds.add(s.Class__c);
        }
    }

    Map<Id, Integer> classStudentCount = new Map<Id, Integer>();
    for (AggregateResult res : [
        SELECT Class__c, COUNT(Id)
        FROM Student__c
        WHERE Class__c IN :classIds
        GROUP BY Class__c
    ]) {
        classStudentCount.put((Id)res.get('Class__c'), (Integer)res.get('expr0'));
    }
}