import React from "react";

interface FiltersProps {
    pagePrefix: string
    filters: React.ReactNode[];
}

function Filters({ pagePrefix, filters }: FiltersProps) {
    return (
        <div className={pagePrefix + "filter-container d-flex flex-column align-items-center justify-content-center mt-4"}>
            <div className={pagePrefix + "filter-header-container text-align-center p-2 container-fluid"}>
                <h3 className="text-center">Filters</h3>
            </div>

            {
                filters.map((filter, index) => (
                  <>
                      {filter}
                  </>
                ))
            }

        </div>
    );
}

export default Filters;