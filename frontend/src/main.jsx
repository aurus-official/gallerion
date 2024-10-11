import { createRoot } from "react-dom/client";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "./routes/Login.jsx";
import Main from "./routes/Main.jsx";
import Register from "./routes/Register.jsx";
import "./styles/main.css";

const domNode = document.getElementById("root");
const root = createRoot(domNode);
const queryClient = new QueryClient();
const router = createBrowserRouter([
    {
        path: "/",
        element: <Main />,
    },
    {
        path: "/register",
        element: <Register />,
    },
]);

root.render(
    <QueryClientProvider client={queryClient}>
        <RouterProvider router={router} />
    </QueryClientProvider>
);
