import {useEffect, useState} from "react";

interface ChosenProduct {
    name: string | null;
    basePrice: number | null;
    imgSrc: string | null;
}

interface BuildData {
    productCategory: string;
    chosenProducts: ChosenProduct[];
}

function useBuildData() {
    const [buildData, setBuildData] = useState<BuildData[]>([]);

    function fetchBuildSetupData() {
        // make an api call


        // hardcoded for now

        // **************************************************************************************************************************************************
        setBuildData(
            [
                {productCategory: "Case", chosenProducts: []},
                {productCategory: "Cooler", chosenProducts: []},
                {productCategory: "CPU", chosenProducts: []},
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