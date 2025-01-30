const baseUrl = 'http://localhost:8080/api/auth';

export async function login(email, password) {
    const response = await fetch(`${baseUrl}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({email, password})
    });

    const result = response.json();

    return result;
}

export async function register(userData) {
    const response = await fetch(`${baseUrl}/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    });

    const result = response.json();
    console.log(result);

    return result;
}