function showField(nextFieldId, currFieldId){
    const currField = document.getElementById(currFieldId);
    const nextField = document.getElementById(nextFieldId);
    const input = currField.querySelector('input');

    if(input && input.value.trim() === ''){
        alert('Please fill out this field before proceeding.');
        return;
    }

    if (currFieldId === "nameField" && !validateName()) return;
    if (currFieldId === "emailField" && !validateEmail()) return;
    if (currFieldId === "passwordField" && !checkPassword()) return;
    if (currFieldId === "cpasswordField" && !checkConfirmPassword()) return;
    // if (currFieldId === "cpasswordField" && !checkContact()) return;
    
    if(currField){
        currField.style.display = 'none';
    }

    if(nextField){
        nextField.style.display = 'block';
    }
}
