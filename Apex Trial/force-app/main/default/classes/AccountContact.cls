public class AccountContact {
    public static void updateAccountContactIds(Id contactId){
       Contact con = [SELECT Id,Name,AccountId 
                      FROM Contact 
                      WHERE Id = :contactId LIMIT 1] ;

        if(con.AccountId == null){
            System.debug('Contact is not associated with any account');
            return ;
        }
        
        Account acc = [SELECT Id,Name,ContactIds__c FROM Account WHERE Id = :con.AccountId LIMIT 1];

        if(String.isBlank(acc.ContactIds__c)){
            List<Contact> relatedContacts = [SELECT Id,Name 
                                            FROM Contact 
                                            WHERE AccountId = :acc.Id]; 

            List<String> contactIds = new List<String>();

            for(Contact con : relatedContacts){
                contactIds.add(c.Id);
            }

            acc.ContactIds__c = String.join(contactIds,', ');
        }else{
            
            List<String> existingIds  = acc.ContactIDs__c.split(', ');
            if(!existingIds.contains(contactId)){
                existingIds.add(contactId);
                acc.ContactIds__c = String.join(existingIds,', ');
            }
        }

        update acc;
    }
}