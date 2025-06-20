// Create two new fields of type Lookup in Opportunity named BillToContact(Contact) and 
// Manager(Account). Create a function in class, which takes List<Opportunity> as argument and check if
// BillToContact field is not Blank and Manager is Blank then populate Manager field by related account
// which is linked with the contact given in BillToContact field.

public class OpportunityHandler {
    public static void updateManagerField(List<Opportunity> oppList){
        Set<Id> contactIds = new Set<Id>();
        for(Opportunity opp: oppList){
            if(opp.billToContact__c != null && opp.manager__c == null){
                contactIds.add(opp.billToContact__c);
            }
        }
        
        Map<Id, Contact> contactMap = new Map<Id, Contact>(
        	[SELECT Id, AccountId 
             FROM Contact 
            WHERE id IN :contactIds]	
        );
        
        List<Opportunity> oppsToUpdate = new List<Opportunity>();
        for(Opportunity opp: oppList){
            if(opp.billToContact__c != null && opp.manager__c == null){
                Contact con = contactMap.get(opp.billToContact__c);
                
                if(con != null && con.accountId != null){
                    opp.manager__c = con.accountId;
                    oppsToUpdate.add(opp);
                }
            } 
        }

        
        
        if(!oppsToUpdate.isEmpty()){
            update oppsToUpdate;
        }
    }
}