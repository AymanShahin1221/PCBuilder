import Header from '../components/common/Header';
import NavBar from '../components/common/NavBar';
import Description from "../components/HomePageComponents/Description";
import BuildButton from "../components/HomePageComponents/BuildButton";

import "../css/HomePageStyles.css";

function HomePage () {
    return (
        <div>
            <Header/>
            <NavBar/>
            <Description />
            <BuildButton/>
        </div>
    );
}

export default HomePage;