trigger ClassTriggers on class__c (before delete) {
    // Not allow any class to delete if there are more than one Female students.
	Set<Id> classIds = new Set<Id>();
    for(class__c c: Trigger.old){
        classIds.add(c.id);
    }
    
    Map<Id, Integer> females = new Map<Id, Integer>();
    for(Student__c s: [
        SELECT id, class__c
        FROM student__c
        WHERE class__c IN :classIds AND
        Gender__c = 'FEMALE'
    ]){
        if(females.containsKey(s.class__c)){
            females.put(s.class__c,females.get(s.class__c)+1);
        }else{
            females.put(s.class__c,1);
        }
    }
    
    for(class__c c: Trigger.old){
        if(females.containsKey(c.ID) && females.get(c.Id) > 1){
            c.addError('cannot delete class with more then one female student.');
        }
    }
}