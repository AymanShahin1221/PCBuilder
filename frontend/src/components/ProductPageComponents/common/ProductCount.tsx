interface ProductCountProps {
    numberOfProducts: number
    pagePrefix: string
}

function ProductCount({ numberOfProducts, pagePrefix }: ProductCountProps) {
    return(
        <div className={pagePrefix + "total-entries-container"}>
            <h2>Browse {numberOfProducts} Products</h2>
        </div>
    );
}

export default ProductCount;