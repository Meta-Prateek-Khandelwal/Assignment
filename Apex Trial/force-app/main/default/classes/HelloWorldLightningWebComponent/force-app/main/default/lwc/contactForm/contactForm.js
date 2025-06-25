import { LightningElement, track } from 'lwc';
import { createRecord } from 'lightning/uiRecordApi';
import CONTACT_OBJECT from '@salesforce/schema/Contact';
import FIRSTNAME_FIELD from '@salesforce/schema/Contact.FirstName';
import LASTNAME_FIELD from '@salesforce/schema/Contact.LastName';
import EMAIL_FIELD from '@salesforce/schema/Contact.Email';
import PHONE_FIELD from '@salesforce/schema/Contact.Phone';
import FAX_FIELD from '@salesforce/schema/Contact.Fax';

export default class CreateContactForm extends LightningElement {
    @track firstName = '';
    @track lastName = '';
    @track email = '';
    @track phone = '';
    @track fax = '';
    @track message = '';
    @track isSuccess = false;
    @track isError = false;

    handleChange(event) {
        const {name, value} = event.target;
        this[name] = value;
    }

    saveContact(){
        const fields = {};
        fields[FIRSTNAME_FIELD.fieldApiName] = this.firstName;
        fields[LASTNAME_FIELD.fieldApiName] = this.lastName;
        fields[EMAIL_FIELD.fieldApiName] = this.email;
        fields[PHONE_FIELD.fieldApiName] = this.phone;
        fields[FAX_FIELD.fieldApiName] = this.fax;
    
        const recordInput = { apiName: CONTACT_OBJECT.objectApiName, fields };
        createRecord(recordInput)   
        .then(Contact => {
            this.isSuccess = true; 
            this.isError = false; 
            this.message = 'Contact saved successfully! Record Id: '+Contact.id;

            this.firstName = '';
            this.lastName = '';
            this.email = '';
            this.phone = '';
            this.fax = '';
        })
        .catch(error => {
            this.isSuccess = false;
            this.isError = true;
            this.message = 'Error: '+error.body.message || 'unkonwn error';
        });
    }
}
