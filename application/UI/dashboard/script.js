document.addEventListener('DOMContentLoaded', function() {
    const roomForm = document.getElementById('roomForm');
    const roomList = document.getElementById('roomList');
    const amenitySelect = document.getElementById('amenitySelect');
    const selectedOptions = amenitySelect.querySelector('.selected-options');
    const optionsContainer = amenitySelect.querySelector('.options-container');
    const checkboxes = optionsContainer.querySelectorAll('input[type="checkbox"]');

    // Toggle options container
    selectedOptions.addEventListener('click', function(e) {
        e.stopPropagation();
        optionsContainer.classList.toggle('show');
        selectedOptions.classList.toggle('active');
    });

    // Close options when clicking outside
    document.addEventListener('click', function() {
        optionsContainer.classList.remove('show');
        selectedOptions.classList.remove('active');
    });

    // Update selected options display
    checkboxes.forEach(function(checkbox) {
        checkbox.addEventListener('change', updateSelectedOptions);
    });

    function updateSelectedOptions() {
        const selected = [];
        checkboxes.forEach(function(checkbox) {
            if (checkbox.checked) {
                selected.push(checkbox.value);
            }
        });
        if (selected.length > 0) {
            selectedOptions.textContent = selected.join(', ');
        } else {
            selectedOptions.textContent = 'Select Amenities';
        }
    }

    roomForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const roomName = document.getElementById('roomName').value;
        const capacity = document.getElementById('capacity').value;
        const amenities = [];
        checkboxes.forEach(function(checkbox) {
            if (checkbox.checked) {
                amenities.push(checkbox.value);
            }
        });
        const credits = document.getElementById('credits').value;

        const roomDiv = document.createElement('div');
        roomDiv.className = 'room';
        roomDiv.innerHTML = `
            <h3>${roomName}</h3>
            <p><strong>Capacity:</strong> ${capacity}</p>
            <p><strong>Amenities:</strong> ${amenities.join(', ')}</p>
            <p><strong>Cost:</strong> ${credits} Credits/Hour</p>
        `;

        roomList.appendChild(roomDiv);

        // Reset the form after submission
        roomForm.reset();
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = false;
        });
        selectedOptions.textContent = 'Select Amenities';
    });
});
