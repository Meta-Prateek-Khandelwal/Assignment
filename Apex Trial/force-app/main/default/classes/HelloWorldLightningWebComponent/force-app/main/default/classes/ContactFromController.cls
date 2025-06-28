public with sharing class ContactFromController {
    @AuraEnabled
    public static Contact saveContactToServer(String firstName, String lastName, String email, String phone, String fax) {
        Contact con = new Contact(
            FirstName = firstName,
            LastName = lastName,
            Email = email,
            Phone = phone,
            Fax = fax
        );
        insert con;
        return con;
    }
}