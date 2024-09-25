import { useState } from "react";
import Register from "./components/Register.jsx";
import Login from "./components/Login.jsx";
import "./styles/App.css";

function App() {
    const [isNotLoggedIn, setIsNotLoggedIn] = useState(false);

    return <Login />;
}

export default App;
