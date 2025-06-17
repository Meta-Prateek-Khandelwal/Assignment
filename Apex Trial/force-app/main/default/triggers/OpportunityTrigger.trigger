trigger OpportunityTrigger on Opportunity (after update) {
    if(Trigger.isAfter && Trigger.isUpdate){
        OpportunityHandler.updateManagerField(Trigger.new);
    }
}