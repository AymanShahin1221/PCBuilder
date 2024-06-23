import HomePage from "./pages/HomePage";
import BuildSetupPage from "./pages/BuildSetupPage";
import BuildPage from "./pages/BuildPage";

import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route index element={<HomePage/>}/>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/home" element={<HomePage/>}/>
                <Route path="/setup" element={<BuildSetupPage/>}/>
                <Route path="/build" element={<BuildPage/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;


