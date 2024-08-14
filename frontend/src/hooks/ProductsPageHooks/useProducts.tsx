import { useState, useEffect } from "react";
import axios from "axios";

function useProducts(category: string) {

    const [productsData, setProductsData] = useState({"totalEntries": 0, "products": []});
    const [currentPage, setCurrentPage] = useState(0);
    const [loading, setLoading] = useState(false);
    const [entriesPerPage] = useState(30);
    const [totalEntries, setTotalEntries] = useState(0);

    const BASE_API_ENDPOINT = "http://localhost:8081/api/v1/getAllParts/";

    async function fetchProducts() {
        try
        {
            setLoading(true);

            const response = await axios.get(
                BASE_API_ENDPOINT +
                category +
                "?page=" +
                currentPage +
                "&size=" +
                entriesPerPage
            );

            setProductsData(response.data);
            setTotalEntries(response.data["totalEntries"])
            setLoading(false);
        }
        catch (error)
        {
            console.error("Error fetching products:", error);
        }
    }

    useEffect(() => {
        fetchProducts();
    }, [currentPage]);

    return {
        productsData,
        currentPage,
        loading,
        entriesPerPage,
        totalEntries,
        setCurrentPage
    };
}

export default useProducts;
