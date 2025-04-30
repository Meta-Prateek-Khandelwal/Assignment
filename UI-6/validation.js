const form = document.getElementById('emp');
const nameInput = document.getElementById('Name');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const contactInput = document.getElementById('contact');

function validateName(name) {
  const isValid = name.length >= 2 && /^[a-zA-Z\s]+$/.test(name); // Min length 2, no numbers
  return isValid ? '' : 'Full Name must have at least 2 letters and contain no numbers.';
}

function validateEmail(email) {
  const isValid = email.includes('@') && email.includes('.') && email.length > 5;
  return isValid ? '' : 'Email must contain "@" and ".", and have sufficient length.';
}

function validatePassword(password) {
  const isValid =
    password.length >= 8 &&
    /[A-Z]/.test(password) &&
    /[a-z]/.test(password) &&
    /\d/.test(password) &&
    /[^a-zA-Z\d]/.test(password);
  return isValid
    ? ''
    : 'Password must have at least 8 characters, including uppercase, lowercase, numeric, and special characters.';
}

function validateContact(contact) {
  const isValid = /^\d{9,}$/.test(contact);
  return isValid ? '' : 'Contact Number must contain only numbers and have a length greater than 8.';
}

form.addEventListener('submit', (event) => {
  event.preventDefault(); 

  let errors = [];

  const nameError = validateName(nameInput.value);
  if (nameError) errors.push(nameError);

  const emailError = validateEmail(emailInput.value);
  if (emailError) errors.push(emailError);

  const passwordError = validatePassword(passwordInput.value);
  if (passwordError) errors.push(passwordError);

  const contactError = validateContact(contactInput.value);
  if (contactError) errors.push(contactError);

  if (errors.length > 0) {
    alert('Validation Errors:\n' + errors.join('\n'));
  } else {
    alert('Form submitted successfully!');
  }
});