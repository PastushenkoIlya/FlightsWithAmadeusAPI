import React, { useState } from 'react'
import Locate from './components/Locate';
export default function App() {
    const [origin, setOrigin] = useState();
    const [destination, setDestination] = useState();
    const [flight, setFlight] = useState();
    return (
        <div>
            <Locate handleChoice={setDestination} display={"Origin"}/>
            <Locate handleChoice={setOrigin} display={"Destination"}/>
            { origin &&
                destination &&
                <Flight origin={origin} destination={destination} setFlight={setFlight}/>
            }
        </div>

    )
}