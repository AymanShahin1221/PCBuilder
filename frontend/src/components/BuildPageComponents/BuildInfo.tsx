import useBuildInfo from "../../hooks/BuildPageHooks/useBuildInfo";

function BuildInfo() {

    const {buildName, budget} = useBuildInfo();

    return (
        <div className="BuildPage_build-info-container container-fluid w-75 mt-2">
            <div className="d-flex flex-row justify-content-start py-3">
                <h4 className="me-5">{buildName}</h4>
                <h4 className="ms-4">Budget: <span className="BuildPage_budget-header">{budget !== "None" ? "$" + budget : "--"}</span></h4>
            </div>
        </div>
    );
}

export default BuildInfo;