import { LightningElement, wire, track } from 'lwc';
import getContactInfo from '@salesforce/apex/ContactDataController.getContactInfo';

export default class JsonResultViewer extends LightningElement {
    @track jsonResult;

    @wire(getContactInfo)
    wiredContact({ data, error }) {
        if (data) {
            this.jsonResult = JSON.stringify(data, null, 2); 
        } else if (error) {
            this.jsonResult = 'Error: ' + JSON.stringify(error, null, 2);
        }
    }
}