import * as yup from "yup";

const schema = yup.object().shape({
  name: yup.string().min(2, "Name must be at least 2 characters").matches(/^([a-zA-Z]+[\s-']?){1,3}$/, "Invalid name format").required("Name is required"),
  email: yup.string().email("Invalid email format").required("Email is required"),
  password: yup.string().min(6, "Password must be at least 6 characters").required("Password is required"),
  confirmPassword: yup.string().oneOf([yup.ref("password")], "Passwords must match").required("Confirm Password is required"),
});

async function validateField(fieldName: string, value: string): Promise<boolean> {
  try {
    await schema.validateAt(fieldName, { [fieldName]: value });
    return true; // Validation passed
  } catch (error) {
    if (error instanceof yup.ValidationError) {
      console.error(`Validation Error in ${fieldName}:`, error.message);
      document.getElementById(`${fieldName}-error`)!.textContent = error.message; // Display error in UI
    }
    return false; // Validation failed
  }
}