// Header.js
import React from 'react';
import logo from '../../assets/images/common/logo.png';
import registerIcon from '../../assets/svgs/common/register-icon.svg';
import loginIcon from '../../assets/svgs/common/login-icon.svg';

function Header() {
    return (
        <div className="Header">
            <Title />
        </div>
    );
}

function Title() {
    return (
        <div className="site-title-container container-fluid p-4">
            <div className="d-flex flex-column flex-md-row justify-content-between align-items-center">
                <div className="d-flex align-items-center mb-3 mb-md-0">
                    <a href="http://localhost:3000/" className="d-flex flex-row main-header-href">
                        <h1 className="display-4 fw-bold title m-0">
                            <span className="title-section1">PC</span>
                            <span className="title-section2">Builder</span>
                        </h1>
                        <img className="logo img-fluid ms-3" src={logo} alt="Logo" />
                    </a>
                </div>
                <div className="d-flex justify-content-md-end">
                    <h1><a className="login-link mx-3" href="#">Log In <img src={loginIcon} className="login-icon img-fluid mb-1" /></a></h1>
                    <h1><a className="register-link mx-3" href="#">Sign Up <img src={registerIcon} className="register-icon img-fluid mb-1 mx-2" /></a></h1>
                </div>
            </div>
        </div>
    );
}

export default Header;