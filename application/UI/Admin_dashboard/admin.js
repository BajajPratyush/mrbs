// document.addEventListener('DOMContentLoaded', function() {
//     const createRoomForm = document.getElementById('create-room-form');
    
//     if (createRoomForm) {
//         createRoomForm.addEventListener('submit', function(event) {
//             event.preventDefault();
            
//             const roomName = document.getElementById('room-name').value;
//             const capacity = document.getElementById('capacity').value;
            
//             // Get selected amenities
//             const amenities = Array.from(document.querySelectorAll('input[name="amenities"]:checked'))
//                 .map(checkbox => checkbox.value);
            
//             const roomData = {
//                 name: roomName,
//                 capacity: capacity,
//                 amenities: amenities
//             };
            
//             // Get existing rooms from local storage
//             let rooms = JSON.parse(localStorage.getItem('rooms')) || [];
//             rooms.push(roomData);
            
//             // Save updated rooms to local storage
//             localStorage.setItem('rooms', JSON.stringify(rooms));
            
//             // Clear form fields
//             createRoomForm.reset();
//             alert('Room added successfully!');
            
//             // Redirect to view rooms page
//             window.location.href = 'rooms.html';
//         });
//     }
// });



document.addEventListener('DOMContentLoaded', function() {
    const createRoomForm = document.getElementById('create-room-form');
    
    if (createRoomForm) {
        createRoomForm.addEventListener('submit', function(event) {
            event.preventDefault();
            
            const roomName = document.getElementById('room-name').value;
            const capacity = document.getElementById('capacity').value;
            
            // Get selected amenities
            const amenities = Array.from(document.querySelectorAll('input[name="amenities"]:checked'))
                .map(checkbox => checkbox.value);
            
            const roomData = {
                name: roomName,
                capacity: capacity,
                amenities: amenities
            };
            
            // Get existing rooms from local storage
            let rooms = JSON.parse(localStorage.getItem('rooms')) || [];
            rooms.push(roomData);
            
            // Save updated rooms to local storage
            localStorage.setItem('rooms', JSON.stringify(rooms));
            
            // Clear form fields
            createRoomForm.reset();
            alert('Room added successfully!');
            
            // Redirect to view rooms page
            window.location.href = 'rooms.html';
        });
    }
});
