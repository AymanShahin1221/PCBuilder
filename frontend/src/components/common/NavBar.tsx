import React, { useState } from 'react';
import homeIcon from "../../assets/svgs/common/home-icon.svg";
import cpuIcon from "../../assets/svgs/common/cpu-icon.svg";
import laptopIcon from "../../assets/svgs/common/laptop-icon.svg";
import linkIcon from "../../assets/svgs/common/link-icon.svg";
import gearIcon from "../../assets/svgs/common/gear-icon.svg";
import graphIcon from "../../assets/svgs/common/graph-icon.svg";

function NavBar() {
    const [isOpen, setIsOpen] = useState(false);

    const toggleNav = () => {
        setIsOpen(!isOpen);
    };

    return (
        <nav className="navbar navbar-expand-lg p-3 navbar-main">
            <button className="navbar-toggler" type="button" onClick={toggleNav} aria-expanded={isOpen ? "true" : "false"}>
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className={`collapse navbar-collapse ${isOpen ? 'show' : ''}`}>
                <ul className="items navbar-nav mx-3">
                    <li className="nav-item mx-3">
                        <a className="home-link nav-link" href="/home"><img src={homeIcon} className="navbar-icon img-fluid mb-1 mx-2" /> Home</a>
                    </li>
                    <li className="nav-item mx-3">
                        <a className="home-link nav-link" href="/setup"><img src={gearIcon} className="navbar-icon img-fluid mb-1 mx-2" /> Builder</a>
                    </li>
                    <li className="nav-item mx-3">
                        <a className="nav-link" href="#"><img src={cpuIcon} className="navbar-icon img-fluid mb-1 mx-2" /> Saved Builds</a>
                    </li>
                    <li className="nav-item mx-3">
                        <a className="nav-link" href="#"><img src={laptopIcon} className="navbar-icon img-fluid mb-1 mx-2" /> Products</a>
                    </li>
                    <li className="nav-item mx-3">
                        <a className="nav-link" href="#"><img src={linkIcon} className="navbar-icon img-fluid mb-1 mx-2" /> Resources</a>
                    </li>
                    <li className="nav-item mx-3">
                        <a className="nav-link" href="#"><img src={graphIcon} className="navbar-icon img-fluid mb-1 mx-2" /> Price Tracker</a>
                    </li>
                </ul>
            </div>
        </nav>
    );
}

export default NavBar;