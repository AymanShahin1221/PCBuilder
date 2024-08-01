import useProducts from "../../../hooks/ProductsPageHooks/useProducts";
import Pagination from "./Pagination";

interface ProductsTableProps {
    category: string
    data_columns: string[]
    header_columns: string[]
    pagePrefix: string
}

function ProductsTable({ category, data_columns, header_columns, pagePrefix }: ProductsTableProps) {
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
            return 'N/A';

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

    return (
        loading
            ?
            <div className="d-flex flex-row justify-content-center mt-5">
                <div style={spinnerStyle} className="spinner-border text-primary" role="status"></div>
            </div>
            :
            <div className="ms-5 me-5">
                <table className={pagePrefix + "products-table table mt-5 mb-5 container-fluid"} style={style}>
                    <tbody>
                        <TableHeader/>
                        {
                            productsList.map((product: any, index: number) =>
                                <tr key={index}>
                                    {
                                        data_columns.slice(0, -1).map((column, idx) => (
                                            <td key={idx}>
                                                {
                                                    idx === 0
                                                        ? (
                                                            <>
                                                                {renderValue(product[column])}
                                                                <img src={product[data_columns[data_columns.length - 1]]}/>
                                                            </>
                                                        )
                                                        : renderValue(product[column])
                                                }
                                            </td>
                                        ))
                                    }
                                </tr>
                            )
                        }
                    </tbody>
                </table>
                <div className="mt-3 mb-5">
                    <Pagination entriesPerPage={entriesPerPage} totalEntries={totalEntries} paginate={paginate} currentPage={currentPage}/>
                </div>
            </div>
    );

}

export default ProductsTable;
