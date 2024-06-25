import Header from "../components/common/Header";
import NavBar from "../components/common/NavBar";
import BuildPageHeader from "../components/BuildPageComponents/Header";
import BuildInfo from "../components/BuildPageComponents/BuildInfo";
import BuildTable from "../components/BuildPageComponents/BuildTable";

import "../css/BuildPageStyles.css";

/**
 * Consists of default webpage header, navigation bar, build page title, info of user's build (build name and budget), and a table of the user's build
 * @returns {JSX.Element}
 * @constructor
 */
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