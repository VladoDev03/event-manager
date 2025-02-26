const baseUrl = 'http://localhost:8080/api/reservations'

export const createReservation = async (eventId, guestId, firstName, lastName, email) => {
    try {
        const response = await fetch(baseUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                eventId,
                guestId,
                firstName,
                lastName,
                email
            })
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        return data;
        
    } catch (error) {
        console.error('Error making reservation:', error);
        throw error;
    }
};

export const deleteReservation = async (reservationId) => { 
    console.log(`${baseUrl}/delete/${reservationId}`);
    try {
        const response = await fetch(`${baseUrl}/delete/${reservationId}`, {
            method: 'DELETE'
        });
    
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
    
        return true;
    } catch (error) { 
        console.error('Error deleting reservation:', error); 
        throw error; 
    } 
};

export const fetchFutureReservations = async (guestId) => {
    try {
        const response = await fetch(`${baseUrl}/future/${guestId}`);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching reservations:', error);
    }
};

export const fetchPreviousReservations = async (guestId) => {
    try {
        const response = await fetch(`${baseUrl}/previous/${guestId}`);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching reservations:', error);
    }
};