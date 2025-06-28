import { LightningElement, track } from 'lwc';
import saveContact from '@salesforce/apex/ContactController.saveContact';

export default class ContactForm extends LightningElement {
    @track firstName = '';
    @track lastName = '';
    @track email = '';
    @track phone = '';
    @track fax = '';
    @track message = '';
    @track messageClass = '';
    @track messageText = '';

    handleFirstNameChange(event) {
        this.firstName = event.target.value;
    }

    handleLastNameChange(event) {
        this.lastName = event.target.value;
    }

    handleEmailChange(event) {
        this.email = event.target.value;
    }

    handlePhoneChange(event) {
        this.phone = event.target.value;
    }

    handleFaxChange(event) {
        this.fax = event.target.value;
    }

    saveContact() {
        let contactRecord = {
            FirstName: this.firstName,
            LastName: this.lastName,
            Email: this.email,
            Phone: this.phone,
            Fax: this.fax
        };

        saveContact({ contactObj: contactRecord })
            .then(result => {
                this.message = 'Contact "' + result.LastName + '" was successfully saved!';
                this.messageText = 'Name: ' + result.FirstName + ' ' + result.LastName  + '\n' + 'Email:' + result.Email + '\n' + ' Phone:' + result.Phone + '\n ' + 'Fax:' + result.Fax;
                console.log(this.messageText);
                

                this.messageClass = 'slds-text-color_success';
                

                this.firstName = '';
                this.lastName = '';
                this.email = '';
                this.phone = '';
                this.fax = '';
            })
            .catch(error => {
                this.message = 'Error saving contact: ' + (error.body ? error.body.message : error.message);
                this.messageClass = 'slds-text-color_error';
            }
        );
    }
}