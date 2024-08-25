let meetings = [];

async function fetchMeetings() {
    try {
        const response = await fetch('meetings.json'); // Fetch from the central data source
        meetings = await response.json();
        showWeek(currentWeekStart);
    } catch (error) {
        console.error('Error fetching meetings:', error);
    }
}

let currentWeekStart = new Date();
currentWeekStart.setDate(currentWeekStart.getDate() - (currentWeekStart.getDay() || 7) + 1);

function showWeek(weekStart) {
    const weekDays = document.getElementById('weekDays');
    weekDays.innerHTML = '';

    const weekEnd = new Date(weekStart);
    weekEnd.setDate(weekStart.getDate() + 6);

    document.getElementById('weekRange').textContent = `${weekStart.toLocaleDateString('en-US', { month: 'short', day: 'numeric' })} - ${weekEnd.toLocaleDateString('en-US', { month: 'short', day: 'numeric' })}`;

    const dayNames = document.querySelectorAll('.day-names div');
    for (let i = 0; i < 7; i++) {
        const currentDate = new Date(weekStart);
        currentDate.setDate(weekStart.getDate() + i);
        
        dayNames[i].textContent = currentDate.toLocaleDateString('en-US', { weekday: 'short' });
    }

    for (let i = 0; i < 7; i++) {
        const currentDate = new Date(weekStart);
        currentDate.setDate(weekStart.getDate() + i);

        const dayColumn = document.createElement('div');
        dayColumn.classList.add('day-column');

        if (i === 5) {
            dayColumn.classList.add('saturday');
        } else if (i === 6) {
            dayColumn.classList.add('sunday');
        }

        const dayHeading = document.createElement('h3');
        dayHeading.textContent = currentDate.toLocaleDateString('en-US', { month: 'short', day: 'numeric' });
        dayColumn.appendChild(dayHeading);

        const dayMeetings = meetings.filter(m => new Date(m.date).toDateString() === currentDate.toDateString());

        dayMeetings.forEach(meeting => {
            const meetingItem = document.createElement('div');
            meetingItem.classList.add('meeting-item');
            meetingItem.innerHTML = `
                <p><strong>${meeting.title}</strong></p>
                <p>${meeting.startTime} - ${meeting.endTime}</p>
            `;

            meetingItem.addEventListener('click', () => {
                openModal(meeting);
            });

            dayColumn.appendChild(meetingItem);
        });

        weekDays.appendChild(dayColumn);
    }
}

function openModal(meeting) {
    const modal = document.getElementById('meetingModal');
    document.getElementById('modalTitle').textContent = meeting.title;
    document.getElementById('modalDate').textContent = new Date(meeting.date).toLocaleDateString('en-GB');
    document.getElementById('modalRoomCode').textContent = meeting.roomCode;
    document.getElementById('modalStartTime').textContent = meeting.startTime;
    document.getElementById('modalEndTime').textContent = meeting.endTime;
    document.getElementById('modalDuration').textContent = meeting.duration;
    modal.style.display = 'block';
}

document.querySelector('.close').addEventListener('click', () => {
    document.getElementById('meetingModal').style.display = 'none';
});

window.addEventListener('click', (event) => {
    const modal = document.getElementById('meetingModal');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
});

document.getElementById('prevWeek').addEventListener('click', () => {
    currentWeekStart.setDate(currentWeekStart.getDate() - 7);
    showWeek(currentWeekStart);
});

document.getElementById('nextWeek').addEventListener('click', () => {
    currentWeekStart.setDate(currentWeekStart.getDate() + 7);
    showWeek(currentWeekStart);
});

document.getElementById('datePicker').addEventListener('change', (event) => {
    const selectedDate = new Date(event.target.value);
    currentWeekStart = new Date(selectedDate);
    currentWeekStart.setDate(currentWeekStart.getDate() - (currentWeekStart.getDay() || 7) + 1);
    showWeek(currentWeekStart);
});

fetchMeetings();
