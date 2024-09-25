import { createRoot } from "react-dom/client";

import App from "./App.jsx";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const domNode = document.getElementById("root");
const root = createRoot(domNode);
const queryClient = new QueryClient();

root.render(
    <QueryClientProvider client={queryClient}>
        <App />
    </QueryClientProvider>
);
