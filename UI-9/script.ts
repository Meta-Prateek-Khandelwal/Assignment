function showField(nextFieldId: string, currFieldId: string): void {
    const currField = document.getElementById(currFieldId) as HTMLElement | null;
    const nextField = document.getElementById(nextFieldId) as HTMLElement | null;
    const input = currField?.querySelector('input') as HTMLInputElement | null;

    if (input && input.value.trim() === '') {
        alert('Please fill out this field before proceeding.');
        return;
    }

    if (currFieldId === "nameField" && !validateName()) return;
    if (currFieldId === "emailField" && !validateEmail()) return;
    if (currFieldId === "passwordField" && !checkPassword()) return;
    if (currFieldId === "cpasswordField" && !checkConfirmPassword()) return;

    if (currField) {
        currField.style.display = 'none';
    }

    if (nextField) {
        nextField.style.display = 'block';
    }
}

interface Pricing {
    [key: string]: Record<string, number>; 
  }
  
  const pricingINR: Pricing = {
    Cycle: { Daily: 10, Monthly: 200, Yearly: 2000 },
    Motorcycle: { Daily: 30, Monthly: 500, Yearly: 5000 },
    FourWheeler: { Daily: 50, Monthly: 800, Yearly: 8000 }
  };
  
  const exchangeRates: Record<string, number> = {
    INR: 1,
    USD: 0.012,
    YEN: 1.7
  };
  
  const nameInput = document.getElementById("name") as HTMLInputElement | null;
  const emailInput = document.getElementById("email") as HTMLInputElement | null;
  const passwordInput = document.getElementById("password") as HTMLInputElement | null;
  const confirmPasswordInput = document.getElementById("conpassword") as HTMLInputElement | null;
  const contactInput = document.getElementById("contact") as HTMLInputElement | null;
  
  function validateName(): boolean {
    const nameValue = nameInput?.value.trim();
    const errorDiv = document.getElementById("name-error") as HTMLElement | null;
    const nameRegex = /^([a-zA-Z]+[\s-']?){1,3}$/;
  
    if (!nameValue || !nameRegex.test(nameValue) || nameValue.length < 2) {
      if (errorDiv) errorDiv.textContent = "Invalid full name format.";
      return false;
    }
  
    if (errorDiv) errorDiv.textContent = "";
    return true;
  }
  
  function validateEmail(): boolean {
    const emailValue = emailInput?.value.trim();
    const errorDiv = document.getElementById("erroremailDiv") as HTMLElement | null;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  
    if (!emailValue || emailValue.length < 2 || !emailRegex.test(emailValue)) {
      if (errorDiv) errorDiv.textContent = "Invalid email format.";
      return false;
    }
  
    if (errorDiv) errorDiv.textContent = "";
    return true;
  }
  
  function checkPassword(): boolean {
    const password = passwordInput?.value;
    const strengthText = document.getElementById("strength-text") as HTMLElement | null;
  
    if (!password) return false;
  
    const hasUpperCase = /[A-Z]/.test(password);
    const hasLowerCase = /[a-z]/.test(password);
    const hasNumbers = /[0-9]/.test(password);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
    const isLongEnough = password.length >= 8;
  
    let strength = "Weak";
    let borderColor = "red";
  
    if (isLongEnough && hasUpperCase && hasLowerCase && hasNumbers && hasSpecialChar) {
      strength = "Strong";
      borderColor = "green";
    } else if (isLongEnough && (hasUpperCase || hasLowerCase) && (hasNumbers || hasSpecialChar)) {
      strength = "Normal";
      borderColor = "orange";
    }
  
    if (passwordInput) passwordInput.style.borderColor = borderColor;
    if (strengthText) {
      strengthText.textContent = `Password Strength: ${strength}`;
      strengthText.style.color = borderColor;
    }
  
    return strength === "Strong";
  }
  
  function checkConfirmPassword(): boolean {
    const password = passwordInput?.value;
    const confirmPassword = confirmPasswordInput?.value;
    const errorDiv = document.getElementById("conpassword-error") as HTMLElement | null;
  
    if (!confirmPassword || password !== confirmPassword) {
      if (errorDiv) errorDiv.textContent = "Passwords do not match.";
      return false;
    }
  
    if (errorDiv) errorDiv.textContent = "";
    return true;
  }
  
  function validateContact(): boolean {
    const phone = contactInput?.value;
    const errorDiv = document.getElementById("phone-error") as HTMLElement | null;
    const phoneRegex = /^[0-9]{10}$/;
  
    if (!phone || !phoneRegex.test(phone)) {
      if (errorDiv) errorDiv.textContent = "Enter a valid 10-digit number.";
      return false;
    }
  
    if (errorDiv) errorDiv.textContent = "";
    return true;
  }
  
  function generateEmployeeId(): string {
    return `EMP2025${Math.floor(1000 + Math.random() * 9000)}`;
  }
  
  // Handle Form Submission
  function handleSubmit(): void {
    const empId = generateEmployeeId();
    const employeeDisplay = document.getElementById("employee-id-display");
    if (employeeDisplay) {
      employeeDisplay.textContent = `Employee ID: ${empId}`;
    }
  }
  
  // Function to Calculate and Show Pass Cost
  function calculateAndShowPass(): void {
    const vehicle = (document.querySelector('input[name="vehicle-type"]:checked') as HTMLInputElement)?.value;
    const plan = (document.querySelector('input[name="plan-type"]:checked') as HTMLInputElement)?.value;
    const currency = (document.getElementById("currency-select") as HTMLSelectElement)?.value;
  
    if (!vehicle || !plan || !pricingINR[vehicle]) {
      const finalPassElement = document.getElementById("final-pass");
      if (finalPassElement) finalPassElement.innerText = "Invalid selection. Please check vehicle type and plan.";
      return;
    }
  
    const inrPrice = pricingINR[vehicle]?.[plan] ?? 0;

    const convertedPrice = (inrPrice * (exchangeRates[currency] ?? 1)).toFixed(2);
    const usdPrice = (inrPrice * exchangeRates["USD"]).toFixed(2);
  
    const finalPassElement = document.getElementById("final-pass");
    if (finalPassElement) {
      finalPassElement.innerHTML = `
        <p>Selected Vehicle: ${vehicle}</p>
        <p>Plan Type: ${plan}</p>
        <p>Price in ${currency}: ${convertedPrice}</p>
        <p style="color: green;">Stored Price (USD): $${usdPrice}</p>
      `;
    }
  }