import useProducts from "../../../hooks/ProductsPageHooks/useProducts";
import * as process from "node:process";

/**
 *
 * @param props - props object
 * @param {String} props.category - name of product category (specifies which products are shown in table)
 * @constructor
 */

interface ProductsProps {
    category: string;
}

interface CPUProducts {
    name: string
}

function Products({ category }: ProductsProps) {
    const {products, currentPage, loading, entriesPerPage} = useProducts(category);
    console.log(JSON.stringify(products));
    return (
        <h1>
            Hello World
        </h1>
    );
}

export default Products;