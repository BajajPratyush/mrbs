document.addEventListener('DOMContentLoaded', function() {
    // Load room details on the rooms page
    const roomDetailsContainer = document.getElementById('room-details');
    if (roomDetailsContainer) {
        const rooms = JSON.parse(localStorage.getItem('rooms')) || [];
        rooms.forEach(room => {
            const roomDiv = document.createElement('div');
            roomDiv.innerHTML = `<h3>${room.name}</h3><p>Capacity: ${room.capacity}</p><p>Amenities: ${room.amenities.join(', ')}</p>`;
            roomDetailsContainer.appendChild(roomDiv);
        });
    }
});
