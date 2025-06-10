public class OpportunityScheduler implements Schedulable{
    public void execute(SchedulableContext sc){
        OpportunityReminder.sendReminder();
    }
}