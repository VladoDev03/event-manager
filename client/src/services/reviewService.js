const baseUrl = 'http://localhost:8080/api/review'

export async function uploadReview(review) {
    const response = await fetch(baseUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(review)
    });

    if (!response.ok) {
        throw new Error(`Failed to upload review: ${response.statusText}`);
    }

    return await response.text();
}

export async function deleteReview(reviewId) {
    const response = await fetch(`${baseUrl}/${reviewId}`, {
        method: 'DELETE'
    })

    const result = await response.json();

    return result
}