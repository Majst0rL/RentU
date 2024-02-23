import React, { useState } from "react";
import { dataService } from "../../modules/DataService.js";

const SubscribeForm = () => {
    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");

    const handleSubscribe = async () => {
        try {
            const response = await dataService.subscribeToNewsletter(email);
            setMessage(response.data.message);
        } catch (error) {
            console.error("Error subscribing to newsletter:", error);
        }
    };

    return (
        <div>
            <h2>Subscribe to Newsletter</h2>
            <label>Email:</label>
            <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <button onClick={handleSubscribe}>Subscribe</button>
            {message && <p>{message}</p>}
        </div>
    );
};

export default SubscribeForm;