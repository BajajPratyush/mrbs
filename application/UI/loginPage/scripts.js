document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent form submission

    const email = document.getElementById('email').value;

    if (email === "") {
        alert("Email is required!");
        return;
    }

    // Fetch user data from the XML file
    fetch('/path/to/data.xml') // Update with the actual path to your XML file
        .then(response => response.text())
        .then(data => {
            const parser = new DOMParser();
            const xmlDoc = parser.parseFromString(data, 'text/xml');
            const users = xmlDoc.getElementsByTagName('user');
            
            let userFound = false;

            Array.from(users).forEach(user => {
                const userEmail = user.getElementsByTagName('email')[0].textContent;
                const userRole = user.getElementsByTagName('role')[0].textContent;

                if (email === userEmail) {
                    userFound = true;
                    // Redirect based on user role
                    if (userRole === 'Manager') {
                        window.location.href = 'manager.html';
                    } else if (userRole === 'Admin') {
                        window.location.href = 'admin.html'; // Ensure you have this page
                    } else if (userRole === 'Member') {
                        window.location.href = 'member.html'; // Ensure you have this page
                    }
                }
            });

            if (!userFound) {
                alert("Invalid login credentials!");
            }
        })
        .catch(error => {
            console.error('Error fetching XML file:', error);
        });
});
