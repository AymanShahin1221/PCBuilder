import removeIcon from "../../assets/svgs/BuildPage/remove-icon.svg";

function BuildTable() {
    return (
        <div className="BuildPage_table-container">
            <table className="BuildPage_table container-fluid mt-2 mb-5">
                <tbody>
                <tr className="BuildPage_table-header">
                    <th>Category</th>
                    <th>Items</th>
                    <th>Base Price</th>
                    <th>Vendor Price</th>
                    <th>Clear</th>
                </tr>
                <tr>
                    <td data-cell="category">CPU</td>
                    <td data-cell="chosen-product">
                        <div className="BuildPage_info-container d-flex flex-row">
                            {/*<img className="BuildPage_product-img img-fluid me-3" src="https://m.media-amazon.com/images/I/61jRMCAX4CL.__AC_SX300_SY300_QL70_FMwebp_.jpg"/>*/}
                            <p>Intel Core i9 14900k</p>
                        </div>
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
                            <p>MSI RTX 4070 TI</p>
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