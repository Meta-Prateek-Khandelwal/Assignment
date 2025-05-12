const showField = (nextFieldId, currFieldId) => {
    console.log(`Moving from ${currFieldId} to ${nextFieldId}`);
    const currField = document.getElementById(currFieldId);
    const nextField = document.getElementById(nextFieldId);
  
    if (!currField || !nextField) {
      console.error("Error: Fields not found!", currFieldId, nextFieldId);
      return;
    }
  
    const input = currField.querySelector('input');
    if (input?.value.trim() === '') {
      alert('Please fill out this field before proceeding.');
      return;
    }
  
    if (currFieldId === "nameField" && !validateName()) return;
    if (currFieldId === "emailField" && !validateEmail()) return;
    if (currFieldId === "passwordField" && !checkPassword()) return;
    if (currFieldId === "cpasswordField" && !checkConfirmPassword()) return;
  
    currField.style.display = 'none';
    nextField.style.display = 'block';
  
    console.log(`Successfully moved to ${nextFieldId}`);
  };