import useBuildInfo from "../../hooks/BuildPageHooks/useBuildInfo";

function BuildInfo() {

    const {buildName, budget} = useBuildInfo();

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