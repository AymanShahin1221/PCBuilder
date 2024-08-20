import Header from "../../components/common/Header";
import NavBar from "../../components/common/NavBar";
import PriceFilter from "../../components/ProductPageComponents/common/PriceFilter";
import ProductsPageHeader from "../../components/ProductPageComponents/common/ProductsPageHeader";
import ProductsTable from "../../components/ProductPageComponents/common/ProductsTable";

import "../../css/ProductPagesStyles/CaseProductsPageStyles.css";
import "../../css/ProductPagesStyles/common/CommonStyles.css";

function CaseProductsPage() {
    const data_columns = [
        "name", "price", "internal_35_bays",
        "color", "external_volume", "sidePanel",
        "type", "add_item", "imageLocation"
    ];

    const header_columns = [
        "Name", "Price", "# Internal 3.5\" Bays",
        "Color", "External Volume", "Side Panel",
        "Type", "Add Item"
    ];

    const unitsMap = {
        "": ""
    };

    const pagePrefix = "CaseProductsPage_";

    const filters = [
        <PriceFilter pagePrefix={pagePrefix} minPrice={0} maxPrice={4200}/>
    ];

    return (
        <div>
            <Header/>
            <NavBar/>
            <ProductsPageHeader title={"Case"} pagePrefix={pagePrefix}/>
            <ProductsTable
                category={"Case"}
                data_columns={data_columns}
                header_columns={header_columns}
                pagePrefix={pagePrefix}
                unitsMap={unitsMap}
                filters={filters}
            />
        </div>
    );
}

export default CaseProductsPage;