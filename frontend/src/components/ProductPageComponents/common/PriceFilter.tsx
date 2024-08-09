import React, { useEffect, useState } from 'react';
import Slider from 'rc-slider';
import 'rc-slider/assets/index.css';

interface PriceFilterProps {
    pagePrefix: string;
    minPrice: number
    maxPrice: number
}

const style = {
    color: "white"
}

function PriceFilter({ pagePrefix, minPrice, maxPrice }: PriceFilterProps) {

    const [rangeStringArray, setRangeStringArray] = useState([minPrice.toString(), maxPrice.toString()]);
    const [rangeNumberArray, setRangeNumberArray] = useState([minPrice, maxPrice]);


    useEffect(() => {
        console.log(rangeNumberArray);
    }, [rangeNumberArray]);

    const onSliderChange = (value: any) => {
        setRangeStringArray(value);
        setRangeNumberArray(value);
    };

    const handleInputChange = (index: number, value: string) => {
        if (value === '' || value === '$')
        {
            const newRange: any = [...rangeStringArray];
            newRange[index] = '';
            setRangeStringArray(newRange);
            return;
        }

        const parsedValue = parseFloat(value.replace('$', ''));

        if (!isNaN(parsedValue)) {
            const newRange = [...rangeStringArray];
            newRange[index] = String(Math.max(minPrice, Math.min(maxPrice, parsedValue)));
            setRangeStringArray(newRange);
            setRangeNumberArray([Number(newRange[0]), Number(newRange[1])]);
        }
    };


    return (
        <div className={pagePrefix + "price-filter-container p-3"}>
            <h6 className="mb-3" style={style}>Price</h6>
            <Slider range min={minPrice} max={maxPrice} value={[parseInt(rangeStringArray[0]), parseInt(rangeStringArray[1])]} onChange={onSliderChange} />

            <div className="input-container mt-1">
                <input
                    className={pagePrefix + "slider-input slider-input"}
                    type="text"
                    value={'$' + rangeStringArray[0]}
                    onChange={(e) => handleInputChange(0, e.target.value)}
                />
                <input
                    className={pagePrefix + "slider-input slider-input text-end"}
                    type="text"
                    value={'$' + rangeStringArray[1]}
                    onChange={(e) => handleInputChange(1, e.target.value)}
                />
            </div>
        </div>
    );
}

export default PriceFilter;
