import HomePage from "./pages/HomePage";
import BuildSetupPage from "./pages/BuildSetupPage";
import BuildPage from "./pages/BuildPage";

import CaseProductsPage from "./pages/product-pages/CaseProductsPage";
import CoolerProductsPage from "./pages/product-pages/CoolerProductsPage";
import CPUProductsPage from "./pages/product-pages/CPUProductsPage";
import GPUProductsPage from "./pages/product-pages/GPUProductsPage";
import KeyboardProductsPage from "./pages/product-pages/KeyboardProductsPage";
import MemoryProductsPage from "./pages/product-pages/MemoryProductsPage";
import MonitorProductsPage from "./pages/product-pages/MonitorProductsPage";
import MotherboardProductsPage from "./pages/product-pages/MotherboardProductsPage";
import OSProductsPage from "./pages/product-pages/OSProductsPage";
import PowerSupplyProductsPage from "./pages/product-pages/PowerSupplyProductsPage";
import StorageProductsPage from "./pages/product-pages/StorageProductsPage";

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
                <Route path="/build/case" element={<CaseProductsPage/>}/>
                <Route path="/build/cooler" element={<CoolerProductsPage/>}/>
                <Route path="/build/CPU" element={<CPUProductsPage/>}/>
                <Route path="/build/GPU" element={<GPUProductsPage/>}/>
                <Route path="/build/Keyboard" element={<KeyboardProductsPage/>}/>
                <Route path="/build/Memory" element={<MemoryProductsPage/>}/>
                <Route path="/build/Monitor" element={<MonitorProductsPage/>}/>
                <Route path="/build/Motherboard" element={<MotherboardProductsPage/>}/>
                <Route path="/build/OS" element={<OSProductsPage/>}/>
                <Route path="/build/PowerSupply" element={<PowerSupplyProductsPage/>}/>
                <Route path="/build/Storage" element={<StorageProductsPage/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;


