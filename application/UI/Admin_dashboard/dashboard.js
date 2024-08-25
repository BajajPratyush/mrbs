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
            calculateAndDisplayCost();
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
        calculateAndDisplayCost();
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

    function calculateAndDisplayCost() {
        let cost = 0;
        const seatingCapacity = parseInt(seatingCapacityInput.value);

        if (seatingCapacity <= 5) {
            cost += 0;
        } else if (seatingCapacity > 5 && seatingCapacity <= 10) {
            cost += 10;
        } else if (seatingCapacity > 10) {
            cost += 20;
        }

        checkboxes.forEach(checkbox => {
            if (checkbox.checked) {
                switch (checkbox.value) {
                    case 'Projector':
                        cost += 5;
                        break;
                    case 'WiFi':
                        cost += 10;
                        break;
                    case 'Conference Call':
                        cost += 15;
                        break;
                    case 'Whiteboard':
                        cost += 5;
                        break;
                    case 'Water Dispenser':
                        cost += 5;
                        break;
                    case 'TV':
                        cost += 10;
                        break;
                    case 'Coffee Machine':
                        cost += 10;
                        break;
                }
            }
        });

        perHourCostInput.value = cost;
    }

    bookingForm.addEventListener('submit', function (event) {
        event.preventDefault();
        
        const roomName = roomNameInput.value.trim();
        const seatingCapacity = seatingCapacityInput.value;
        const perHourCost = perHourCostInput.value;
        const meetingType = meetingTypeSelect.value;
        const amenities = Array.from(checkboxes)
            .filter(checkbox => checkbox.checked)
            .map(checkbox => checkbox.value);
    
        if (roomName && seatingCapacity && perHourCost && meetingType) {
            const roomData = {
                roomName,
                seatingCapacity,
                perHourCost,
                meetingType,
                amenities
            };
    
            let rooms = JSON.parse(localStorage.getItem('rooms')) || [];
            rooms.push(roomData);
            localStorage.setItem('rooms', JSON.stringify(rooms));
    
            window.location.href = '../rooms/rooms.html'; // Redirect to rooms.html
        }
    });

    document.addEventListener('click', function (event) {
        if (!selectedOptions.contains(event.target) && !optionsContainer.contains(event.target)) {
            selectedOptions.classList.remove('active');
            optionsContainer.classList.remove('active');
        }
    });

    seatingCapacityInput.addEventListener('input', function () {
        calculateAndDisplayCost();
    });
});
