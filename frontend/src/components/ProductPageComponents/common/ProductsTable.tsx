import useProducts from "../../../hooks/ProductsPageHooks/useProducts";
import Pagination from "./Pagination";
import ProductCount from "./ProductCount";
import CurrentBuildInfo from "./CurrentBuildInfo";
import { ReactComponent as AddItemIcon } from "../../../assets/svgs/ProductPages/common/add-item-icon.svg";
import searchIcon from "../../../assets/svgs/ProductPages/common/search-icon.svg";
import LoadingSpinner from "./LoadingSpinner";
import useProductsSearch from "../../../hooks/ProductsPageHooks/useProductsSearch";
import React, { useState, useEffect, useRef } from "react";
import Filters from "./Filters";

interface ProductsTableProps {
    category: string;
    data_columns: string[];
    header_columns: string[];
    pagePrefix: string;
    unitsMap: { [key: string]: string };
    filters: React.ReactNode[];
}

function ProductsTable({
                           category,
                           data_columns,
                           header_columns,
                           pagePrefix,
                           unitsMap,
                           filters
                       }: ProductsTableProps) {
    const {
        productsData,
        loading,
        entriesPerPage,
        totalEntries,
        setCurrentPage,
        currentPage
    } = useProducts(category);

    const [searchTerm, setSearchTerm] = useState("");

    const isSearching = searchTerm.trim().length > 0;


    const {
        productsResultSet,
        currentResultsPage,
        loadingSearchResults,
        productsPerPage,
        totalProducts,
        setCurrentResultsPage
    } = useProductsSearch(category, searchTerm);

    const productsList = productsData["products"];
    const queriedProductsList = productsResultSet["products"];

    const inputRef = useRef<HTMLInputElement>(null);
    const [wasFocused, setWasFocused] = useState(false);

    useEffect(() => {
        if (inputRef.current && wasFocused)
            inputRef.current.focus();

    }, [productsResultSet, productsData, wasFocused]);

    const handleFocus = () => {
        setWasFocused(true);
    };

    const handleBlur = () => {
        setWasFocused(false);
    };

    const handleSearch = (searchTerm: string) => {
        setSearchTerm(searchTerm);
    };

    function paginate(pageNumber: number) {
        if (isSearching)
            setCurrentResultsPage(pageNumber);
        else
            setCurrentPage(pageNumber);
    }

    const renderValue = (value: any) => {
        if (value === null)
            return "-";

        if (typeof value === 'boolean')
            return value ? 'Yes' : 'No';

        return value;
    };

    function TableHeader() {
        return (
            <tr>
                {header_columns.map(header => <th key={header}>{header}</th>)}
            </tr>
        );
    }

    function addProductToBuild(product: any) {
        console.log(JSON.stringify(product));
    }

    function renderColumnContent(product: any, dataAttribute: string) {
        const value = renderValue(product[dataAttribute]);
        const unit = value !== "-" && unitsMap[dataAttribute] ? unitsMap[dataAttribute] : "";

        switch (dataAttribute) {
            case "name":
                return (
                    <div className={pagePrefix + "product-img-container d-flex flex-row"}>
                        <img
                            src="https://m.media-amazon.com/images/I/61jRMCAX4CL._AC_UY327_FMwebp_QL65_.jpg"
                            width={"40rem"}
                            height={"40rem"}
                            className="me-4"
                        />
                        <div className="mt-2">
                            {renderValue(value)}
                        </div>
                    </div>
                );

            case "price":
                return (
                    <div className="mt-2">
                        {value !== "-" && value !== undefined ? "$" + value.toLocaleString("en-us", {
                            minimumFractionDigits: 2,
                            maximumFractionDigits: 2
                        }) : "-"}
                    </div>
                );

            case "add_item":
                return (
                    <div className={pagePrefix + "add-item-icon-container add-item-icon-container d-flex flex-row"}>
                        <AddItemIcon
                            className={pagePrefix + "add-item-icon add-item-icon me-3"}
                            width={"2rem"}
                            height={"2rem"}
                            onClick={() => addProductToBuild(product)}
                        />
                    </div>
                );

            default:
                return (
                    <div className="mt-2">
                        {value} {unit}
                    </div>
                );
        }
    }

    const productsToRender: any = isSearching ? queriedProductsList : productsList;
    const isLoading = loading || loadingSearchResults;

    return (
        isLoading
            ? <LoadingSpinner/>
            : <div className={pagePrefix + "--wrapper-- d-flex flex-row align-items-start"}>
                <div className={pagePrefix + "main-side-content w-auto d-flex flex-column mw-100 col-lg-2 align-items-center justify-content-center ms-3"}>
                    <CurrentBuildInfo/>
                    <Filters
                        pagePrefix={pagePrefix}
                        filters={filters}
                    />
                </div>

                <div className={pagePrefix + "table-container"}>
                    <div className={"table-container-top-wrapper container-fluid d-flex flex-row"}>
                        <ProductCount numberOfProducts={isSearching ? totalProducts : totalEntries} pagePrefix={pagePrefix}/>
                        <div className="searchbar-container d-flex flex-row h-25">
                            <img src={searchIcon} className="img-fluid me-2"/>
                            <input
                                placeholder={"Search..."}
                                onChange={(e) => handleSearch(e.target.value)}
                                value={searchTerm}
                                ref={inputRef}
                                onFocus={handleFocus}
                                onBlur={handleBlur}
                            />
                        </div>
                    </div>

                    <table className={pagePrefix + "products-table table mb-5"}>
                        <tbody>
                            <TableHeader/>
                            {productsToRender.map((product: any, index: number) =>
                                <tr key={index}>
                                    {data_columns.slice(0, -1).map((column, idx) => (
                                        <td key={idx} data-cell={header_columns[idx]}>
                                            {renderColumnContent(product, column)}
                                        </td>
                                    ))}
                                </tr>
                            )}
                        </tbody>
                    </table>
                    <div className="mt-3 mb-5">
                        <Pagination
                            entriesPerPage={isSearching ? productsPerPage : entriesPerPage}
                            totalEntries={isSearching ? totalProducts : totalEntries}
                            paginate={paginate}
                            currentPage={isSearching ? currentResultsPage : currentPage}
                        />
                    </div>
                </div>
            </div>
    );
}

export default ProductsTable;
