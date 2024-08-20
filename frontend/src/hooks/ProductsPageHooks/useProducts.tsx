import { useState, useEffect } from "react";
import axios from "axios";

function useProducts(category: string) {
    const [productsData, setProductsData] = useState({"totalEntries": 0, "products": []});

    const [currentPage, setCurrentPage] = useState(() => {
        const savedPage = localStorage.getItem("currentPage_" + category);
        return savedPage ? parseInt(savedPage) : 0;
    });

    const [loading, setLoading] = useState(false);
    const [entriesPerPage] = useState(30);
    const [totalEntries, setTotalEntries] = useState(0);

    const BASE_API_ENDPOINT = "http://localhost:8081/api/v1/getAllParts/";

    async function fetchProducts() {
        try
        {
            setLoading(true);

            const endpoint = BASE_API_ENDPOINT +
                                   category +
                                   "?page=" +
                                   currentPage +
                                   "&size=" +
                                   entriesPerPage;

            const response = await axios.get(endpoint);

            setProductsData(response.data);
            setTotalEntries(response.data["totalEntries"]);
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

    useEffect(() => {
        localStorage.setItem("currentPage_" + category, currentPage.toString());
    }, [currentPage, category]);

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
