import PriceFilter from "./PriceFilter";

function Filters() {
    return (
        <div className={pagePrefix + "filter-container d-flex flex-column align-items-center justify-content-center mt-4"}>
        <div className={pagePrefix + "filter-header-container text-align-center p-2 container-fluid"}>
        <h3 className="text-center">Filters</h3>
            </div>

    {/* maxPrice is currently hardcoded*/}
    <PriceFilter pagePrefix={pagePrefix} minPrice={0} maxPrice={4200}/>
    {/* Next common filters go after this... */}
    </div>
    );
}

export default Filters;