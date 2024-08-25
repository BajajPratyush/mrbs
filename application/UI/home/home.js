document.addEventListener("DOMContentLoaded", () => {
    // Example: Add event listeners if needed in the future
    const buttons = document.querySelectorAll('.btn');

    buttons.forEach(button => {
        button.addEventListener('click', () => {
            alert(`Navigating to ${button.textContent.trim()}`);
        });
    });
});
