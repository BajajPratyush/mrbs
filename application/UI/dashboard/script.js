document.addEventListener('DOMContentLoaded', function () {
    const selectedOptions = document.querySelector('.selected-options');
    const optionsContainer = document.querySelector('.options-container');
    const checkboxes = document.querySelectorAll('.options-container input[type="checkbox"]');
    const meetingTypeSelect = document.getElementById('meeting-type');
    const amenitiesOptions = document.querySelectorAll('.options-container input[type="checkbox"]');
    const roomNameInput = document.getElementById('room-name');
    const seatingCapacityInput = document.getElementById('seating-capacity');
    const perHourCostInput = document.getElementById('per-hour-cost');
    const bookingForm = document.getElementById('booking-form');
    const bookingSummary = document.getElementById('booking-summary');
    const summaryDetails = document.getElementById('summary-details');

    selectedOptions.addEventListener('click', function () {
        this.classList.toggle('active');
        optionsContainer.classList.toggle('active');
    });

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function () {
            const selectedAmenities = [];
            checkboxes.forEach(checkbox => {
                if (checkbox.checked) {
                    selectedAmenities.push(checkbox.value);
                }
            });
            selectedOptions.innerText = selectedAmenities.length ? selectedAmenities.join(', ') : 'Select Amenities';
        });
    });

    function resetAmenities() {
        amenitiesOptions.forEach(option => option.checked = false);
        selectedOptions.innerText = 'Select Amenities';
    }

    function selectMandatoryAmenities(amenities) {
        const selectedAmenities = [];
        amenitiesOptions.forEach(option => {
            if (amenities.includes(option.value)) {
                option.checked = true;
                selectedAmenities.push(option.value);
            }
        });
        selectedOptions.innerText = selectedAmenities.length ? selectedAmenities.join(', ') : 'Select Amenities';
    }

    meetingTypeSelect.addEventListener('change', function () {
        resetAmenities();
        
        const selectedMeetingType = this.value;
        let mandatoryAmenities = [];
        
        if (selectedMeetingType === 'classroom') {
            mandatoryAmenities = ['Projector', 'Whiteboard'];
        } else if (selectedMeetingType === 'online') {
            mandatoryAmenities = ['Projector', 'WiFi'];
        } else if (selectedMeetingType === 'conference') {
            mandatoryAmenities = ['Conference Call'];
        } else if (selectedMeetingType === 'business') {
            mandatoryAmenities = ['Projector'];
        }
        
        selectMandatoryAmenities(mandatoryAmenities);
    });

    bookingForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const roomName = roomNameInput.value;
        const seatingCapacity = seatingCapacityInput.value;
        const perHourCost = perHourCostInput.value;
        const meetingType = meetingTypeSelect.value;
        const selectedAmenities = Array.from(checkboxes)
            .filter(checkbox => checkbox.checked)
            .map(checkbox => checkbox.value);

        summaryDetails.innerHTML = `
            <p><strong>Room Name:</strong> ${roomName}</p>
            <p><strong>Seating Capacity:</strong> ${seatingCapacity}</p>
            <p><strong>Per Hour Cost (in credits):</strong> ${perHourCost}</p>
            <p><strong>Meeting Type:</strong> ${meetingType.charAt(0).toUpperCase() + meetingType.slice(1).replace('-', ' ')}</p>
            <p><strong>Amenities:</strong> ${selectedAmenities.length ? selectedAmenities.join(', ') : 'None selected'}</p>
        `;

        bookingSummary.style.display = 'block';
    });

    document.addEventListener('click', function (event) {
        if (!selectedOptions.contains(event.target) && !optionsContainer.contains(event.target)) {
            selectedOptions.classList.remove('active');
            optionsContainer.classList.remove('active');
        }
    });
});

