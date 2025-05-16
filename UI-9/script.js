function showField(nextFieldId, currFieldId) {
    var currField = document.getElementById(currFieldId);
    var nextField = document.getElementById(nextFieldId);
    var input = currField === null || currField === void 0 ? void 0 : currField.querySelector('input');
    if (input && input.value.trim() === '') {
        alert('Please fill out this field before proceeding.');
        return;
    }
    if (currFieldId === "nameField" && !validateName())
        return;
    if (currFieldId === "emailField" && !validateEmail())
        return;
    if (currFieldId === "passwordField" && !checkPassword())
        return;
    if (currFieldId === "cpasswordField" && !checkConfirmPassword())
        return;
    // if (currFieldId === "cpasswordField" && !checkContact()) return;
    if (currField) {
        currField.style.display = 'none';
    }
    if (nextField) {
        nextField.style.display = 'block';
    }
}
// Pricing Data
var pricingINR = {
    Cycle: { Daily: 10, Monthly: 200, Yearly: 2000 },
    Motorcycle: { Daily: 30, Monthly: 500, Yearly: 5000 },
    FourWheeler: { Daily: 50, Monthly: 800, Yearly: 8000 }
};
// Exchange Rates Data
var exchangeRates = {
    INR: 1,
    USD: 0.012,
    YEN: 1.7
};
// Get Form Elements (Ensure they exist to prevent errors)
var nameInput = document.getElementById("name");
var emailInput = document.getElementById("email");
var passwordInput = document.getElementById("password");
var confirmPasswordInput = document.getElementById("conpassword");
var contactInput = document.getElementById("contact");
// Function to Validate Name
function validateName() {
    var nameValue = nameInput === null || nameInput === void 0 ? void 0 : nameInput.value.trim();
    var errorDiv = document.getElementById("name-error");
    var nameRegex = /^([a-zA-Z]+[\s-']?){1,3}$/;
    if (!nameValue || !nameRegex.test(nameValue) || nameValue.length < 2) {
        if (errorDiv)
            errorDiv.textContent = "Invalid full name format.";
        return false;
    }
    if (errorDiv)
        errorDiv.textContent = "";
    return true;
}
// Function to Validate Email
function validateEmail() {
    var emailValue = emailInput === null || emailInput === void 0 ? void 0 : emailInput.value.trim();
    var errorDiv = document.getElementById("erroremailDiv");
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailValue || emailValue.length < 2 || !emailRegex.test(emailValue)) {
        if (errorDiv)
            errorDiv.textContent = "Invalid email format.";
        return false;
    }
    if (errorDiv)
        errorDiv.textContent = "";
    return true;
}
// Function to Validate Password
function checkPassword() {
    var password = passwordInput === null || passwordInput === void 0 ? void 0 : passwordInput.value;
    var strengthText = document.getElementById("strength-text");
    if (!password)
        return false;
    var hasUpperCase = /[A-Z]/.test(password);
    var hasLowerCase = /[a-z]/.test(password);
    var hasNumbers = /[0-9]/.test(password);
    var hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
    var isLongEnough = password.length >= 8;
    var strength = "Weak";
    var borderColor = "red";
    if (isLongEnough && hasUpperCase && hasLowerCase && hasNumbers && hasSpecialChar) {
        strength = "Strong";
        borderColor = "green";
    }
    else if (isLongEnough && (hasUpperCase || hasLowerCase) && (hasNumbers || hasSpecialChar)) {
        strength = "Normal";
        borderColor = "orange";
    }
    if (passwordInput)
        passwordInput.style.borderColor = borderColor;
    if (strengthText) {
        strengthText.textContent = "Password Strength: ".concat(strength);
        strengthText.style.color = borderColor;
    }
    return strength === "Strong";
}
// Function to Validate Confirm Password
function checkConfirmPassword() {
    var password = passwordInput === null || passwordInput === void 0 ? void 0 : passwordInput.value;
    var confirmPassword = confirmPasswordInput === null || confirmPasswordInput === void 0 ? void 0 : confirmPasswordInput.value;
    var errorDiv = document.getElementById("conpassword-error");
    if (!confirmPassword || password !== confirmPassword) {
        if (errorDiv)
            errorDiv.textContent = "Passwords do not match.";
        return false;
    }
    if (errorDiv)
        errorDiv.textContent = "";
    return true;
}
// Function to Validate Contact Number
function validateContact() {
    var phone = contactInput === null || contactInput === void 0 ? void 0 : contactInput.value;
    var errorDiv = document.getElementById("phone-error");
    var phoneRegex = /^[0-9]{10}$/;
    if (!phone || !phoneRegex.test(phone)) {
        if (errorDiv)
            errorDiv.textContent = "Enter a valid 10-digit number.";
        return false;
    }
    if (errorDiv)
        errorDiv.textContent = "";
    return true;
}
// Function to Generate Employee ID
function generateEmployeeId() {
    return "EMP2025".concat(Math.floor(1000 + Math.random() * 9000));
}
// Handle Form Submission
function handleSubmit() {
    var empId = generateEmployeeId();
    var employeeDisplay = document.getElementById("employee-id-display");
    if (employeeDisplay) {
        employeeDisplay.textContent = "Employee ID: ".concat(empId);
    }
}
// Function to Calculate and Show Pass Cost
function calculateAndShowPass() {
    var _a, _b, _c, _d, _e, _f;
    var vehicle = (_a = document.querySelector('input[name="vehicle-type"]:checked')) === null || _a === void 0 ? void 0 : _a.value;
    var plan = (_b = document.querySelector('input[name="plan-type"]:checked')) === null || _b === void 0 ? void 0 : _b.value;
    var currency = (_c = document.getElementById("currency-select")) === null || _c === void 0 ? void 0 : _c.value;
    if (!vehicle || !plan || !pricingINR[vehicle]) {
        var finalPassElement_1 = document.getElementById("final-pass");
        if (finalPassElement_1)
            finalPassElement_1.innerText = "Invalid selection. Please check vehicle type and plan.";
        return;
    }
    var inrPrice = (_e = (_d = pricingINR[vehicle]) === null || _d === void 0 ? void 0 : _d[plan]) !== null && _e !== void 0 ? _e : 0;
    var convertedPrice = (inrPrice * ((_f = exchangeRates[currency]) !== null && _f !== void 0 ? _f : 1)).toFixed(2);
    var usdPrice = (inrPrice * exchangeRates["USD"]).toFixed(2);
    var finalPassElement = document.getElementById("final-pass");
    if (finalPassElement) {
        finalPassElement.innerHTML = "\n        <p>Selected Vehicle: ".concat(vehicle, "</p>\n        <p>Plan Type: ").concat(plan, "</p>\n        <p>Price in ").concat(currency, ": ").concat(convertedPrice, "</p>\n        <p style=\"color: green;\">Stored Price (USD): $").concat(usdPrice, "</p>\n      ");
    }
}
