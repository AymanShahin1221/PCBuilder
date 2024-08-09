import React, { useState } from 'react';
import Slider from 'rc-slider';
import 'rc-slider/assets/index.css';

const PriceFilter = () => {
    const [range, setRange] = useState([20, 50]);

    console.log('Rendering SimpleSlider');
    console.log('Current range:', range);

    return (
        <div>
            <Slider
                range
                min={0}
                max={100}
                value={range}
                onChange={(value: any) => setRange(value)}
            />
            <input
                type="number"
                value={range[0]}
                onChange={(e) => setRange([+e.target.value, range[1]])}
            />
            <input
                type="number"
                value={range[1]}
                onChange={(e) => setRange([range[0], +e.target.value])}
            />
        </div>
    );
};

export default PriceFilter;
