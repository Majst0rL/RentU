import React, { useEffect, useState } from "react";
import { dataService } from "../../modules/DataService.js";

export default function Agencies() {
    const [agencies, setAgencies] = useState([]);
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [street, setStreet] = useState("");
    const [number, setNumber] = useState(0);
    const [city, setCity] = useState("");
    const [country, setCountry] = useState("");
    const [agencyId, setAgencyId] = useState(0);
    const [vehicleId, setVehicleId] = useState(0);

    useEffect(() => {
        dataService.getAgencies()
            .then((response) => setAgencies(response.data))
            .catch((error) => console.error("Error fetching data:", error));
    }, []);

    const createAgency = (event) => {
        event.preventDefault();

        const newAgency = {
            name: name,
            email: email,
            location: {
                street: street,
                number: number,
                city: city,
                country: country
            },
            vehicles: [],
            reservations: []
        };

        dataService.createAgency(newAgency);
        document.location.reload();
    }

    const updateAgency = (event) => {
        event.preventDefault();

        // eslint-disable-next-line eqeqeq
        const selectedAgency = agencies.find((agency) => (agency.id == agencyId))
        if (!selectedAgency) {
            alert("Agency with id " + agencyId + " does not exist");
            return;
        }

        const updatedAgency = {
            name: name,
            email: email,
            location: {
                street: street,
                number: number,
                city: city,
                country: country
            },
            vehicles: selectedAgency.vehicles.map((vehicle) => {
                return { id: vehicle.id }
            }),
            reservations: [...selectedAgency.reservations]
        };

        dataService.updateAgency(agencyId, updatedAgency);
        document.location.reload();
    }

    const deleteAgency = (agencyId) => {
        dataService.deleteAgency(agencyId);
        document.location.reload();
    }

    const addAgencyVehicle = (event) => {
        event.preventDefault();

        // eslint-disable-next-line eqeqeq
        const selectedAgency = agencies.find((agency) => (agency.id == agencyId))
        if (!selectedAgency) {
            alert("Agency with id " + agencyId + " does not exist");
            return;
        }

        const updatedVehicles = [...selectedAgency.vehicles.map((vehicle) => {
            return { id: vehicle.id }
        }), { id: vehicleId }];

        // eslint-disable-next-line eqeqeq
        const updatedAgency = { ...selectedAgency, vehicles: updatedVehicles };
        console.log(JSON.stringify(updatedAgency)); //!

        dataService.updateAgency(agencyId, updatedAgency).then((response) => {
            document.location.reload();
        }).catch((error) => {
            alert("Error. Possible cause: vehicle id is not correct");
            console.error(error);
        });
    }

    const removeAgencyVehicle = (agencyId, vehicleId) => {
        // eslint-disable-next-line eqeqeq
        const selectedAgency = agencies.find((agency) => (agency.id == agencyId))

        // eslint-disable-next-line eqeqeq
        const updatedAgency = { ...selectedAgency, vehicles: selectedAgency.vehicles.filter((vehicle) => (vehicle.id != vehicleId)) }

        dataService.updateAgency(agencyId, updatedAgency);
        document.location.reload();
    }

    return (
        <div>
            <h1>Agencies</h1>
            <h2>Add/update agency</h2>
            <form>
                Name: <input type="text" name="name" value={name} onChange={(e) => { setName(e.target.value) }} />
                Email: <input type="text" name="email" value={email} onChange={(e) => { setEmail(e.target.value) }} />
                Street: <input type="text" name="street" value={street} onChange={(e) => { setStreet(e.target.value) }} />
                Number: <input type="number" name="number" value={number} onChange={(e) => { setNumber(e.target.value) }} />
                City: <input type="text" name="city" value={city} onChange={(e) => { setCity(e.target.value) }} />
                Country: <input type="text" name="country" value={country} onChange={(e) => { setCountry(e.target.value) }} />
                <input type="submit" value="Add" onClick={createAgency} />
                Agency id: <input type="number" name="agencyId" value={agencyId} onChange={(e) => { setAgencyId(e.target.value) }} />
                <input type="submit" value="Update" onClick={updateAgency} />
            </form>
            <hr />
            <form>
                <h2>Add vehicle to agency</h2>
                Agency id: <input type="number" name="agencyId2" value={agencyId} onChange={(e) => { setAgencyId(e.target.value) }} />
                Vehicle id: <input type="number" name="vehicleId" value={vehicleId} onChange={(e) => { setVehicleId(e.target.value) }} />
                <input type="submit" value="Update" onClick={addAgencyVehicle} />
            </form>

            <hr />

            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Location</th>
                        <th>Vehicles</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {agencies.map((agency) => (
                        <tr key={agency.id}>
                            <td>{agency.id}</td>
                            <td>{agency.name}</td>
                            <td>{agency.email}</td>
                            <td>{`${agency.location.street}, ${agency.location.number}, ${agency.location.city}, ${agency.location.country}`}</td>
                            <td>
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Manufacturer</th>
                                            <th>Model</th>
                                            <th>Year</th>
                                            <th>Price Per Day</th>
                                            <th>Fuel Type</th>
                                            <th>Vehicle Type</th>
                                            <th>Power</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {agency.vehicles.map((vehicle, vehicleIndex) => (
                                            <tr key={vehicle.id}>
                                                <td>{vehicle.id}</td>
                                                <td>{vehicle.manufacturer}</td>
                                                <td>{vehicle.model}</td>
                                                <td>{vehicle.year}</td>
                                                <td>{vehicle.pricePerDay}</td>
                                                <td>{vehicle.fuelType}</td>
                                                <td>{vehicle.vehicleType}</td>
                                                <td>{vehicle.power}</td>
                                                <td><button onClick={() => { removeAgencyVehicle(agency.id, vehicle.id) }}>Remove</button></td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                            </td>
                            <td><button onClick={() => { deleteAgency(agency.id) }}>Delete</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};
