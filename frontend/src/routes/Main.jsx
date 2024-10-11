import { useState } from "react";
import Navbar from "./Navbar";
import Login from "./Login";
import { useQueryClient } from "@tanstack/react-query";

function Main() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const queryClient = useQueryClient();
    const images = queryClient.getQueryData(["images"]);

    function printImages() {
        console.log(images);
    }

    // return <>{isLoggedIn ? <Navbar /> : <Login handleLogin={handleLogin} />}</>;
    return <Login printImages={printImages} />;
}

export default Main;
