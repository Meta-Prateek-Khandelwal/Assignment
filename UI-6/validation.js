const form = document.getElementById('emp');
console.log(form.value)
const nameInput = document.getElementById('Name');
console.log(nameInput.value)
const emailInput = document.getElementById('email');
console.log(emailInput.value)
const passwordInput = document.getElementById('password');
console.log(passwordInput.value)
const contactInput = document.getElementById('contact');
console.log(contactInput.value)

document.getElementById("final-pass").style.display = "block";


function validateName() {
  const nameValue = document.getElementById("Name").value.trim();
  const greetingMessage = document.getElementById("greetingMessage");
  const errorDiv = document.getElementById("name-error");

  const nameRegex = /^([a-zA-Z]+[\s-']?){1,3}$/;
  console.log(errorDiv);
  if (!nameRegex.test(nameValue) || nameValue.length < 2) {
    if (errorDiv) errorDiv.textContent = "invalid full name format.";
    return false;
  }

  if (errorDiv) errorDiv.textContent = "";
  greetingMessage.innerText = nameInput ? `Hi ${nameValue}! Can I know your gender?` : "Hi! Can I know your gender?";
  return true;
}

function validateEmail() {
  const emailValue = document.getElementById("email").value.trim();
  const erroremailDiv = document.getElementById("erroremailDiv");
  console.log(emailValue);
  console.log(emailValue.length);

  if (emailValue.length < 2) {
    erroremailDiv.textContent = "Enter Correct email";
    return false;
  }

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(emailValue)) {
    if (erroremailDiv) erroremailDiv.textContent = "invalid Email format.";
    return false;
  }

  if (erroremailDiv) erroremailDiv.textContent = "";
  return true;
}

function checkPassword() {
  const passwordInput = document.getElementById("password");
  const strengthText = document.getElementById("strength-text");
  const errorDiv = document.getElementById("password-error");
  const password = passwordInput.value;

  const hasUpperCase = /[A-Z]/.test(password);
  const hasLowerCase = /[a-z]/.test(password);
  const hasNumbers = /[0-9]/.test(password);
  const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
  const isLongEnough = password.length > 8;

  let strength = "Weak";
  let borderColor = "red";

  if (isLongEnough && hasUpperCase && hasLowerCase && hasNumbers && hasSpecialChar) {
    strength = "Strong";
    borderColor = "green";
    return true;
  } else if (isLongEnough && (hasUpperCase || hasLowerCase) && (hasNumbers || hasSpecialChar)) {
    strength = "Normal";
    borderColor = "orange";
    return true;
  }

  passwordInput.style.borderColor = borderColor;
  strengthText.textContent = `Password Strength: ${strength}`;
  strengthText.style.color = borderColor;

  errorDiv.textContent = "";
  return false;
}

function checkConfirmPassword() {
  const password = document.getElementById("password").value;
  const confirm = document.getElementById("conpassword").value;
  const errorDiv = document.getElementById("conpassword-error");

  if (!confirm) {
    errorDiv.textContent = "Confirm password is required.";
    return false;
  }

  if (password !== confirm) {
    errorDiv.textContent = "Passwords do not match.";
    return false;
  }

  errorDiv.textContent = "";
  return true;
}


function validateContact() {
  const phone = document.getElementById("phone").value;
  const errorDiv = document.getElementById("phone-error");
  const phoneRegex = /^[0-9]{10}$/;


  if (!phoneRegex.test(phone)) {
    errorDiv.textContent = "Enter a valid 10-digit number.";
    return false;
  }

  errorDiv.textContent = "";
  return true;
}

function generateEmployeeId() {
  const x = document.getElementById("PhoneField");
  x.style.display = 'none';
  const randomNum = Math.floor(1000 + Math.random() * 9000);
  return `EMP2025${randomNum}`;
}

function handleSubmit() {
  const empId = generateEmployeeId();
  document.getElementById(
    "employee-id-display"
  ).textContent = `Employee ID: ${empId}`;
  document.querySelector(".add_employee_form").style.display = "none";
  document.getElementById("newVec").style.display = "block";
  document.getElementById("newVec").open = true;
}

function generateCarPlan() {
  document.getElementById("planDetails").innerHTML = `
 <h4 id="s-plan">Selected Plan: <span style="color:green">Car</span></h4>
 <table style="width:100%">
   <tr>
     <th>Cost</th>
     <th>Time</th>
   </tr>
   <tr>
     <td>20</td>
     <td>Daily</td>
   </tr>
   <tr>
     <td>500</td>
     <td>Monthly</td>
   </tr>
     <tr>
     <td>35000</td>
     <td>Yearly</td>
   </tr>
 </table>
   `;
}

function generateBikePlan() {
  document.getElementById("planDetails").innerHTML = `
 <h4 id="s-plan">Selected Plan: <span style="color:green">Motorcycle</span></h4>
 <ul>
   <li>$10 Daily</li>
   <li>$200 Monthly</li>
   <li>$1,000 Yearly</li>
 </ul>
   `;
}

function generateCyclePlan() {
  document.getElementById("planDetails").innerHTML = `
 <h4 id="s-plan">Selected Plan: <span style="color:green">Cycle</span></h4>
 <ul>
   <li>$5 Daily</li>
   <li>$100 Monthly</li>
   <li>$500 Yearly</li>
 </ul>
   `;
}

// const pricingINR = {
//   Cycle: { Daily: 10, Monthly: 200, Yearly: 2000 },
//   Motorcycle: { Daily: 30, Monthly: 500, Yearly: 5000 },
//   FourWheeler: { Daily: 50, Monthly: 800, Yearly: 8000 }
// };

// const exchangeRates = {
//   INR: 1,
//   USD: 0.012,
//   YEN: 1.7
// };

function calculateAndShowPass() {
  const vehicle = document.querySelector('input[name="vehicle-type"]:checked')?.value;
  const plan = document.querySelector('input[name="plan-type"]:checked')?.value;
  const currency = document.getElementById("currency-select")?.value;

  console.log(vehicle);
  console.log(plan);
  console.log(currency);

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
  console.log(inrPrice);
  const convertedPrice = (inrPrice * exchangeRates[currency]).toFixed(2);
  const usdPrice = (inrPrice * exchangeRates["USD"]).toFixed(2);

  document.getElementById("final-pass").innerHTML = `
    <p>Selected Vehicle: ${vehicle}</p>
    <p>Plan Type: ${plan}</p>
    <p>Price in ${currency}: ${convertedPrice}</p>
    <p style="color: green;">Stored Price (USD): $${usdPrice}</p>
`;
}

