import Header from "../../components/common/Header";
import NavBar from "../../components/common/NavBar";
import ProductsTable from "../../components/ProductPageComponents/common/ProductsTable";

import "../../css/ProductPagesStyles/CPUProductsPageStyles.css";

function CPUProductsPage() {
    const data_columns = [
        "name", "price", "core_count",
        "core_clock", "boost_clock", "graphics",
        "tdp", "smt", "imageLocation"
    ];

    const header_columns = [
        "Name", "Price", "Core Count",
        "Core Clock", "Boost Clock", "Graphics",
        "TDO", "SMT"
    ];

    const pagePrefix = "CPUProductsPage_";

    return (
        <div>
            <Header/>
            <NavBar/>
            <ProductsTable category={"CPU"} data_columns={data_columns} header_columns={header_columns} pagePrefix={pagePrefix}/>
        </div>
    );
}

export default CPUProductsPage;

