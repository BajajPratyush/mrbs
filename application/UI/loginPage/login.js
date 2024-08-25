document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent form submission

    const email = document.getElementById('email').value;
    const name = document.getElementById('name').value;

    // Simple validation
    if (email === "" || name === "") {
        alert("Both email and name are required!");
        return;
    }

    // Load XML and validate user
    fetch('data.xml') // Relative path to the XML file
        .then(response => response.text())
        .then(data => {
            const parser = new DOMParser();
            const xml = parser.parseFromString(data, "application/xml");
            const users = xml.getElementsByTagName('user');

            let validUser = false;
            let userRole = '';

            for (let i = 0; i < users.length; i++) {
                const xmlName = users[i].getElementsByTagName('name')[0].textContent;
                const xmlEmail = users[i].getElementsByTagName('email')[0].textContent;
                const xmlRole = users[i].getElementsByTagName('role')[0].textContent;

                if (email === xmlEmail && name === xmlName) {
                    validUser = true;
                    userRole = xmlRole;
                    break;
                }
            }

            if (validUser) {
                if (userRole === 'Admin') {
                    window.location.href = "../admin/admin.html";
                } else if (userRole === 'Manager') {
                    window.location.href = "../manager/manager.html";
                } else if (userRole === 'Member') {
                    window.location.href = "../member/member.html";
                }
            } else {
                alert("Invalid name or email!");
            }
        })
        .catch(error => {
            console.error("Error loading XML file:", error);
            alert("Error loading user data.");
        });
});
