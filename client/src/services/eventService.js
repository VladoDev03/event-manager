const API_URL = "http://localhost:8080/api/events";

export const createEvent = async (eventData) => {
  try {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(eventData),
    });

    if (!response.ok) {
      throw new Error("Error creating event");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error creating event:", error);
    throw error;
  }
};

export const getEventById = async (id) => {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "GET",
    });

    if (!response.ok) {
      throw new Error("Error fetching event by ID");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching event by ID:", error);
    throw error;
  }
};

export const updateEvent = async (id, eventData) => {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(eventData),
    });

    if (!response.ok) {
      throw new Error("Error updating event");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error updating event:", error);
    throw error;
  }
};

export const deleteEvent = async (id) => {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "DELETE",
    });

    if (!response.ok) {
      throw new Error("Error deleting event");
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error deleting event:", error);
    throw error;
  }
};

export const fetchFilteredEvents = async (params) => {
  try {
    const response = await fetch(`${API_URL}/filterEvents?${params}`, {
      method: "GET",
    });
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    console.log(response);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching events:", error);
    throw error;
  }
};

export const fetchAllEvents = async () => {
  try {
    const response = await fetch(`${API_URL}/allEvents`, {
      method: "GET",
    });

    if (!response.ok) {
      throw new Error("Error fetching all events");
    }

    const data = await response.json();
    console.log(data);
    return data;
  } catch (error) {
    console.error("Error fetching all events: ", error);
    throw error;
  }
};

export const fetchSearchedEvents = async (queryParams) => {
  try {
    console.log(`${API_URL}/search?${queryParams}`);
    const response = await fetch(`${API_URL}/search?${queryParams}`);

    if (!response.ok) {
      throw new Error("Error fetching searched events");
    }

    console.log(response);
    const data = await response.json();
    console.log(data);
    return data;
  } catch (error) {
    console.error("Error fetching searched events: ", error);
    throw error;
  }
};

export const fetchFilteredSearchedEvents = async (queryParams) => {
  try {
    console.log(`${API_URL}/events?${queryParams}`);

    const response = await fetch(`${API_URL}/search/filter?${queryParams}`);

    if (!response.ok) {
      throw new Error("Error fetching events");
    }

    const data = await response.json();
    console.log(data);
    return data;
  } catch (error) {
    console.error("Error fetching events: ", error);
    throw error;
  }
};

export const getEventsByUser = async (userId) => {
  try {
    const response = await fetch(`${API_URL}/createdEvents/${userId}`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching reservations:", error);
  }
};
