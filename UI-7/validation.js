const validateName = () => {
  const nameValue = document.getElementById("Name").value.trim();
  const greetingMessage = document.getElementById("greetingMessage");
  const errorDiv = document.getElementById("name-error");

  const nameRegex = /^([a-zA-Z]+[\s-']?){1,3}$/;
  
  if (!nameRegex.test(nameValue) || nameValue.length < 2) {
    errorDiv.textContent = "Invalid full name format.";
    return false;
  }

  errorDiv.textContent = "";
  greetingMessage.innerText = nameValue ? `Hi ${nameValue}! Can I know your gender?` : "Hi! Can I know your gender?";
  return true;
};

const validateEmail = () => {
  const emailValue = document.getElementById("email").value.trim();
  const errorEmailDiv = document.getElementById("erroremailDiv");

  if (emailValue.length < 2) {
    errorEmailDiv.textContent = "Enter a correct email";
    return false;
  }

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(emailValue)) {
    errorEmailDiv.textContent = "Invalid email format.";
    return false;
  }

  errorEmailDiv.textContent = "";
  return true;
};

const checkPassword = () => {
  const passwordInput = document.getElementById("password");
  const strengthText = document.getElementById("strength-text");
  const password = passwordInput.value;

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

  passwordInput.style.borderColor = borderColor;
  strengthText.textContent = `Password Strength: ${strength}`;
  strengthText.style.color = borderColor;

  return strength === "Strong";
};

const checkConfirmPassword = () => {
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("conpassword").value;
  const errorDiv = document.getElementById("conpassword-error");

  if (!confirmPassword) {
    errorDiv.textContent = "Confirm password is required.";
    return false;
  }

  if (password !== confirmPassword) {
    errorDiv.textContent = "Passwords do not match.";
    return false;
  }

  errorDiv.textContent = "";
  return true;
};

const validateContact = () => {
  const phone = document.getElementById("phone").value;
  const errorDiv = document.getElementById("phone-error");
  const phoneRegex = /^[0-9]{10}$/;

  if (!phoneRegex.test(phone)) {
    errorDiv.textContent = "Enter a valid 10-digit number.";
    return false;
  }

  errorDiv.textContent = "";
  return true;
};

const generateEmployeeId = () => `EMP2025${Math.floor(1000 + Math.random() * 9000)}`;

const handleSubmit = () => {
  const empId = generateEmployeeId();
  document.getElementById("employee-id-display").textContent = `Employee ID: ${empId}`;
  document.querySelector(".add_employee_form").style.display = "none";
  document.getElementById("newVec").style.display = "block";
  document.getElementById("newVec").open = true;
};

const generateVehiclePlan = (vehicle, costDaily, costMonthly, costYearly) => {
  document.getElementById("planDetails").innerHTML = `
    <h4 id="s-plan">Selected Plan: <span style="color:green">${vehicle}</span></h4>
    <table style="width:100%">
      <tr>
        <th>Cost</th>
        <th>Time</th>
      </tr>
      <tr>
        <td>${costDaily}</td>
        <td>Daily</td>
      </tr>
      <tr>
        <td>${costMonthly}</td>
        <td>Monthly</td>
      </tr>
      <tr>
        <td>${costYearly}</td>
        <td>Yearly</td>
      </tr>
    </table>
  `;
};

const generateCarPlan = () => generateVehiclePlan("Car", 20, 500, 35000);
const generateBikePlan = () => generateVehiclePlan("Motorcycle", 10, 200, 1000);
const generateCyclePlan = () => generateVehiclePlan("Cycle", 5, 100, 500);

const pricingINR = {
  Cycle: { Daily: 10, Monthly: 200, Yearly: 2000 },
  Motorcycle: { Daily: 30, Monthly: 500, Yearly: 5000 },
  FourWheeler: { Daily: 50, Monthly: 800, Yearly: 8000 }
};

const exchangeRates = { INR: 1, USD: 0.012, YEN: 1.7 };

const calculateAndShowPass = () => {
  const vehicle = document.querySelector('input[name="vehicle-type"]:checked')?.value;
  const plan = document.querySelector('input[name="plan-type"]:checked')?.value;
  const currency = document.getElementById("currency-select")?.value;

  if (!vehicle || !plan) {
    document.getElementById("final-pass").innerText = "Please select vehicle and plan type.";
    return;
  }

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