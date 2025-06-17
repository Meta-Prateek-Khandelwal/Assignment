trigger Account on Account (after insert, after update) {
	List<Opportunity> oppList = new List<Opportunity>();
    
    for(Account acc: Trigger.new){
        if(acc.industry == 'Agriculture'){
            oppList.add(new Opportunity(
            Name = acc.name +' Opp', 
            AccountId = acc.id, 
            stageName = 'prospecting',
            closeDate = Date.today().addDays(7)));
        } 
    }
    
    if(!oppList.isEmpty()){
        insert oppList;
    }
}