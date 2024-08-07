import { useEffect, useState } from "react";

function useBuildInfo(): {buildName: string, budget: string} {
    const [buildName, setBuildName] = useState<string>('');
    const [budget, setBudget] = useState<string>('');

    function fetchBuildSetupData() {
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
                setBudget(data.budget === null ? " None" : data.budget.toLocaleString("en-us", {minimumFractionDigits: 2, maximumFractionDigits: 2}));

            })
            .catch(error => {
                console.error('There was a problem with your fetch operation:', error);
            });
    }

    useEffect(() => {
        fetchBuildSetupData();
    }, []);

    return { buildName, budget };
}

export default useBuildInfo;