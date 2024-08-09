import React, { useState } from 'react';
import Slider from 'rc-slider';
import 'rc-slider/assets/index.css';

interface PriceFilterProps {
    pagePrefix: string;
}

const style = {
    color: "white"
}

function PriceFilter({ pagePrefix }: PriceFilterProps) {

    const [range, setRange] = useState([1, 100]);

    const onSliderChange = (value: any) => {
        setRange(value);
    };

    return (
        <div className={pagePrefix + "price-filter-container p-3"}>
            <h6 className="mb-3" style={style}>Price</h6>
            <Slider range min={0} max={100} value={range} onChange={onSliderChange} />

            <div className="input-container mt-1">
                <input className={pagePrefix + "slider-input slider-input"} type="text" value={"$" + range[0]} onChange={(e) => setRange([+e.target.value, range[1]])}/>
                <input className={pagePrefix + "slider-input slider-input text-end"} type="text" value={"$" + range[1]} onChange={(e) => setRange([range[0], +e.target.value])}/>
            </div>
        </div>
    );
}

export default PriceFilter;