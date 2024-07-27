import removeIcon from "../../assets/svgs/BuildPage/remove-icon.svg";
import useBuildData from "../../hooks/BuildPageHooks/useBuildData";
import GenericAddPartButton from "./GenericAddPartButton";
import {BuildData, ChosenProduct, TableRowProps } from "../../types/BuildPageTypes/Types";
import {ReactElement} from "react";

function getProductsNamesAndImages(chosenProducts: ChosenProduct[]): ReactElement[] {
    return chosenProducts.map(
        product =>
            <div className="BuildPage_info-container d-flex flex-row my-4" key={product.name}>
                <img className="BuildPage_product-img img-fluid me-2" src={product.imgSrc!} alt={product.name || 'Product Image'} />
                <p>{product.name}</p>
            </div>
    );
}

function getProductPrices(chosenProducts: ChosenProduct[]): ReactElement[] {
    return chosenProducts.map(
        product =>
            <div className="BuildPage_info-container d-flex flex-row my-4" key={product.basePrice}>
                <p>{product.basePrice}</p>
            </div>
    );
}

function TableHeader() {
    return (
        <tr className="BuildPage_table-header">
            <th>Category</th>
            <th>Item</th>
            <th>Base Price</th>
            <th>Vendor Price</th>
            <th>Clear</th>
        </tr>
    );
}

function GrandTotal() {
    return (
        <tr className="BuildPage_grand-total-container">
            <td className="BuildPage_grand-total-col" colSpan={5}>
                <div
                    className="BuildPage_grand-total-container d-flex flex-row justify-content-center mx-5">
                    Grand Total: $
                </div>
            </td>
        </tr>
    );
}

/**
 * BuildTable component manages a table for selecting and displaying PC component chosen by the user
 * @component
 */
function BuildTable() {

    const buildData = useBuildData();

    function getBuildDataByCategory(category: string): BuildData | undefined {
        return buildData.find(item => item.productCategory === category);
    }

    function renderProductsNamesAndImages(categoryName: string): ReactElement[] | ReactElement {
        const categoryBuildData = getBuildDataByCategory(categoryName);

        if (categoryBuildData && categoryBuildData.chosenProducts.length !== 0)
            return getProductsNamesAndImages(categoryBuildData.chosenProducts);

        else
            return <GenericAddPartButton category={categoryName} />;

    }

    function renderProductPrices(categoryName: string): ReactElement[] | undefined {
        const categoryBuildData = getBuildDataByCategory(categoryName);

        if (categoryBuildData && categoryBuildData.chosenProducts.length !== 0)
            return getProductPrices(categoryBuildData.chosenProducts);
    }

    function TableRow({categoryName}: TableRowProps): ReactElement {
        return (
            <tr>
                <td data-cell="category">{categoryName}</td>
                <td data-cell="chosen-product">
                    {renderProductsNamesAndImages(categoryName)}
                </td>

                <td data-cell="base-price">{renderProductPrices(categoryName)}</td>
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

                    <TableHeader/>

                    <TableRow categoryName={"CPU"}/>
                    <TableRow categoryName={"GPU"}/>
                    <TableRow categoryName={"Memory"}/>
                    <TableRow categoryName={"Storage"}/>
                    <TableRow categoryName={"Cooler"}/>
                    <TableRow categoryName={"Power Supply"}/>
                    <TableRow categoryName={"Motherboard"}/>
                    <TableRow categoryName={"Case"}/>
                    <TableRow categoryName={"Keyboard"}/>
                    <TableRow categoryName={"OS"}/>
                    <TableRow categoryName={"Monitor"}/>

                    <GrandTotal/>
                </tbody>
            </table>
        </div>
    );
}

export default BuildTable;