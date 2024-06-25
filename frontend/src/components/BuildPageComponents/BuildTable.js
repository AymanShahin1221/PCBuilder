import removeIcon from "../../assets/svgs/BuildPage/remove-icon.svg";
import GenericAddPartButton from "./GenericAddPartButton";

/**
 * BuildTable component manages a table for selecting and displaying various PC components.
 * @component
 */
function BuildTable() {

    /**
     * Table data fetched from database
     */
    let tableData = [
        { category: "Case", chosenProduct: { imgSrc: null, description: null }, basePrice: null, vendorPrice: null },
        { category: "Cooler", chosenProduct: { imgSrc: null, description: null }, basePrice: null, vendorPrice: null },
        { category: "CPU", chosenProduct: { imgSrc: null, description: null }, basePrice: null, vendorPrice: null },
        { category: "GPU", chosenProduct: { imgSrc: null, description: null }, basePrice: null, vendorPrice: null },
        { category: "Keyboard", chosenProduct: { imgSrc: null, description: null }, basePrice: null, vendorPrice: null },
        { category: "Memory", chosenProduct: { imgSrc: null, description: null }, basePrice: null, vendorPrice: null },
        { category: "Monitor", chosenProduct: { imgSrc: null, description: null }, basePrice: null, vendorPrice: null },
        { category: "Motherboard", chosenProduct: { imgSrc: null, description: null }, basePrice: null, vendorPrice: null },
        { category: "OS", chosenProduct: { imgSrc: null, description: null }, basePrice: null, vendorPrice: null },
        { category: "PowerSupply", chosenProduct: { imgSrc: null, description: null }, basePrice: null, vendorPrice: null },
        { category: "Storage", chosenProduct: { imgSrc: null, description: null }, basePrice: null, vendorPrice: null },
    ];

    function getDataByCategory(categoryName) {
        for(let i = 0; i < tableData.length; i++)
        {
            if(tableData[i].category === categoryName)
                return tableData[i];
        }
    }

    /**
     *
     * @param categoryName represents category name of a particular field.
     * If both the image source and description are not present in tableData, a default button will be displayed prompting the user to add an item
     * @returns {JSX.Element}
     */
    function conditionallyRenderProductDetails(categoryName) {
        const product = getDataByCategory(categoryName);
        const imgSrc = product.chosenProduct.imgSrc;
        const description = product.chosenProduct.description;

        if (imgSrc && description)
        {
            console.log("both are present")
            return (
                <div className="BuildPage_info-container d-flex flex-row">
                    <img className="BuildPage_product-img img-fluid me-3" src={imgSrc}/>
                    <p>{description}</p>
                </div>
            );
        }
        else
        {
            console.log("both are not present")
            return (
              <GenericAddPartButton category={categoryName}/>
            );
        }
    }

    return (
        <div className="BuildPage_table-container">
            <table className="BuildPage_table container-fluid mt-2 mb-5">
                <tbody>
                <tr className="BuildPage_table-header">
                    <th>Category</th>
                    <th>Item</th>
                    <th>Base Price</th>
                    <th>Vendor Price</th>
                    <th>Clear</th>
                </tr>

                <tr>
                    <td data-cell="category">CPU</td>
                    <td data-cell="chosen-product">
                        {conditionallyRenderProductDetails("CPU")}
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">
                        {/*<a className="BuildPage_vendor-product-link" href="#"><img className="BuildPage_product-vendor-logo img-fluid me-3" />*/}
                        {/*    <img className="BuildPage_link-icon mb-2 img-fluid ms-1" src={linkIcon}/>*/}
                        {/*</a>*/}

                    </td>
                    <td data-cell="action">
                        <div className="BuildPage_actions-container d-flex flex-row">
                            <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td data-cell="category">GPU</td>
                    <td data-cell="chosen-product">
                        <div className="BuildPage_info-container d-flex flex-row">
                            {/*<img className="BuildPage_product-img img-fluid me-3"*/}
                            {/*     src="https://m.media-amazon.com/images/I/61jRMCAX4CL.__AC_SX300_SY300_QL70_FMwebp_.jpg"/>*/}
                            {/*<p>MSI RTX 4070 TI</p>*/}
                        </div>
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">
                        {/*<a className="BuildPage_vendor-product-link" href="#">*/}
                        {/*    <img className="BuildPage_product-vendor-logo img-fluid me-3" /><img className="BuildPage_link-icon mb-2 img-fluid ms-1" src={linkIcon}/>*/}
                        {/*</a>*/}
                    </td>
                    <td data-cell="action">
                        <div className="BuildPage_actions-container d-flex flex-row">

                            <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td data-cell="category">Memory</td>
                    <td data-cell="chosen-product">
                        <div className="BuildPage_info-container d-flex flex-row">
                            {/*<img className="BuildPage_product-img img-fluid me-3"*/}
                            {/*     src="https://m.media-amazon.com/images/I/61jRMCAX4CL.__AC_SX300_SY300_QL70_FMwebp_.jpg"/>*/}
                            {/*<p>Intel Core i9 14900k</p>*/}
                        </div>
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">
                        {/*<a className="BuildPage_vendor-product-link" href="#">*/}
                        {/*    <img className="BuildPage_product-vendor-logo img-fluid me-3" /><img className="BuildPage_link-icon mb-2 img-fluid ms-1" src={linkIcon}/>*/}
                        {/*</a>*/}
                    </td>
                    <td data-cell="action">
                        <div className="BuildPage_actions-container d-flex flex-row">

                            <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td data-cell="category">Storage</td>
                    <td data-cell="chosen-product">
                        <div className="BuildPage_info-container d-flex flex-row">
                            {/*<img className="BuildPage_product-img img-fluid me-3"*/}
                            {/*     src="https://m.media-amazon.com/images/I/61jRMCAX4CL.__AC_SX300_SY300_QL70_FMwebp_.jpg"/>*/}
                            {/*<p>Intel Core i9 14900k</p>*/}
                        </div>
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">
                        {/*<a className="BuildPage_vendor-product-link" href="#">*/}
                        {/*    <img className="BuildPage_product-vendor-logo img-fluid me-3" /><img className="BuildPage_link-icon mb-2 img-fluid ms-1" src={linkIcon}/>*/}
                        {/*</a>*/}
                    </td>
                    <td data-cell="action">
                        <div className="BuildPage_actions-container d-flex flex-row">

                            <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td data-cell="category">Cooler</td>
                    <td data-cell="chosen-product">
                        <div className="BuildPage_info-container d-flex flex-row">
                            {/*<img className="BuildPage_product-img img-fluid me-3"*/}
                            {/*     src="https://m.media-amazon.com/images/I/61jRMCAX4CL.__AC_SX300_SY300_QL70_FMwebp_.jpg"/>*/}
                            {/*<p>Intel Core i9 14900k</p>*/}
                        </div>
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">
                        {/*<a className="BuildPage_vendor-product-link" href="#">*/}
                        {/*    <img className="BuildPage_product-vendor-logo img-fluid me-3" /><img className="BuildPage_link-icon mb-2 img-fluid ms-1" src={linkIcon}/>*/}
                        {/*</a>*/}
                    </td>
                    <td data-cell="action">
                        <div className="BuildPage_actions-container d-flex flex-row">

                            <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td data-cell="category">Power Supply</td>
                    <td data-cell="chosen-product">
                        <div className="BuildPage_info-container d-flex flex-row">
                            {/*<img className="BuildPage_product-img img-fluid me-3"*/}
                            {/*     src="https://m.media-amazon.com/images/I/61jRMCAX4CL.__AC_SX300_SY300_QL70_FMwebp_.jpg"/>*/}
                            {/*<p>Intel Core i9 14900k</p>*/}
                        </div>
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">
                        {/*<a className="BuildPage_vendor-product-link" href="#">*/}
                        {/*    <img className="BuildPage_product-vendor-logo img-fluid me-3" /><img className="BuildPage_link-icon mb-2 img-fluid ms-1" src={linkIcon}/>*/}
                        {/*</a>*/}
                    </td>
                    <td data-cell="action">
                        <div className="BuildPage_actions-container d-flex flex-row">

                            <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td data-cell="category">Motherboard</td>
                    <td data-cell="chosen-product">
                        <div className="BuildPage_info-container d-flex flex-row">
                            {/*<img className="BuildPage_product-img img-fluid me-3"*/}
                            {/*     src="https://m.media-amazon.com/images/I/61jRMCAX4CL.__AC_SX300_SY300_QL70_FMwebp_.jpg"/>*/}
                            {/*<p>Intel Core i9 14900k</p>*/}
                        </div>
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">
                        {/*<a className="BuildPage_vendor-product-link" href="#">*/}
                        {/*    <img className="BuildPage_product-vendor-logo img-fluid me-3" /><img className="BuildPage_link-icon mb-2 img-fluid ms-1" src={linkIcon}/>*/}
                        {/*</a>*/}
                    </td>
                    <td data-cell="action">
                        <div className="BuildPage_actions-container d-flex flex-row">

                            <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td data-cell="category">Case</td>
                    <td data-cell="chosen-product">
                        <div className="BuildPage_info-container d-flex flex-row">
                            {/*<img className="BuildPage_product-img img-fluid me-3"*/}
                            {/*     src="https://m.media-amazon.com/images/I/61jRMCAX4CL.__AC_SX300_SY300_QL70_FMwebp_.jpg"/>*/}
                            {/*<p>Intel Core i9 14900k</p>*/}
                        </div>
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">
                        {/*<a className="BuildPage_vendor-product-link" href="#">*/}
                        {/*    <img className="BuildPage_product-vendor-logo img-fluid me-3" /><img className="BuildPage_link-icon mb-2 img-fluid ms-1" src={linkIcon}/>*/}
                        {/*</a>*/}
                    </td>
                    <td data-cell="action">
                        <div className="BuildPage_actions-container d-flex flex-row">

                            <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td data-cell="category">Keyboard</td>
                    <td data-cell="chosen-product">
                        <div className="BuildPage_info-container d-flex flex-row">
                            {/*<img className="BuildPage_product-img img-fluid me-3"*/}
                            {/*     src="https://m.media-amazon.com/images/I/61jRMCAX4CL.__AC_SX300_SY300_QL70_FMwebp_.jpg"/>*/}
                            {/*<p>Intel Core i9 14900k</p>*/}
                        </div>
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">
                        {/*<a className="BuildPage_vendor-product-link" href="#">*/}
                        {/*    <img className="BuildPage_product-vendor-logo img-fluid me-3" /><img className="BuildPage_link-icon mb-2 img-fluid ms-1" src={linkIcon}/>*/}
                        {/*</a>*/}
                    </td>
                    <td data-cell="action">
                        <div className="BuildPage_actions-container d-flex flex-row">

                            <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td data-cell="category">OS</td>
                    <td data-cell="chosen-product">
                        <div className="BuildPage_info-container d-flex flex-row">
                            {/*<img className="BuildPage_product-img img-fluid me-3"*/}
                            {/*     src="https://m.media-amazon.com/images/I/61jRMCAX4CL.__AC_SX300_SY300_QL70_FMwebp_.jpg"/>*/}
                            {/*<p>Intel Core i9 14900k</p>*/}
                        </div>
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">
                        {/*<a className="BuildPage_vendor-product-link" href="#">*/}
                        {/*    <img className="BuildPage_product-vendor-logo img-fluid me-3" /><img className="BuildPage_link-icon mb-2 img-fluid ms-1" src={linkIcon}/>*/}
                        {/*</a>*/}
                    </td>
                    <td data-cell="action">
                        <div className="BuildPage_actions-container d-flex flex-row">

                            <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td data-cell="category">Monitor</td>
                    <td data-cell="chosen-product">
                        <div className="BuildPage_info-container d-flex flex-row">
                                {/*<img className="BuildPage_product-img img-fluid me-3"*/}
                                {/*     src="https://m.media-amazon.com/images/I/61jRMCAX4CL.__AC_SX300_SY300_QL70_FMwebp_.jpg"/>*/}
                                {/*<p>Intel Core i9 14900k</p>*/}
                        </div>
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">
                        {/*<a className="BuildPage_vendor-product-link" href="#">*/}
                        {/*    <img className="BuildPage_product-vendor-logo img-fluid me-3" /><img className="BuildPage_link-icon mb-2 img-fluid ms-1" src={linkIcon}/>*/}
                        {/*</a>*/}
                    </td>
                    <td data-cell="action">
                        <div className="BuildPage_actions-container d-flex flex-row">

                            <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                        </div>
                    </td>
                </tr>
                <tr className="BuildPage_grand-total-container">
                    <td className="BuildPage_grand-total-col" colSpan="5">
                        <div
                            className="BuildPage_grand-total-container d-flex flex-row justify-content-center mx-5">
                            Grand Total: $
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}

export default BuildTable;