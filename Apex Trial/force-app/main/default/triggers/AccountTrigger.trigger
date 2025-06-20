trigger AccountTrigger on SOBJECT (before update) {
    for(Account a: Trigger.new){
        if(a.Update_Counts__c == null){
            a.Update_Counts__c = 0;
        }
        a.Update_Counts__c += 1;
    }
}