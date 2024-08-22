// document.addEventListener("DOMContentLoaded", () => {
//     // Example: Add event listeners if needed in the future
//     const buttons = document.querySelectorAll('.btn');

//     buttons.forEach(button => {
//         button.addEventListener('click', () => {
//             alert(`Navigating to ${button.textContent.trim()}`);
//         });
//     });
// });

document.addEventListener("DOMContentLoaded", () => {
    const buttons = document.querySelectorAll('.btn');

    buttons.forEach(button => {
        button.addEventListener('click', () => {
            if (button.textContent.trim() === 'Go to Dashboard') {
                window.location.href = 'dashboard.html';
            } else if (button.textContent.trim() === 'View Bookings') {
                window.location.href = 'view-meetings.html';
            }
        });
    });
});

