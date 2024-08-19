interface HeaderProps {
    title: string
    pagePrefix: string
}

function ProductsPageHeader({ title }: HeaderProps) {
    return (
        <div className={"products-page-title-container container-fluid p-3"}>
            <div className="d-flex justify-content-center">
                <div className="d-flex align-items-center mb-md-0">
                    <h2 className={"products-page-title"}>Choose a {title}</h2>
                </div>
            </div>
        </div>
    );
}

export default ProductsPageHeader;