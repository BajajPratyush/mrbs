document.getElementById('add-user-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const userName = document.getElementById('user-name').value;
    const userEmail = document.getElementById('user-email').value;
    const userPhone = document.getElementById('user-phone').value;
    const userRole = document.getElementById('user-role').value;

    const user = {
        name: userName,
        email: userEmail,
        phone: userPhone,
        role: userRole
    };

    // Save the user details to an XML file (using a hypothetical API)
    saveUserToXML(user);

    alert('User added successfully!');
    this.reset();
});

function saveUserToXML(user) {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '../login/data.xml', true); // Replace with the actual path to your XML API
    xhr.setRequestHeader('Content-Type', 'application/xml');

    const userXML = `
        <user>
            <name>${user.name}</name>
            <email>${user.email}</email>
            <phone>${user.phone}</phone>
            <role>${user.role}</role>
        </user>
    `;

    xhr.send(userXML);
}


