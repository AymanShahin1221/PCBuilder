import React, { useEffect, useState } from "react";

function BuildInfo() {

    const [buildName, setBuildName] = useState<string>('');
    const [budget, setBudget] = useState<string>('');

    function fetchBuildData() {
        fetch("http://localhost:8081/api/getBuildData", {
            method: "GET",
            mode: "cors"
        })
            .then(response => {
                if (!response.ok)
                    throw new Error('Network response was not ok');

                return response.json();
            })
            .then(data => {
                setBuildName(data.buildName);
                setBudget(data.budget === null ? " None" : " $" + data.budget.toLocaleString("en-us", { minimumFractionDigits: 2, maximumFractionDigits: 2 }));

            })
            .catch(error => {
                console.error('There was a problem with your fetch operation:', error);
            });
    }

    useEffect(() => {
        fetchBuildData();
    }, []);

    return (
        <div className="BuildPage_build-info-container container-fluid w-75 mt-4">
            <div className="d-flex flex-row justify-content-start py-3">
                <h3 className="me-5">{buildName}</h3>
                <h3 className="ms-4">Budget:<span className="BuildPage_budget-header">{budget}</span></h3>
            </div>
        </div>
    );
}

export default BuildInfo;