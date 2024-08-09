import useBuildInfo from "../../../hooks/BuildPageHooks/useBuildInfo";

function CurrentBuildInfo() {

    const {budget} = useBuildInfo();

    return (
        <div className="info-container col-sm-11 col-md-6 col-lg-1 d-flex flex-column align-items-center mw-100 w-auto">
            <div className="info-title-container container-fluid">
                <div>
                    <h2>Your Build</h2>
                </div>
            </div>
            <div className="current-info container-fluid d-flex flex-column align-items-center">
                <div className="info-fields-container d-flex flex-row">
                    <div className="d-flex flex-column info-field">
                        <h4>Budget</h4>
                        <h4><span className="budget-header-span">{budget !== "None" ? "$" + budget : "--"}</span></h4>
                    </div>

                    <div className="d-flex flex-column info-field">
                        <h4>Total</h4>
                        <h4><span className="total-cost-header-span">$0.00</span></h4>
                    </div>

                    <div className="d-flex flex-column info-field">
                        <h4>Remaining</h4>
                        <h4><span className="remaining-header-span">{budget !== "None" ? "$" + budget : "--"}</span></h4>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CurrentBuildInfo;
