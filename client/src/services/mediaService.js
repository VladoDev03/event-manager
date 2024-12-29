const baseUrl = 'http://localhost:8080/api/media'

export async function getImages() {
    const response = await fetch(baseUrl)
    const result = await response.json();

    return result;
}

export async function getImageById(imageUrl) {
    const response = await fetch(`${baseUrl}/${imageUrl}`)
    const result = await response.json();

    return result;
}

export async function uploadImages(formData) {
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

export async function editImage({imageId, description}) {
    const response = await fetch(`${baseUrl}/${imageId}`, {
        method: 'PUT',
        body: JSON.stringify({description}),
        headers: {
            'Content-Type': 'application/json'
        }
    })

    const result = await response.json();

    return result
}