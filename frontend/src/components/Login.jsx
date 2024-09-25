import Logo from "../assets/logo.svg";
import "../styles/Login.css";
import { useState } from "react";
import { useMutation } from "@tanstack/react-query";

async function handleRegister(formData) {
    try {
        const response = await fetch("http://127.0.0.1:8080/v1/user", {
            headers: {
                "Content-Type": "application/json",
            },
            method: "POST",
            body: JSON.stringify(formData),
        });
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

    function handleChange(event) {
        setFormData((prevState) => ({
            ...prevState,
            [event.target.name]: event.target.value,
        }));
    }

    function handleSubmit(event) {
        setFormData({
            username: "",
            password: "",
        });
    }

    return (
        <div className="whole-page-container">
            <div className="login-image-container"></div>
            <div className="login-container">
                <img className="logo" src={Logo} alt="logo" />
                <div className="center-container">
                    <div className="title-container">
                        <h1 className="login-title">Access your</h1>
                        <h1 className="login-title">free account.</h1>
                    </div>
                    <div className="username-container">
                        <input
                            value={form.username}
                            name="username"
                            className="username"
                            type="text"
                            placeholder="Username"
                            onChange={handleChange}
                        />
                    </div>
                    <div className="password-container">
                        <input
                            value={form.password}
                            name="password"
                            className="password"
                            type="password"
                            placeholder="Password"
                            onChange={handleChange}
                        />
                    </div>
                    <button onClick="#" type="button">
                        LOGIN
                    </button>
                    <div className="noaccount-container">
                        <a href="#" className="noaccount-title">
                            DON'T HAVE AN ACCOUNT YET?
                        </a>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;
