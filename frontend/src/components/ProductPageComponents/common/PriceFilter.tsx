import React from 'react';
import Slider from 'rc-slider';
import 'rc-slider/assets/index.css';

const PriceFilter = () => {
    return (
        <div>
            <Slider range min={0} max={100} />
        </div>
    );
};

export default PriceFilter;
