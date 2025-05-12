document.addEventListener("DOMContentLoaded", () => {
  const nameInput = document.getElementById("Name");
  const genderField = document.getElementById("genderField");
  const greetingMessage = document.getElementById("greetingMessage");

  nameInput.addEventListener("keypress", (event) => {
      if (event.key === "Enter") {
          event.preventDefault();
          const nameValue = nameInput.value.trim();

          if (nameValue) {
              greetingMessage.innerText = `Hi ${nameValue}! Can I know your gender?`;
              document.getElementById("nameField").style.display = "none";
              genderField.style.display = "block";
          } else {
              alert("Please enter your name before proceeding.");
          }
      }
  });
});

// **Arrow Function for Form Validations**
const validateName = () => {
  const nameValue = document.getElementById("Name").value.trim();
  const errorDiv = document.getElementById("name-error");
  const nameRegex = /^([a-zA-Z]+[\s-']?){1,3}$/;

  errorDiv.textContent = !nameRegex.test(nameValue) || nameValue.length < 2 
      ? "Invalid full name format." : "";

  return nameRegex.test(nameValue) && nameValue.length >= 2;
};

const validateEmail = () => {
  const emailValue = document.getElementById("email").value.trim();
  const erroremailDiv = document.getElementById("erroremailDiv");
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  erroremailDiv.textContent = emailValue.length < 2 || !emailRegex.test(emailValue)
      ? "Invalid Email format." : "";
  
  return emailValue.length >= 2 && emailRegex.test(emailValue);
};

const checkPassword = () => {
  const password = document.getElementById("password").value;
  const errorDiv = document.getElementById("password-error");
  const strengthText = document.getElementById("strength-text");

  const hasUpperCase = /[A-Z]/.test(password);
  const hasLowerCase = /[a-z]/.test(password);
  const hasNumbers = /[0-9]/.test(password);
  const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
  const isLongEnough = password.length > 8;

  let strength = "Weak", borderColor = "red";

  if (isLongEnough && hasUpperCase && hasLowerCase && hasNumbers && hasSpecialChar) {
      strength = "Strong";
      borderColor = "green";
  } else if (isLongEnough && (hasUpperCase || hasLowerCase) && (hasNumbers || hasSpecialChar)) {
      strength = "Normal";
      borderColor = "orange";
  }

  document.getElementById("password").style.borderColor = borderColor;
  strengthText.textContent = `Password Strength: ${strength}`;
  strengthText.style.color = borderColor;

  return strength === "Strong" || strength === "Normal";
};

const checkConfirmPassword = () => {
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("conpassword").value;
  const errorDiv = document.getElementById("conpassword-error");

  errorDiv.textContent = !confirmPassword
      ? "Confirm password is required." 
      : password !== confirmPassword
      ? "Passwords do not match."
      : "";

  return confirmPassword && password === confirmPassword;
};

const validateContact = () => {
  const phone = document.getElementById("phone").value;
  const errorDiv = document.getElementById("phone-error");
  const phoneRegex = /^[0-9]{10}$/;

  errorDiv.textContent = !phoneRegex.test(phone)
      ? "Enter a valid 10-digit number."
      : "";

  return phoneRegex.test(phone);
};

// **Arrow Function for Employee ID Generation**
const generateEmployeeId = () => {
  document.getElementById("PhoneField").style.display = 'none';
  return `EMP2025${Math.floor(1000 + Math.random() * 9000)}`;
};

// **Arrow Function for Handling Submission**
const handleSubmit = () => {
  const empId = generateEmployeeId();
  document.getElementById("employee-id-display").textContent = `Employee ID: ${empId}`;
  document.querySelector(".add_employee_form").style.display = "none";
  document.getElementById("newVec").style.display = "block";
  document.getElementById("newVec").open = true;
};

// **Arrow Function for Vehicle Pricing Calculation**
const calculateAndShowPass = () => {
  const vehicle = document.querySelector('input[name="vehicle-type"]:checked')?.value;
  const plan = document.querySelector('input[name="plan-type"]:checked')?.value;
  const currency = document.getElementById("currency-select")?.value;

  if (!vehicle || !plan) {
      document.getElementById("final-pass").innerText = "Please select vehicle and plan type.";
      return;
  }

  const pricingINR = {
      Cycle: { Daily: 10, Monthly: 200, Yearly: 2000 },
      Motorcycle: { Daily: 30, Monthly: 500, Yearly: 5000 },
      FourWheeler: { Daily: 50, Monthly: 800, Yearly: 8000 }
  };

  const exchangeRates = {
      INR: 1,
      USD: 0.012,
      YEN: 1.7
  };

  const inrPrice = pricingINR[vehicle][plan];
  const convertedPrice = (inrPrice * exchangeRates[currency]).toFixed(2);
  const usdPrice = (inrPrice * exchangeRates["USD"]).toFixed(2);

  document.getElementById("final-pass").innerHTML = `
      <p>Selected Vehicle: ${vehicle}</p>
      <p>Plan Type: ${plan}</p>
      <p>Price in ${currency}: ${convertedPrice}</p>
      <p style="color: green;">Stored Price (USD): $${usdPrice}</p>
  `;
};