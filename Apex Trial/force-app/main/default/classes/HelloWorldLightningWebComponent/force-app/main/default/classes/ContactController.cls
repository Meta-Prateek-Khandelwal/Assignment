public with sharing class ContactController {
    @AuraEnabled
    public static Contact saveContact(Contact contactObj){
        try {
            insert contactObj;
            return contactObj;
        } catch (Exception e) {
            throw new AuraHandledException('Error saving contact: '+e.getMessage());
        }
    }
}