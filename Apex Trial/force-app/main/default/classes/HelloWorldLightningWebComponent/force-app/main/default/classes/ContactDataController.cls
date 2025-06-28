public with sharing class ContactDataController {
    @AuraEnabled(cacheable=true)
    public static List<Contact> getContactInfo() {
        return [SELECT Id, FirstName, LastName, Email, Phone FROM Contact LIMIT 10];
    }
}