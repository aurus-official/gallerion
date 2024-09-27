import Logo from "./../assets/logo.svg";
import "./../styles/register.css";
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

function Register() {
    const [form, setFormData] = useState({
        username: "",
        password: "",
        confirmPassword: "",
    });

    const postNewUser = useMutation({ mutationFn: handleRegister });

    function handleChange(event) {
        setFormData((prevState) => ({
            ...prevState,
            [event.target.name]: event.target.value,
        }));
    }

    function handleSubmit(event) {
        event.preventDefault();
        postNewUser.mutate(form);
        setFormData({
            username: "",
            password: "",
            confirmPassword: "",
        });
    }

    return (
        <div className="register-whole-page-container">
            <div className="register-container">
                <img className="register-logo" src={Logo} alt="logo" />
                <div className="register-center-container">
                    <div className="register-title-container">
                        <h1 className="register-title">Create a</h1>
                        <h1 className="register-title">free account.</h1>
                    </div>
                    <div className="register-username-container">
                        <input
                            value={form.username}
                            name="username"
                            className="register-username"
                            type="text"
                            placeholder="Username"
                            onChange={handleChange}
                        />
                    </div>
                    <div className="register-password-container">
                        <input
                            value={form.password}
                            name="password"
                            className="register-password"
                            type="password"
                            placeholder="Password"
                            onChange={handleChange}
                        />
                    </div>
                    <div className="register-confirmpassw-container">
                        <input
                            value={form.confirmPassword}
                            name="confirmPassword"
                            className="register-confirmpass"
                            type="password"
                            placeholder="Confirm Password"
                            onChange={handleChange}
                        />
                    </div>
                    <button
                        className="register-button"
                        onClick={handleSubmit}
                        type="button"
                    >
                        REGISTER
                    </button>
                    <div className="register-account-container">
                        <a href="#" className="register-account-title">
                            ALREADY HAVE AN ACCOUNT?
                        </a>
                    </div>
                </div>
            </div>
            <div className="register-image-container"></div>
        </div>
    );
}

export default Register;
