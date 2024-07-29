import { useState, useEffect } from "react";
import axios from "axios";

function useProducts(category: string) {
    const [products, setProducts] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [loading, setLoading] = useState(false);
    const [entriesPerPage, setEntriesPerPage] = useState(10);

    async function fetchProducts() {
        try
        {
            setLoading(true);
            const response = await axios.get("http://localhost:8081/api/v1/getAllParts/" + category + "?page=1&size=10");
            setProducts(response.data);
            setLoading(false);
        }
        catch (error)
        {
            console.error("Error fetching products:", error);
        }
    }

    useEffect(() => {
        fetchProducts();
    }, [category]);

    return { products, currentPage, loading, entriesPerPage };
}

export default useProducts;
