document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent form submission

    // Get input values
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    // Error message element
    const errorMessage = document.getElementById("error-message");

    // Check if fields are empty
    if (username === "" || password === "") {
        errorMessage.textContent = "Both fields are required!";
        errorMessage.style.display = "block";
    } else {
        // If both fields are filled, submit the form or perform your desired action
        errorMessage.style.display = "none";
        alert("Form submitted successfully!");
        // You can replace the alert with an actual form submission if needed
        // For example: this.submit();
    }
});
