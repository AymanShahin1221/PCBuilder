import Header from "../components/common/Header";
import NavBar from "../components/common/NavBar";
import BuildPageHeader from "../components/BuildPageComponents/Header";
import BuildInfo from "../components/BuildPageComponents/BuildInfo";
import BuildTable from "../components/BuildPageComponents/BuildTable";

import "../css/BuildPageStyles.css";

function BuildPage() {
    return (
      <div>
          <Header/>
          <NavBar/>
          <BuildPageHeader/>
          <BuildInfo/>
          <BuildTable/>
      </div>
    );
}

export default BuildPage;