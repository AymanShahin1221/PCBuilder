import Header from "../components/common/Header";
import NavBar from "../components/common/NavBar";

import SetupForm from "../components/BuildSetupPageComponents/SetupForm";
import "../css/BuildSetupPage.css";

function BuildSetupPage() {
    return(
        <div>
            <Header/>
            <NavBar/>
            <SetupForm/>
        </div>
    );
}

export default BuildSetupPage;