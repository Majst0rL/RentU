import React, { useEffect, useState } from "react";
import { dataService } from "../../../modules/DataService";
import { Bar } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

export default function VehicleGraph() {
    const [vehicles, setVehicles] = useState([]);
    const [chartData, setChartData] = useState(null);

    useEffect(() => {
        const getAll = () => {
            dataService.getVehicles()
                .then((response) => {
                    setVehicles(response.data);
                    updateChartData(response.data);
                })
                .catch((error) => console.error("Error fetching data:", error));
        }
        getAll();
    }, []); 

    const updateChartData = (data) => {
        const years = Array.from(new Set(data.map((vehicle) => vehicle.year)));
        const vehicleCountByYear = years.map((year) => {
            const vehiclesInYear = data.filter((vehicle) => vehicle.year === year);
            return vehiclesInYear.length;
        });

        const newChartData = {
            labels: years,
            datasets: [
                {
                    label: "Number of Vehicles",
                    data: vehicleCountByYear,
                    backgroundColor: 'rgba(255, 99, 132, 0.9)', // pink
                },
            ],
        };

        setChartData(newChartData);
    };

    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Vehicle Chart',
            },
        },
        scales: {
            x: {
                type: 'category',
                stacked: true,
            },
            y: {
                stacked: true,
            },
        },
    };

    return (
        <div className="">
            <h2>Vehicle Graph</h2>
            {vehicles.length > 0 && (
                <Bar
                    options={options}
                    data={chartData}
                />
            )}
        </div>
    );
}
