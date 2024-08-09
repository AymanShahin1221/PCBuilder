import Header from "../../components/common/Header";
import NavBar from "../../components/common/NavBar";
import ProductsTable from "../../components/ProductPageComponents/common/ProductsTable";
import ProductsPageHeader from "../../components/ProductPageComponents/common/ProductsPageHeader";

import "../../css/ProductPagesStyles/CPUProductsPageStyles.css";
import "../../css/ProductPagesStyles/common/CommonStyles.css";

function CPUProductsPage() {

    const data_columns = [
        "name", "price", "core_count",
        "core_clock", "boost_clock", "graphics",
        "tdp", "smt", "add_item", "imageLocation"
    ];

    const header_columns = [
        "Item", "Price", "# of Cores",
        "Core Clock (GHz)", "Boost Clock (GHz)", "Integrated Graphics",
        "TDP", "SMT", "Add Item"
    ];

    const unitsMap = {
        "core_clock": "GHz",
        "boost_clock": "GHz",
        "tdp": "W"
    };

    const pagePrefix = "CPUProductsPage_";

    return (
        <div>
            <Header/>
            <NavBar/>
            <ProductsPageHeader title={"CPU"} pagePrefix={pagePrefix}/>
            <ProductsTable
                category={"CPU"}
                data_columns={data_columns}
                header_columns={header_columns}
                pagePrefix={pagePrefix}
                unitsMap={unitsMap}
            />
        </div>
    );
}

export default CPUProductsPage;

