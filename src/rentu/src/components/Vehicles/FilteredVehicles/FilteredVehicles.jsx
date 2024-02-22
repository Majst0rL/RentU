import React, { useState } from "react";
import { dataService } from "../../../modules/DataService";

export default function FilteredVehicles() {
    const [vehicles, setVehicles] = useState([]);
    const [power, setPower] = useState(0);
    const [price, setPrice] = useState(1000);
    const [year, setYear] = useState(2025);

    const getFilteredVehicles = () => {
        dataService.getFilteredVehicles(power, price, year)
            .then((response) => setVehicles(response.data))
            .catch((error) => console.error("Error fetching data:", error));
    }

    return (
        <div>
            <h2>Filtered Vehicles</h2>
            <div>
                <label>Power more than:</label>
                <input type="number" value={power} onChange={(e) => setPower(e.target.value)} />
            </div>
            <div>
                <label>and price per day less than:</label>
                <input type="number" value={price} onChange={(e) => setPrice(e.target.value)} />
            </div>
            <div>
                <label>and build year less than:</label>
                <input type="number" value={year} onChange={(e) => setYear(e.target.value)} />
            </div>
            <button onClick={getFilteredVehicles}>Search</button>

            {vehicles.length > 0 && (
                <table>
                    <thead>
                        <tr>
                            <th>Manufacturer</th>
                            <th>Model</th>
                            <th>Year</th>
                            <th>Price per Day</th>
                            <th>Fuel Type</th>
                            <th>Vehicle Type</th>
                            <th>Power</th>
                        </tr>
                    </thead>
                    <tbody>
                        {vehicles.map((vehicle) => (
                            <tr key={vehicle.id}>
                                <td>{vehicle.manufacturer}</td>
                                <td>{vehicle.model}</td>
                                <td>{vehicle.year}</td>
                                <td>{vehicle.pricePerDay}</td>
                                <td>{vehicle.fuelType}</td>
                                <td>{vehicle.vehicleType}</td>
                                <td>{vehicle.power}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};
