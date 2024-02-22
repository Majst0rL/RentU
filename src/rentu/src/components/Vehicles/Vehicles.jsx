import React, { useEffect, useState } from "react";
import { dataService } from "../../modules/DataService.js";
import FilteredVehicles from "./FilteredVehicles/FilteredVehicles.jsx";
import VehicleGraph from "./VehicleGraph/VehicleGraph.jsx";

export default function Vehicles() {
    const [vehicles, setVehicles] = useState([]);
    const [manufacturer, setManufacturer] = useState("");
    const [model, setModel] = useState("");
    const [year, setYear] = useState(0);
    const [pricePerDay, setPricePerDay] = useState(0);
    const [fuelType, setFuelType] = useState(0);
    const [vehicleType, setVehicleType] = useState(0);
    const [power, setPower] = useState(0);
    const [id, setId] = useState(0);

    useEffect(() => {
        getAll();
    }, []);

    const getAll = () => {
        dataService.getVehicles()
            .then((response) => setVehicles(response.data))
            .catch((error) => console.error("Error fetching data:", error));
    }

    const createVehicle = (event) => {
        event.preventDefault();

        const newVehicle = {
            manufacturer: manufacturer,
            model: model,
            year: year,
            pricePerDay: pricePerDay,
            fuelType: fuelType,
            vehicleType: vehicleType,
            power: power
        };
        dataService.createVehicle(newVehicle).then((result) => {
            getAll();
        });
    }

    const updateVehicle = (event) => {
        event.preventDefault();

        const newVehicle = {
            id: id,
            manufacturer: manufacturer,
            model: model,
            year: year,
            pricePerDay: pricePerDay,
            fuelType: fuelType,
            vehicleType: vehicleType,
            power: power
        };

        dataService.updateVehicle(id, newVehicle).then((result) => {
            getAll();
        });
    }

    const deleteVehicle = (id) => {
        dataService.deleteVehicle(id).then((result) => {
            getAll();
        });
    }

    return (
        <div>
            <h1>Vehicles</h1>
            <form>
                Manufacturer: <input type="text" name="manufacturer" value={manufacturer} onChange={(e) => { setManufacturer(e.target.value) }} />
                Model: <input type="text" name="model" value={model} onChange={(e) => { setModel(e.target.value) }} />
                Year: <input type="number" name="year" value={year} onChange={(e) => { setYear(e.target.value) }} />
                Price Per Day: <input type="number" name="pricePerDay" value={pricePerDay} onChange={(e) => { setPricePerDay(e.target.value) }} />
                Fuel Type:
                <select name="fuelType" value={fuelType} onChange={(e) => { setFuelType(e.target.value) }}>
                    <option value="PETROL">PETROL</option>
                    <option value="DIESEL">DIESEL</option>
                    <option value="ELECTRIC">ELECTRIC</option>
                    <option value="HYBRID">HYBRID</option>
                </select>
                Vehicle Type:
                <select name="vehicleType" value={vehicleType} onChange={(e) => { setVehicleType(e.target.value) }}>
                    <option value="LIMOUSINE">LIMOUSINE</option>
                    <option value="HATCHBACK">HATCHBACK</option>
                    <option value="COMBI">COMBI</option>
                    <option value="SUV">SUV</option>
                    <option value="SEDAN">SEDAN</option>
                </select>
                Power: <input type="number" name="power" value={power} onChange={(e) => { setPower(e.target.value) }} />
                <input type="submit" value="Add" onClick={createVehicle} />

                Id: <input type="number" name="id" value={id} onChange={(e) => { setId(e.target.value) }} />
                <input type="submit" value="Update" onClick={updateVehicle} />
            </form>

            <hr />

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
                    {vehicles.map((vehicle) => (
                        <tr key={vehicle.id}>
                            <td>{vehicle.id}</td>
                            <td>{vehicle.manufacturer}</td>
                            <td>{vehicle.model}</td>
                            <td>{vehicle.year}</td>
                            <td>{vehicle.pricePerDay}</td>
                            <td>{vehicle.fuelType}</td>
                            <td>{vehicle.vehicleType}</td>
                            <td>{vehicle.power}</td>
                            <td><button onClick={() => { deleteVehicle(vehicle.id) }}>Delete</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <br /><br />
            <hr />
            <FilteredVehicles />
            <br /><br />
            <hr />
            <VehicleGraph />
        </div>
    );
};

