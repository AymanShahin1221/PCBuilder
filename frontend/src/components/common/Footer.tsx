function Footer() {
    return (
        <footer className="main-footer container-fluid mt-5">
            <div className="container d-flex flex-row p-3">
                <div className="footer-section">
                    <h4>PC Builder</h4>
                    <ul>
                        <li>Home</li>
                        <li>Builder</li>
                        <li>Saved Builds</li>
                        <li>Products</li>
                        <li>Resources</li>
                        <li>Price Tracker</li>
                    </ul>
                </div>
                <div className="footer-section">
                    <h4>Account</h4>
                    <ul>
                        <li>Log In</li>
                        <li>Sign Up</li>
                    </ul>
                </div>
                <div className="footer-section">
                    <h4>Github</h4>
                    <ul>
                        <li>View Project Repository</li>
                    </ul>
                </div>
            </div>
            <div className="container footer-end-container">
                <h6>&copy;  {new Date().getFullYear()} PCBuilder, All Right Reserved.</h6>
            </div>
        </footer>
    );
}

export default Footer;