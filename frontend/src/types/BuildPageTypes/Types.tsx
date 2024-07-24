export interface ChosenProduct {
    name: string | null;
    basePrice: number | null;
    imgSrc: string | null;
}

export interface BuildData {
    productCategory: string;
    chosenProducts: ChosenProduct[];
}