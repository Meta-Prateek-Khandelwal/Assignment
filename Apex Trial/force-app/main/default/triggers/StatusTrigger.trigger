trigger StatusTrigger on class__c (after update) {
	Set<ID> classIds = new Set<ID>();
    
    for(class__c cls : Trigger.new){
        class__c oldCls = Trigger.oldMap.get(cls.id);
        
        if(cls.Status__c != oldCls.Status__c && 
           (oldCls.Status__c == 'Reset' || oldCls.Status__c == 'New')){
               classIds.add(cls.Id);
           }
    }
    
    if(!classIds.isEmpty()){
        List<Student__c> std = [
            SELECT id FROM 
            Student__c
            WHERE class__c IN :classIds
        ];
        if(!std.isEmpty()){
            delete std;
        }
    }
}