import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { fetchCategories } from "../services/categoriesService";
import { fetchFilteredEvents } from "../services/eventService";
import "../style/FilteredSearch.css";

const FilterComponent = ({ initialEvents, onFilter }) => {
    /* const location = useLocation();
    const initialEvents = location.state?.initialEvents || [];

    const [events, setEvents] = useState(initialEvents); */
    const [categories, setCategories] = useState([]);
    const [category, setCategory] = useState('');
    const [minPrice, setMinPrice] = useState('');
    const [maxPrice, setMaxPrice] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            try {
                const categoriesData = await fetchCategories();
                setCategories(categoriesData);
            } catch (error) {
                console.error('Error fetching categories', error);
            }
        };

        fetchData();
    }, []);

    const handleFilter = () => { 
        const params = { 
            initialEvents,
            category: category.toUpperCase().replaceAll(' ', '_') || null, 
            minPrice, 
            maxPrice, 
            startDate: startDate || null, 
            endDate: endDate || null
        }; 
        onFilter(params); 
    };

    const enforceMinMax = (el) => {
        if (el.value != "") {
          if (parseInt(el.value) < parseInt(el.min)) {
            el.value = el.min;
          }
          if (parseInt(el.value) > parseInt(el.max)) {
            el.value = el.max;
          }
        }
      }

    return (
        <div className="filter-container">
            <form id="filter-form">
                <div className="filter">
                    <label htmlFor="category">Category</label>
                    <select
                        id="category"
                        value={category}
                        onChange={(e) => setCategory(e.target.value)}
                        >
                            <option value="">Select Category</option>
                            {categories.map(cat => (
                                <option key={cat} value={cat}>{cat}</option>
                            ))}
                    </select>
                </div>
                <div className="filter">
                    <label htmlFor="startDate">Start Date</label>
                    <input
                        type="date"
                        id="startDate"
                        value={startDate}
                        max={endDate}
                        onChange={(e) => setStartDate(e.target.value)}
                    />
                </div>
                <div className="filter">
                    <label htmlFor="endDate">End Date</label>
                    <input
                        type="date"
                        id="endDate"
                        value={endDate}
                        min={startDate}
                        onChange={(e) => setEndDate(e.target.value)}
                    />
                </div>
                <div className="filter">
                    <label htmlFor="minPrice">Min Price</label>
                    <input
                        type="number"
                        id="minPrice"
                        value={minPrice}
                        min={0}
                        max={maxPrice}
                        onBlur={(e) => {
                            enforceMinMax(e.target);
                            setMinPrice(e.target.value)
                        }}
                        onChange={(e) => setMinPrice(e.target.value)}
                        placeholder="Min Price"
                    />
                </div>
                <div className="filter">
                    <label htmlFor="maxPrice">Max Price</label>
                    <input
                        type="number"
                        id="maxPrice"
                        value={maxPrice}
                        min={minPrice}
                        onBlur={(e) => 
                            {
                                enforceMinMax(e.target);
                                setMaxPrice(e.target.value)
                            }
                        }
                        onChange={(e) => setMaxPrice(e.target.value)}
                        placeholder="Max Price"
                    />
                </div>
                <button id="apply-filters" type="button" onClick={handleFilter}>Apply Filters</button>
            </form>
        </div>
    );
};

export default FilterComponent;
