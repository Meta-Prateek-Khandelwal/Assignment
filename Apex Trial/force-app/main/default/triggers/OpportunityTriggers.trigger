trigger OpportunityTriggers on Opportunity (before update) {
	
    for(Opportunity opp : Trigger.new){
        Opportunity oldOpp = Trigger.oldMap.get(opp.id);
        if(opp.StageName != oldOpp.StageName && (Opp.StageName == 'CLOSED_WON' || Opp.StageName == 'CLOSED_WON')){
               opp.CloseDate = Date.Today();      		
        }
    }
    
}