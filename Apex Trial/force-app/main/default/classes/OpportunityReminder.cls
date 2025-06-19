// Create a Scheduler to mail to Opportunity Owner, if not modified for last 30 days.
public class OpportunityReminder{
    
    public static void sendReminder(){
        DateTime last30Days = System.now().addDays(-30);
        
        List<Opportunity> opportunityList = [
            SELECT Owner.Email,LastModifiedDate 
            FROM Opportunity 
            WHERE LastModifiedDate <= :last30Days
        ];
        
        System.debug('opportunityList' +opportunityList);
        
        List<Messaging.SingleEmailMessage> mails = new List<Messaging.SingleEmailMessage>();

        for(Opportunity opp : opportunityList){
            if(opp.Owner.Email != null){
                Messaging.SingleEmailMessage email = new Messaging.SingleEmailMessage();
                email.setToAddresses(new String[] {opp.Owner.Email});
                email.setSubject('Reminder: Oppertunity"'+opp.Name+'" has not been updated');
                email.setPlainTextBody(
                    'Hello '+opp.Owner.Name+',\n'+ ',\n\n '+
                    'This is a reminder to update the opportunity "'+opp.Name+'" .\n\n'+
                    'please take necessary action now.\n\n'+
                    'Thanks,\n'+''
                );
                mails.add(email);
            }

            if(!mails.isEmpty()){
                Messaging.sendEmail(mails);
            }
        }
    }
}