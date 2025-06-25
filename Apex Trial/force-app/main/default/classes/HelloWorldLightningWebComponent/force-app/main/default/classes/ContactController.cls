public with sharing class ContactController {
    @AuraEnabled
    public static Contact saveTeacherRecord(Contact contactRecord){
        try {
            insert contactRecord;
            return contactRecord;
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }
}