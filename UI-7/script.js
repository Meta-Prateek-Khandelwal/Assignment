const showField = (nextFieldId, currFieldId) => {
    const currField = document.getElementById(currFieldId);
    const nextField = document.getElementById(nextFieldId);
    const input = currField?.querySelector('input');

    if (input?.value.trim() === '') {
        alert('Please fill out this field before proceeding.');
        return;
    }

    if (
        (currFieldId === "nameField" && !validateName()) ||
        (currFieldId === "emailField" && !validateEmail()) ||
        (currFieldId === "passwordField" && !checkPassword()) ||
        (currFieldId === "cpasswordField" && !checkConfirmPassword())
    ) {
        return;
    }

    currField?.style.display = 'none';
    nextField?.style.display = 'block';
};