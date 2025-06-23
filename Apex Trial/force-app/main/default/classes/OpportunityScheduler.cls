global class OpportunityScheduler implements Schedulable{
    global void execute(SchedulableContext sc){
        OpportunityReminder.sendReminder();
    }
}