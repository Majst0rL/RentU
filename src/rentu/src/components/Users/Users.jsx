import React, { useEffect, useState } from "react";
import { dataService } from "../../modules/DataService.js";

export default function Users({ loggedInUser, setLoggedInUser }) {
    const [users, setUsers] = useState([]);
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [birthday, setBirthday] = useState("");
    const [licence, setLicence] = useState(false);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [gender, setGender] = useState("male");
    const [userRole, setUserRole] = useState(0);
    const [id, setId] = useState(0);
    const [loginUsername, setLoginUsername] = useState("");
    const [loginPassword, setLoginPassword] = useState("");

    useEffect(() => {
        getAll();
    }, []);

    const getAll = () => {
        dataService.getUsers()
            .then((response) => setUsers(response.data))
            .catch((error) => console.error("Error fetching data:", error));
    }

    const loginUser = (event) => {
        event.preventDefault();
        dataService.loginUser(loginUsername, loginPassword).then((response) => {
            setLoggedInUser(response.data);
        }).catch((error) => {
            alert("Wrong username or password");
            console.error(error);
        });
        getAll();
    }

    const signOutUser = () => {
        setLoggedInUser(undefined);
    }

    const registerUser = (event) => {
        event.preventDefault();

        const newUser = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            birthday: birthday,
            licence: licence,
            username: username,
            password: password,
            gender: gender,
            userRole: "USER"
        };

        dataService.registerUser(newUser).then((result) => {
            setFirstName("");
            setLastName("");
            setEmail("");
            setBirthday("");
            setLicence(false);
            setUsername("");
            setPassword("");
            setGender("male");
            setUserRole(0);
            setId(0);
            getAll();
        });
    }

    const updateUser = (event) => {
        event.preventDefault();

        if (!loggedInUser) {
            alert("For updating user info you must be logged in");
            return;
        }

        let role;
        let userId;
        if (loggedInUser.userRole === "ADMIN") {
            role = userRole;
            userId = id;
        }
        else {
            role = loggedInUser.userRole;
            userId = loggedInUser.id;
        }

        const updatedUser = {
            id: userId,
            firstName: firstName,
            lastName: lastName,
            email: email,
            birthday: birthday,
            licence: licence,
            username: username,
            password: password,
            gender: gender,
            userRole: role
        };

        dataService.updateUser(userId, updatedUser).then((result) => {
            if (result.data.id === loggedInUser.id)
                setLoggedInUser(result.data);

            getAll();
        });
    }

    const deleteUser = (id) => {
        dataService.deleteUser(id).then((result) => {
            getAll();
        });
    }

    return (
        <div>
            <h1>Users</h1>
            <form>
                <h2>Registration</h2>
                First Name: <input type="text" name="firstName" value={firstName} onChange={(e) => { setFirstName(e.target.value) }} />
                Last Name: <input type="text" name="lastName" value={lastName} onChange={(e) => { setLastName(e.target.value) }} />
                Email: <input type="email" name="email" value={email} onChange={(e) => { setEmail(e.target.value) }} />
                Birthday: <input type="date" name="birthday" value={birthday} onChange={(e) => { setBirthday(e.target.value) }} />
                Licence: <input type="checkbox" name="licence" checked={licence} onChange={(e) => { setLicence(e.target.checked) }} />
                Username: <input type="text" name="username" value={username} onChange={(e) => { setUsername(e.target.value) }} />
                Password: <input type="password" name="password" value={password} onChange={(e) => { setPassword(e.target.value) }} />
                Gender:
                <select name="gender" value={gender} onChange={(e) => { setGender(e.target.value) }}>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
                <input type="submit" value="Add" onClick={registerUser} />

                {loggedInUser &&
                    <span>
                        {loggedInUser.userRole === "ADMIN" &&
                            <span>
                                Id: <input type="number" name="id" value={id} onChange={(e) => { setId(e.target.value) }} />
                                User Role:
                                <select name="userRole" value={userRole} onChange={(e) => { setUserRole(e.target.value) }}>
                                    <option value="USER">USER</option>
                                    <option value="ADMIN">ADMIN</option>
                                    <option value="MODERATOR">MODERATOR</option>
                                </select>
                            </span>
                        }
                        <input type="submit" value="Update" onClick={updateUser} />
                    </span>
                }
            </form>
            <form>
                <h2>Login</h2>
                Username: <input type="text" name="loginUsername" value={loginUsername} onChange={(e) => { setLoginUsername(e.target.value) }} />
                Password: <input type="password" name="loginPassword" value={loginPassword} onChange={(e) => { setLoginPassword(e.target.value) }} />
                <input type="submit" value="Login" onClick={loginUser} />
                <input type="submit" value="Sign out" onClick={signOutUser} />
            </form>

            {loggedInUser &&
                <div>
                    <h3>Logged in as:</h3>
                    <div>ID: {loggedInUser.id}</div>
                    <div>First Name: {loggedInUser.firstName}</div>
                    <div>Last Name: {loggedInUser.lastName}</div>
                    <div>Email: {loggedInUser.email}</div>
                    <div>Birthday: {loggedInUser.birthday}</div>
                    <div>Licence: {loggedInUser.licence ? 'Yes' : 'No'}</div>
                    <div>Username: {loggedInUser.username}</div>
                    <div>Password: {loggedInUser.password}</div>
                    <div>Gender: {loggedInUser.gender}</div>
                    <div>User Role: {loggedInUser.userRole}</div>
                </div>
            }

            <hr />
            {loggedInUser && loggedInUser.userRole === "ADMIN" &&
                <table>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Birthday</th>
                            <th>Licence</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Gender</th>
                            <th>User Role</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map((user) => (
                            <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>{user.firstName}</td>
                                <td>{user.lastName}</td>
                                <td>{user.email}</td>
                                <td>{user.birthday}</td>
                                <td>{user.licence ? 'Yes' : 'No'}</td>
                                <td>{user.username}</td>
                                <td>{user.password}</td>
                                <td>{user.gender}</td>
                                <td>{user.userRole}</td>
                                <td><button onClick={() => { deleteUser(user.id) }}>Delete</button></td>
                            </tr>
                        ))}
                    </tbody>
                </table>}
        </div>
    );
};
