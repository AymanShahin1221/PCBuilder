import useProducts from "../../../hooks/ProductsPageHooks/useProducts";
import Pagination from "./Pagination";

interface ProductsProps {
    category: string;
    columns: string[];
}

function Products({category, columns}: ProductsProps) {
    const {productsData, currentPage, loading, entriesPerPage, totalEntries, setCurrentPage} = useProducts(category);

    function paginate(pageNumber: number) {
        setCurrentPage(pageNumber)
    }

    const productsList = productsData["products"];

    const style = {
        color: 'white',
    };

    const spinnerStyle = {
        width: "3rem",
        height: "3rem"
    }

    const renderValue = (value: any) => {
        if (value === null)
            return 'N/A';

        if (typeof value === 'boolean')
            return value ? 'Yes' : 'No';

        return value;
    };

    console.log("Current page: ", currentPage)

    return (
        loading
            ?
            <div className="d-flex flex-row justify-content-center mt-5">
                <div style={spinnerStyle} className="spinner-border text-primary" role="status"> </div>
            </div>
            :
            <div className="ms-5 mt-4" style={style}>
                {
                    productsList.map((product: any, index: number) =>
                        <div className="ms-5 mb-4" key={index}>
                            {
                                columns.map((column, idx) => (
                                    <div key={idx}>
                                        <strong>{column}</strong>: {renderValue(product[column])}
                                    </div>
                                ))
                            }
                        </div>
                    )
                }
                <div className="mt-3 mb-5">
                    <Pagination entriesPerPage={entriesPerPage} totalEntries={totalEntries} paginate={paginate}/>
                </div>
            </div>
    );
}

export default Products;
