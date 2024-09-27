import "./../styles/Navbar.css";
import Logo from "../assets/logo.svg";
import Profile from "../assets/pfp.svg";

function Navbar() {
    return (
        <>
            <ul className="navbar-container">
                <li className="title-container">
                    <img className="navbar-logo" src={Logo} alt="logo" />
                </li>
                <li className="searchbar-container">
                    <input
                        placeholder="SEARCH"
                        className="navbar-searchbar"
                        type="text"
                    />
                </li>
                <li className="user-container">
                    <img
                        className="navbar-userimg"
                        src={Profile}
                        alt="profile"
                    />
                </li>
            </ul>
        </>
    );
}

export default Navbar;
