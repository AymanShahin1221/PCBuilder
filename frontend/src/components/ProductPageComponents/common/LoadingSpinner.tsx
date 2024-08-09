const style = {
    width: "3rem",
    height: "3rem"
}

function LoadingSpinner() {
    return (
        <div className="d-flex flex-row justify-content-center mt-5">
            <div style={style} className="spinner-border text-primary" role="status"></div>
        </div>
    );
}

export default LoadingSpinner;