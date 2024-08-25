document.addEventListener('DOMContentLoaded', function () {
    const createRoomForm = document.getElementById('create-room-form');
    const roomNameInput = document.getElementById('room-name');
    const capacityInput = document.getElementById('capacity');
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');

    createRoomForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const roomName = roomNameInput.value;
        const capacity = capacityInput.value;
        const amenities = Array.from(checkboxes)
            .filter(checkbox => checkbox.checked)
            .map(checkbox => ({
                name: checkbox.nextElementSibling.textContent,
                cost: parseInt(checkbox.value, 10)
            }));

        const roomData = {
            name: roomName,
            capacity: capacity,
            amenities: amenities
        };

        // Save room data to localStorage
        let rooms = JSON.parse(localStorage.getItem('rooms')) || [];
        rooms.push(roomData);
        localStorage.setItem('rooms', JSON.stringify(rooms));

        // Redirect to rooms page
        window.location.href = '../rooms/rooms.html';
    });
});
