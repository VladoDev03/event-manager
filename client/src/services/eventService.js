const API_URL = 'http://localhost:8080/api/event';

export const createEvent = async (eventData) => {
  try {
    const response = await fetch(API_URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(eventData),
    });

    if (!response.ok) {
      throw new Error('Error creating event');
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error creating event:', error);
    throw error;
  }
};

export const getEventById = async (id) => {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: 'GET',
    });

    if (!response.ok) {
      throw new Error('Error fetching event by ID');
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error fetching event by ID:', error);
    throw error;
  }
};

export const updateEvent = async (id, eventData) => {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(eventData),
    });

    if (!response.ok) {
      throw new Error('Error updating event');
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error updating event:', error);
    throw error;
  }
};

export const deleteEvent = async (id) => {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: 'DELETE',
    });

    if (!response.ok) {
      throw new Error('Error deleting event');
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error deleting event:', error);
    throw error;
  }
};
