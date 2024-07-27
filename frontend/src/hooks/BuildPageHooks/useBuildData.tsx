import { useEffect, useState } from "react";
import { BuildData } from "../../types/BuildPageTypes/Types";

function useBuildData() {
    const [buildData, setBuildData] = useState<BuildData[]>([]);

    function fetchBuildSetupData() {
        // make an api call


        // hardcoded build data for now

        // **************************************************************************************************************************************************
        setBuildData(
            [
                {productCategory: "Case", chosenProducts: []},
                {productCategory: "Cooler", chosenProducts: []},
                {productCategory: "CPU", chosenProducts: [{name: "Intel Core i9 14900k", imgSrc: "https://m.media-amazon.com/images/I/61jRMCAX4CL._AC_UY327_FMwebp_QL65_.jpg", basePrice: 520.99},{name: "Intel Core i9 14900k", imgSrc: "https://m.media-amazon.com/images/I/61jRMCAX4CL._AC_UY327_FMwebp_QL65_.jpg", basePrice: 520.99}]},
                {productCategory: "GPU", chosenProducts: []},
                {productCategory: "Keyboard", chosenProducts: []},
                {productCategory: "Memory", chosenProducts: []},
                {productCategory: "Monitor", chosenProducts: []},
                {productCategory: "Motherboard", chosenProducts: []},
                {productCategory: "OS", chosenProducts: []},
                {productCategory: "PowerSupply", chosenProducts: []},
                {productCategory: "Storage", chosenProducts: []}
            ]
        );

        // **************************************************************************************************************************************************

    }

    useEffect(() => {
        fetchBuildSetupData();
    }, []);

    return buildData;

}

export default useBuildData;