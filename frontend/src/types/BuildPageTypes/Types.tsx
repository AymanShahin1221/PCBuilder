export interface ChosenProductType {
    name: string | null;
    basePrice: number | null;
    imgSrc: string | null;
}

export interface BuildDataType {
    productCategory: string;
    chosenProducts: ChosenProductType[];
}

export interface TableRowProps {
    categoryName: string;
}