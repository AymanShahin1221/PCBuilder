import removeIcon from "../../assets/svgs/BuildPage/remove-icon.svg";
import useBuildData from "../../hooks/BuildPageHooks/useBuildData";
import GenericAddPartButton from "./GenericAddPartButton";
import {BuildDataType, ChosenProductType, TableRowProps } from "../../types/BuildPageTypes/types";
import {ReactElement} from "react";

function renderProductsNamesAndImages(chosenProducts: ChosenProductType[]): ReactElement[] {
    return chosenProducts.map(
        product =>
            <div className="BuildPage_info-container d-flex flex-row my-4" key={product.name}>
                <img className="BuildPage_product-img img-fluid me-2" src={product.imgSrc!} alt={product.name || 'Product Image'} />
                <p>{product.name}</p>
            </div>
    );
}


/**
 * BuildTable component manages a table for selecting and displaying PC component chosen by the user
 * @component
 */
function BuildTable() {

    const buildData = useBuildData();

    function getBuildDataByCategory(category: string): BuildDataType | undefined {
        return buildData.find(item => item.productCategory === category);
    }

    function renderUserBuildDataByCategory(categoryName: string): ReactElement[] | ReactElement {
        const categoryBuildData = getBuildDataByCategory(categoryName);

        if (categoryBuildData && categoryBuildData.chosenProducts.length !== 0)
            return renderProductsNamesAndImages(categoryBuildData.chosenProducts);

        else
            return <GenericAddPartButton category={categoryName} />;

    }

    function TableRow({ categoryName } : TableRowProps): ReactElement {
        return (
            <tr>
                <td data-cell="category">{categoryName}</td>
                <td data-cell="chosen-product">
                    {renderUserBuildDataByCategory(categoryName)}
                </td>

                <td data-cell="base-price">{}</td>
                <td data-cell="vendor-price">{}</td>

                <td data-cell="action">
                    <div className="BuildPage_actions-container d-flex flex-row">
                        <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                    </div>
                </td>
            </tr>
        );
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
                        {renderUserBuildDataByCategory("CPU")}
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">

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
                        {renderUserBuildDataByCategory("GPU")}
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">
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
                            {renderUserBuildDataByCategory("Memory")}
                        </div>
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">

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
                            {renderUserBuildDataByCategory("Storage")}
                        </div>
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">

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
                        {renderUserBuildDataByCategory("Cooler")}
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">

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
                        {renderUserBuildDataByCategory("Power Supply")}
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">

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
                        {renderUserBuildDataByCategory("Motherboard")}
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">

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
                        {renderUserBuildDataByCategory("Case")}
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">

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
                        {renderUserBuildDataByCategory("Keyboard")}
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">

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
                        {renderUserBuildDataByCategory("OS")}
                    </td>

                    <td data-cell="base-price"></td>
                    <td data-cell="vendor-price">

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
                        {renderUserBuildDataByCategory("Monitor")}
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
                    <td className="BuildPage_grand-total-col" colSpan={5}>
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