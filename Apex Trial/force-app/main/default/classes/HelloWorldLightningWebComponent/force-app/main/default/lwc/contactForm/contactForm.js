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