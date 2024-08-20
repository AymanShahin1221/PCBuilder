import removeIcon from "../../assets/svgs/BuildPage/remove-icon.svg";
import useBuildData from "../../hooks/BuildPageHooks/useBuildData";
import GenericAddPartButton from "./GenericAddPartButton";
import {BuildData, TableRowProps} from "../../types/BuildPageTypes/types";
import {memo, ReactElement} from "react";

function TableHeader() {
    return (
        <tr className="BuildPage_table-header">
            <th>Category</th>
            <th>Item</th>
            <th>Base Price</th>
            <th>Clear</th>
        </tr>
    );
}

function GrandTotal() {
    return (
        <tr className="BuildPage_grand-total-container">
            <td className="BuildPage_grand-total-col" colSpan={5}>
                <div
                    className="BuildPage_grand-total-container d-flex flex-row justify-content-center">
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

    function getBuildDataByCategory(categoryName: string): BuildData | undefined {
        return buildData.find(item => item.productCategory === categoryName);
    }


    const ProductInfo = memo(({category}: { category: string }): ReactElement => {
        const products = getBuildDataByCategory(category)?.chosenProducts;
        const ifProductsExist: boolean | undefined = products && products?.length > 0;

        return (
            <>
                <td data-cell="chosen-product">
                    {
                        ifProductsExist ? products?.map(
                                product =>
                                    <div className="BuildPage_info-container d-flex flex-row my-4" key={product.name}>
                                        <img className="BuildPage_product-img img-fluid me-2" src={product.imgSrc!}
                                             alt={product.name || 'Product Image'}/>
                                        <p>{product.name}</p>
                                    </div>
                            )
                            : <GenericAddPartButton category={category}/>
                    }
                </td>

                <td data-cell="base-price">
                    {
                        ifProductsExist ? products?.map(
                                product =>
                                    <div className="BuildPage_info-container d-flex flex-row my-4" key={product.basePrice}>
                                        <p>{product.basePrice}</p>
                                    </div>
                            )
                            : null
                    }
                </td>
            </>
        );
    });

    const TableRow = memo(({categoryName}: TableRowProps): ReactElement => (
        <tr>
            <td data-cell="category">{categoryName}</td>
            <ProductInfo category={categoryName}/>
            <td data-cell="action">
                <div className="BuildPage_actions-container d-flex flex-row">
                    <img className="BuildPage_remove-icon img-fluid ms-3" src={removeIcon}/>
                </div>
            </td>
        </tr>
    ));

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