import useProducts from "../../../hooks/ProductsPageHooks/useProducts";
import Pagination from "./Pagination";
import ProductCount from "./ProductCount";
import CurrentBuildInfo from "./CurrentBuildInfo";
import PriceFilter from "./PriceFilter";
import {ReactComponent as AddItemIcon} from "../../../assets/svgs/ProductPages/common/add-item-icon.svg"
import searchIcon from "../../../assets/svgs/ProductPages/common/search-icon.svg";

interface ProductsTableProps {
    category: string
    data_columns: string[]
    header_columns: string[]
    pagePrefix: string
    unitsMap: { [key: string]: string }
}

function ProductsTable({ category, data_columns, header_columns, pagePrefix, unitsMap }: ProductsTableProps) {
    const {
        productsData,
        loading,
        entriesPerPage,
        totalEntries,
        setCurrentPage,
        currentPage
    } = useProducts(category);

    const productsList = productsData["products"];

    function paginate(pageNumber: number) {
        setCurrentPage(pageNumber)
    }

    const spinnerStyle = {
        width: "3rem",
        height: "3rem"
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
                {
                    header_columns.map(
                        header => <th>{header}</th>
                    )
                }
            </tr>
        );
    }

    const style = {
        color: "white"
    }

    function addProductToBuild(product: any) {
        console.log(JSON.stringify(product));
    }

    function renderColumnContent(product: any, dataAttribute: string) {
        const value = renderValue(product[dataAttribute]);
        const unit = value !== "-" && unitsMap[dataAttribute] ? unitsMap[dataAttribute] : "";

        switch (dataAttribute)
        {
            case "name":
                return (
                    <div className={pagePrefix + "product-img-container d-flex flex-row"}>
                        {/*<img src={product[data_columns[data_columns.length - 1]]}/>*/}
                        <img src="https://m.media-amazon.com/images/I/61jRMCAX4CL._AC_UY327_FMwebp_QL65_.jpg" width={"40rem"} height={"40rem"} className="me-4"/>
                        <div className="mt-2">
                            {renderValue(value)}
                        </div>
                    </div>
                );

            case "price":
                return (
                    <div className="mt-2">
                        {value !== "-" ? "$" + value.toLocaleString("en-us", {minimumFractionDigits: 2, maximumFractionDigits: 2}) : value}
                    </div>
                );

            case "add_item":
                return (
                    <div className={pagePrefix + "add-item-icon-container d-flex flex-row"}>
                        <AddItemIcon className={pagePrefix + "add-item-icon me-3"} width={"2rem"} height={"2rem"} onClick={() => addProductToBuild(product)}/>
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

    return (
        loading
            ?
            <div className="d-flex flex-row justify-content-center mt-5">
                <div style={spinnerStyle} className="spinner-border text-primary" role="status"></div>
            </div>
            :
            <div className={pagePrefix + "--wrapper-- d-flex flex-row align-items-start"}>

                <div className={pagePrefix + "main-side-content w-auto d-flex flex-column mw-100 col-lg-2 align-items-center justify-content-center ms-3"}>
                    <CurrentBuildInfo/>

                    <div className={pagePrefix + "filter-container d-flex flex-column align-items-center justify-content-center mt-4"}>
                        <div className={pagePrefix + "filter-header-container text-align-center p-2 container-fluid"}>
                            <h3 className="text-center">Filters</h3>
                        </div>

                        {/*this is hardcoded. make sure to change min and max price*/}
                        <PriceFilter pagePrefix={pagePrefix} minPrice={0} maxPrice={4200}/>
                        {/* Next common filters go after this... */}
                    </div>

                </div>

                <div className={pagePrefix + "table-container"}>

                    <div className={"table-container-top-wrapper container-fluid d-flex flex-row"}>
                        <ProductCount numberOfProducts={totalEntries} pagePrefix={pagePrefix}/>
                        <div className="searchbar-container d-flex flex-row justify-content-start h-25">
                            <img src={searchIcon} className="img-fluid me-2"/>
                            <input className="input-group" placeholder={" Search..."}/>
                        </div>
                    </div>

                    <table className={pagePrefix + "products-table table mb-5"} style={style}>
                        <tbody>
                        <TableHeader/>
                        {
                            productsList.map((product: any, index: number) =>
                                <tr key={index}>
                                    {
                                        data_columns.slice(0, -1).map((column, idx) => (
                                            <td key={idx} data-cell={header_columns[idx]}>
                                                {renderColumnContent(product, column)}
                                            </td>
                                        ))
                                    }
                                </tr>
                            )}
                        </tbody>
                    </table>
                    <div className="mt-3 mb-5">
                        <Pagination entriesPerPage={entriesPerPage} totalEntries={totalEntries} paginate={paginate}
                                    currentPage={currentPage}/>
                    </div>
                </div>
            </div>
    );

}

export default ProductsTable;

