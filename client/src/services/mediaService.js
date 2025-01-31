const baseUrl = 'http://localhost:8080/api/media'

export async function uploadMedia(formData) {
    const response = await fetch(baseUrl, {
        method: 'POST',
        body: formData,
    });

    if (!response.ok) {
        throw new Error(`Failed to upload images: ${response.statusText}`);
    }

    return await response.text();
}

export async function deleteMedia(mediaId) {
    const response = await fetch(`${baseUrl}/${mediaId}`, {
        method: 'DELETE'
    })

    const result = await response.json();

    return result
}

export const getMediaByEventId = async (id) => {
    try {
      const response = await fetch(`${baseUrl}/${id}`, {
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