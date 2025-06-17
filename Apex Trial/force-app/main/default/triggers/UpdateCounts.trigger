// Write a after update trigger on Account to increment Custom Field 'Update Counts' with 1 whenever the account record is updated.
trigger UpdateCounts on SOBJECT (before update) {
    for(Account a: Trigger.new){
        if(a.Update_Counts__c == null){
            a.Update_Counts__c = 0;
        }
        a.Update_Counts__c += 1;
    }
}