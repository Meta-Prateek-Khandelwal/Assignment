trigger CountTrigger on Student__c (after insert, after update) {
	Set<ID> classIds = new Set<ID>();
    
    for(Student__c s: Trigger.new){
        if(s.class__c != null){
            classIds.add(s.class__c);
        }
    }
    
    Map<Id, Integer> classCount = new Map<Id, Integer>();
    for(AggregateResult ar : [
        SELECT class__c, COUNT(id) countStd
        FROM student__c
        WHERE class__c IN :classIds
        GROUP BY class__c
    ]){
        classCount.put((id)ar.get('class__c'),(Integer)ar.get('countStd'));
    }
    
    List<Class__c> updates = new List<class__c>();
    for(Id classId: classCount.keySet()){
        updates.add(new Class__c(
        	Id = classID,
            MyCount__c = classCount.get(classID)
        ));
    }
    
    update updates;
}