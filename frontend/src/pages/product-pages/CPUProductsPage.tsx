import Header from "../../components/common/Header";
import NavBar from "../../components/common/NavBar";
import Products from "../../components/ProductPageComponents/common/Products";

function CPUProductsPage() {
    return (
        <div>
            <Header/>
            <NavBar/>
            <Products category={"CPU"} columns={["name", "price", "core_count",
                                                 "core_clock", "boost_clock", "graphics",
                                                 "tdp", "smt", "imageLocation"]}/>
        </div>
    );
}

export default CPUProductsPage;

