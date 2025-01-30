const baseUrl = 'http://localhost:8080/api/auth';

export async function login(userData) {
    const response = await fetch(`${baseUrl}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    });

    const result = response.json();

    return result;
}

export async function register(userData) {
    try {
        const response = await fetch(`${baseUrl}/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });

        if (!response.ok) {
            const errorMessage = await response.text();
            throw new Error(errorMessage || "Registration failed");
        }

        return await response.json();
    } catch (error) {
        console.error("Registration error:", error.message);
        return { error: error.message };
    }
}
