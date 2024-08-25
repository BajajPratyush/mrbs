document.addEventListener('DOMContentLoaded', function () {
    
    const roomsList = document.getElementById('rooms-list');

    function displayRooms() {
        const rooms = JSON.parse(localStorage.getItem('rooms')) || [];

        if (rooms.length === 0) {
            roomsList.innerHTML = '<p>No rooms available.</p>';
            return;
        }

        rooms.forEach(room => {
            const roomDiv = document.createElement('div');
            roomDiv.classList.add('room-card');
            roomDiv.innerHTML = `
                <h3>${room.roomName}</h3>
                <p><strong>Seating Capacity:</strong> ${room.seatingCapacity}</p>
                <p><strong>Per Hour Cost:</strong> ${room.perHourCost} credits</p>
                <p><strong>Meeting Type:</strong> ${room.meetingType}</p>
                <p><strong>Amenities:</strong> ${room.amenities.join(', ')}</p>
            `;
            roomsList.appendChild(roomDiv);
        });
    }

    displayRooms();
});
