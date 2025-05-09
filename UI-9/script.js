function showField(nextFieldId: string, currFieldId: string): void {
    try {
      const currField = document.getElementById(currFieldId) as HTMLDivElement;
      const nextField = document.getElementById(nextFieldId) as HTMLDivElement;
      const input = currField?.querySelector("input") as HTMLInputElement;
      const errorDiv = currField?.querySelector(".error-message") as HTMLDivElement;
  
      if (input && input.value.trim() === "") {
        if (errorDiv) errorDiv.textContent = "Please fill out this field before proceeding.";
        return;
      } else if (errorDiv) {
        errorDiv.textContent = "";
      }
  
      if (currFieldId === "nameField" && !validateName()) {
        throw new Error("Invalid Name");
      }
      if (currFieldId === "emailField" && !validateEmail()) {
        throw new Error("Invalid Email");
      }
      if (currFieldId === "passwordField" && !checkPassword()) {
        throw new Error("Invalid Password");
      }
      if (currFieldId === "cpasswordField" && !checkConfirmPassword()) {
        throw new Error("Password Mismatch");
      }
  
      currField.style.display = "none";
      nextField.style.display = "block";
    } catch (error) {
      console.error("Validation Error:", error);
      alert(error.message); // Shows the error as an alert message
    }
  }