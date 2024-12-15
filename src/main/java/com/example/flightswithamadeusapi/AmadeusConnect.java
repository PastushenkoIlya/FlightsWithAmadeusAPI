package com.example.flightswithamadeusapi;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.Location;
import com.amadeus.exceptions.ResponseException;

enum AmadeusConnect {
    INSTANCE;
    private final Amadeus amadeus;
    private void apiCallsLimiter(){
        try {
            Thread.currentThread().sleep(100);  //sleep 100 ms
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private AmadeusConnect() {
        this.amadeus = Amadeus
                .builder(System.getenv("AMADEUS_API_KEY"),
                        System.getenv("AMADEUS_API_SECRET"))
                .build();
    }
    public Location[] location(String keyword) throws ResponseException {
        apiCallsLimiter();
        return amadeus.referenceData.locations.get(Params
                .with("keyword", keyword)
                .and("subType", Locations.AIRPORT));
    }
    public FlightOfferSearch[] flights(String origin, String destination, String departDate, String adults, String returnDate) throws ResponseException {
        apiCallsLimiter();
        return amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", origin)
                        .and("destinationLocationCode", destination)
                        .and("departureDate", departDate)
                        .and("returnDate", returnDate)
                        .and("adults", adults)
                        .and("max", 3));
    }
    public FlightPrice confirm(FlightOfferSearch offer) throws ResponseException {
        apiCallsLimiter();
        return amadeus.shopping.flightOffersSearch.pricing.post(offer);
    }
}