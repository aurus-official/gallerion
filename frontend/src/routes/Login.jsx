import { useQuery } from "@tanstack/react-query";
import Logo from "./../assets/logo.svg";
import "./../styles/login.css";
import { useState } from "react";

async function handleLogin({ queryKey }) {
    const [_key, { form }] = queryKey;
    try {
        const response = await fetch(
            "http://localhost:8080/v1/images/".concat(form.username),
            {
                headers: {
                    Authorization: `Basic ${btoa(`${form.username}:${form.password}`)}`,
                },
                credentials: "include",
            }
        );
        console.log(response);
        const data = await response.json();
        console.log(data);
        return data;
    } catch (exception) {
        console.error(exception);
    }
}

function Login() {
    const [form, setFormData] = useState({
        username: "",
        password: "",
    });

    const { isPending, error, data, refetch } = useQuery({
        queryKey: ["images", { form }],
        queryFn: handleLogin,
        enabled: false,
    });

    function handleChange(event) {
        setFormData((prevState) => ({
            ...prevState,
            [event.target.name]: event.target.value,
        }));
    }

    function handleSubmit(event) {
        refetch();
        setFormData({
            username: "",
            password: "",
        });
    }

    return (
        <div className="login-whole-page-container">
            <div className="login-image-container"></div>
            <div className="login-container">
                <img className="login-logo" src={Logo} alt="logo" />
                <div className="login-center-container">
                    <div className="login-title-container">
                        <h1 className="login-title">Access your</h1>
                        <h1 className="login-title">free account.</h1>
                    </div>
                    <div className="login-username-container">
                        <input
                            value={form.username}
                            name="username"
                            className="login-username"
                            type="text"
                            placeholder="Username"
                            onChange={handleChange}
                        />
                    </div>
                    <div className="login-password-container">
                        <input
                            value={form.password}
                            name="password"
                            className="login-password"
                            type="password"
                            placeholder="Password"
                            onChange={handleChange}
                        />
                    </div>
                    <button
                        className="login-button"
                        onClick={handleSubmit}
                        type="button"
                    >
                        LOGIN
                    </button>
                    <div className="login-noaccount-container">
                        <a href="#" className="login-noaccount-title">
                            DON'T HAVE AN ACCOUNT YET?
                        </a>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;
