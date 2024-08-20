import { useState, useEffect } from "react";
import axios from "axios";

function useProductsSearch(category: string, searchTerm: string) {

    const [productsResultSet, setProductsResultSet] = useState({"totalEntries": 0, "products": []});
    const [currentResultsPage, setCurrentResultsPage] = useState(1);
    const [loadingSearchResults, setLoading] = useState(false);
    const [productsPerPage] = useState(30);
    const [totalProducts, setTotalEntries] = useState(0);

    const BASE_API_ENDPOINT = "http://localhost:8081/api/v1/getAllPartsBySearchTerm/";

    async function search() {

        if (searchTerm.trim() === "")
            return;

        try
        {
            setLoading(true);

            const endpoint = BASE_API_ENDPOINT +
                                   category +
                                   "?page=" +
                                   currentResultsPage +
                                   "&size=" +
                                   productsPerPage +
                                   "&searchTerm=" + searchTerm;

            const response = await axios.get(endpoint);

            setProductsResultSet(response.data);
            setTotalEntries(response.data["totalEntries"])
            setLoading(false);
        }
        catch (error)
        {
            console.error("Error fetching products for specified search term:", error);
        }
    }

    useEffect(() => {
        search();
    }, [searchTerm, currentResultsPage]);

    return {
        productsResultSet,
        currentResultsPage,
        loadingSearchResults,
        productsPerPage,
        totalProducts,
        setCurrentResultsPage
    };
}

export default useProductsSearch;
