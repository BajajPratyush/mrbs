document.addEventListener('DOMContentLoaded', () => {
    const roomSelect = document.getElementById('room-selection');
    const amenitiesSection = document.getElementById('amenities-section');
    const meetingForm = document.getElementById('organize-meeting-form');

    function populateRoomSelection() {
        const rooms = JSON.parse(localStorage.getItem('rooms')) || [];
        if (rooms.length === 0) {
            const option = document.createElement('option');
            option.textContent = 'No rooms available';
            option.disabled = true;
            roomSelect.appendChild(option);
            return;
        }

        rooms.forEach(room => {
            const option = document.createElement('option');
            option.value = room.roomName;
            option.textContent = room.roomName;
            roomSelect.appendChild(option);
        });
    }

    function populateAmenitiesChecklist() {
        const amenities = JSON.parse(localStorage.getItem('amenities')) || [];
        amenitiesSection.innerHTML = '<legend>Select Amenities:</legend>';

        if (amenities.length === 0) {
            const p = document.createElement('p');
            p.textContent = 'Amenities Not Available';
            amenitiesSection.appendChild(p);
            return;
        }

        amenities.forEach(amenity => {
            const label = document.createElement('label');
            label.textContent = `${amenity.name} (${amenity.creditValue} credits)`;

            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.value = amenity.name;

            label.insertBefore(checkbox, label.firstChild);
            amenitiesSection.appendChild(label);
            amenitiesSection.appendChild(document.createElement('br'));
        });
    }

    populateRoomSelection();
    populateAmenitiesChecklist();

    meetingForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const meetingName = document.getElementById('meeting-name').value;
        const roomName = roomSelect.value;
        const meetingStart = new Date(document.getElementById('meeting-start').value);
        const meetingEnd = new Date(document.getElementById('meeting-end').value);

        if (meetingStart >= meetingEnd) {
            alert('End time must be after start time.');
            return;
        }

        const selectedAmenities = Array.from(document.querySelectorAll('#amenities-section input:checked'))
            .map(checkbox => checkbox.value);

        const startTime = meetingStart.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
        const endTime = meetingEnd.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
        const duration = calculateDuration(meetingStart, meetingEnd);

        const meeting = {
            title: meetingName,
            roomCode: roomName,
            startTime: startTime,
            endTime: endTime,
            date: meetingStart.toISOString().split('T')[0],
            duration: duration,
            amenities: selectedAmenities
        };

        let meetings = JSON.parse(localStorage.getItem('meetings')) || [];
        meetings.push(meeting);
        localStorage.setItem('meetings', JSON.stringify(meetings));

        alert('Meeting organized successfully!');
        meetingForm.reset();

        window.location.href = '../view-meetings/view-meetings.html';
    });

    function calculateDuration(start, end) {
        const durationMs = end - start;
        const hours = Math.floor(durationMs / (1000 * 60 * 60));
        const minutes = Math.floor((durationMs % (1000 * 60 * 60)) / (1000 * 60));
        return `${hours}h ${minutes}m`;
    }
});
