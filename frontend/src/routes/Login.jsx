import { useQuery } from "@tanstack/react-query";
import Logo from "./../assets/logo.svg";
import "./../styles/login.css";
import { useRef, useState } from "react";
import { Link } from "react-router-dom";

async function handleLogin({ queryKey }) {
    const [_key, { formData }] = queryKey;
    try {
        const response = await fetch(
            "http://localhost:8080/v1/images/".concat(
                formData.current.username
            ),
            {
                headers: {
                    Authorization: `Basic ${btoa(`${formData.current.username}:${formData.current.password}`)}`,
                },
                credentials: "include",
            }
        );

        if (!response.ok) {
            console.error(response);
            return undefined;
        }

        return await response.json();
    } catch (exception) {
        console.error(exception);
    }
}

function Login({ printImages }) {
    const [form, setFormData] = useState({
        username: "",
        password: "",
    });

    const [enabled, setEnabled] = useState(false);

    const formData = useRef({
        username: "",
        password: "",
    });

    const { fetchStatus, isError, data, refetch, status, error } = useQuery({
        queryKey: ["images", { formData }],
        queryFn: handleLogin,
        enabled: enabled,
    });

    console.log(data);
    console.log(printImages());

    function handleChange(event) {
        setFormData((prevState) => ({
            ...prevState,
            [event.target.name]: event.target.value,
        }));
    }

    function handleSubmit(event) {
        event.preventDefault();
        if (form.username == "" || form.password == "") {
            console.error("Missing data.");
            return;
        }

        formData.current = { username: form.username, password: form.password };
        setEnabled(true);
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
                            className={
                                isError
                                    ? "error-login-username"
                                    : "login-username"
                            }
                            type="text"
                            placeholder={
                                isError ? "Invalid Username!" : "Username"
                            }
                            onChange={handleChange}
                        />
                    </div>
                    <div className="login-password-container">
                        <input
                            value={form.password}
                            name="password"
                            className={
                                isError
                                    ? "error-login-password"
                                    : "login-password"
                            }
                            type="password"
                            placeholder={
                                isError ? "Invalid Password!" : "Password"
                            }
                            onChange={handleChange}
                        />
                    </div>
                    <button
                        className={
                            fetchStatus !== "fetching"
                                ? "login-button"
                                : "loading-button"
                        }
                        onClick={handleSubmit}
                        type="button"
                    >
                        {fetchStatus !== "fetching" ? (
                            "LOGIN"
                        ) : (
                            <i className="fa fa-circle-o-notch fa-spin"></i>
                        )}
                    </button>
                    <div className="login-noaccount-container">
                        <Link
                            to={"./register"}
                            className="login-noaccount-title"
                        >
                            DON'T HAVE AN ACCOUNT YET?
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;
