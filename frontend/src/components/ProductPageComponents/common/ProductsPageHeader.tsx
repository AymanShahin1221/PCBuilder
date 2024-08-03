interface HeaderProps {
    title: string
    pagePrefix: string
}

function ProductsPageHeader({ title, pagePrefix }: HeaderProps) {
    return (
        <div className={pagePrefix + "title-container container-fluid p-2"}>
            <div className="d-flex justify-content-center">
                <div className="d-flex align-items-center mb-md-0">
                    <h2 className={pagePrefix + "title"}>Choose a {title}</h2>
                    <div/>
                </div>
            </div>
        </div>
    );
}

export default ProductsPageHeader;