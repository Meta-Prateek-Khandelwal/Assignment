trigger AccountTriggers on Account (after update) {
	Set<Id> changedAccounts = new Set<Id>();
    
    for(Account acc : Trigger.new){
        Account oldacc = Trigger.oldMap.get(acc.id);
        
        if(acc.billingCity != oldacc.BillingCity){
            changedAccounts.add(acc.id);
        }
    }
    
    if(!changedAccounts.isEmpty()){
        List<Contact> contactsToUpdate = new List<Contact>();
        
        for(Contact con: [
            SELECT id, AccountId, mailingCity, Account.billingCity
            FROM Contact 
            WHERE AccountId IN :changedAccounts
        ]){
            contactsToUpdate.add(con);
        }
        
        if(!contactsToUpdate.isEmpty()){
            update contactsToUpdate;
        }
    }
}