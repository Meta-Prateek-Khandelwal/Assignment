public class OpportunityHandler {
    public static void updateManagerField(List<Opportunity> oppList){
        Set<Id> contactIds = new Set<Id>();
        for(Opportunity opp: oppList){
            if(opp.BillToContact__c != null && opp.Manager__c == null){
                contactIds.add(opp.BillToContact__c);
            }
        }
        
        Map<Id, Contact> contactMap = new Map<Id, Contact>(
        	[SELECT id, AccountId 
             From Contact 
            WHERE id IN :contactIds]	
        );
        
        List<Opportunity> oppsToUpdate = new List<Opportunity>();
        for(Opportunity opp: oppList){
            if(opp.BillToContact__c != null && opp.Manager__c == null){
                Contact con = ContactMap.get(opp.BillToContact__c);
                
                if(con != null && con.AccountId != null){
                    opp.Manager__c = con.AccountId;
                    oppsToUpdate.add(opp);
                }
            }
            
        }
        
        if(!oppsToUpdate.isEmpty()){
            Update oppsToUpdate;
        }
    }
}