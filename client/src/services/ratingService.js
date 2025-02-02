const baseUrl = 'http://localhost:8080/api/ratings';

export const fetchRatings = async () => {
    try {
        const response = await fetch(baseUrl);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        data.forEach((element, index, array) => {
            array[index] = element.toLowerCase();
            array[index] = array[index].replaceAll('_',' ');
        });
        return data;
    } catch (error) {
        console.error('Error fetching ratings:', error);
        throw error;
    }
}