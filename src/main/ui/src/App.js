import React, { useState } from 'react'
import Locate from './components/Locate';
import Order from "./components/Order";
import Flight from "./components/Flight";
import Confirm from "./components/Confirm";
export default function App() {
    const [origin, setOrigin] = useState();
    const [destination, setDestination] = useState();
    const [flight, setFlight] = useState();
    const [confirmation, setConfirmation] = useState();
    const [order, setOrder] = useState();

    console.log("App component rendered");

    return (
        <div>
            <Locate handleChoice={setDestination} display={"Origin"}/>
            <Locate handleChoice={setOrigin} display={"Destination"}/>
            { origin &&
                destination &&
                <Flight origin={origin} destination={destination} setFlight={setFlight}/>
            }
            { flight &&
                <Confirm flight={flight} setConfirmation={setConfirmation} />
            }
            { confirmation &&
                <Order confirmation={confirmation} order={order} setOrder={setOrder} />
            }
        </div>

    )
}