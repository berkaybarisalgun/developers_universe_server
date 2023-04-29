package com.codenfast.developersuniverse.common.entity.schema;

import java.time.LocalDateTime;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
// https://schema.org/Flight


/**An airline flight.*/
public class Flight {
                        private String id;
            private Boolean passive = Boolean.FALSE;
            private LocalDateTime createTime = null;
            private LocalDateTime updateTime = null;


    /**
     * The kind of aircraft (e.g., "Boeing 747").
     */
        private String aircraft;

    /**
     * The airport where the flight terminates.
     */
            private Airport arrivalAirport;

    /**
     * Identifier of the flight's arrival gate.
     */
        private String arrivalGate;

    /**
     * Identifier of the flight's arrival terminal.
     */
        private String arrivalTerminal;

    /**
     * The type of boarding policy used by the airline (e.g. zone-based or group-based).
     */
            private BoardingPolicyType boardingPolicy;

    /**
     * The airport where the flight originates.
     */
            private Airport departureAirport;

    /**
     * Identifier of the flight's departure gate.
     */
        private String departureGate;

    /**
     * Identifier of the flight's departure terminal.
     */
        private String departureTerminal;

    /**
     * The estimated time the flight will take.
     */
            private Duration estimatedFlightDuration;

    /**
     * The distance of the flight.
     */
            private Distance flightDistance;

    /**
     * The unique identifier for a flight including the airline IATA code. For example, if describing United flight 110, where the IATA code for United is 'UA', the flightNumber is 'UA110'.
     */
        private String flightNumber;

    /**
     * Description of the meals that will be provided or available for purchase.
     */
        private String mealService;

    /**
     * An entity which offers (sells / leases / lends / loans) the services / goods. A seller may also be a provider. Supersedes merchant, vendor.
     */
        private java.util.List<Organization> vendor;

    /**
     * The time when a passenger can check into the flight online.
     */
        private LocalDateTime webCheckinTime;

    /**
     * The expected arrival time.
     */
        private LocalDateTime arrivalTime;

    /**
     * The expected departure time.
     */
        private LocalDateTime departureTime;

    /**
     * Destination(s) ( Place ) that make up a trip. For a trip where destination order is important use ItemList to specify that order (see examples).
     */
            private ItemList itinerary;

    /**
     * An offer to provide this item—for example, an offer to sell a product, rent the DVD of a movie, perform a service, or give away tickets to an event. Use businessFunction to indicate the kind of transaction offered, i.e. sell, lease, etc. This property can also be used to describe a Demand. While this property is listed as expected on a number of common types, it can be used in others. In that case, using a second type, such as Product or a subtype of Product, can clarify the nature of the offer.
     * Inverse property: itemOffered
     */
            private Demand offers;

    /**
     * Identifies that this Trip is a subTrip of another Trip. For example Day 1, Day 2, etc. of a multi-day trip.
     * Inverse property: subTrip
     */
            private Trip partOfTrip;

    /**
     * The service provider, service operator, or service performer; the goods producer. Another party (a seller) may offer those services or goods on behalf of the provider. A provider may also serve as the seller. Supersedes carrier.
     */
        private java.util.List<Organization> carrier;

    /**
     * Identifies a Trip that is a subTrip of this Trip. For example Day 1, Day 2, etc. of a multi-day trip.
     * Inverse property: partOfTrip
     */
            private Trip subTrip;

    /**
     * An additional type for the item, typically used for adding more specific types from external vocabularies in microdata syntax. This is a relationship between something and a class that the thing is in. In RDFa syntax, it is better to use the native RDFa syntax - the 'typeof' attribute - for multiple types. Schema.org tools may have only weaker understanding of extra types, in particular those defined externally.
     */
        private String additionalType;

    /**
     * An alias for the item.
     */
        private String alternateName;

    /**
     * A description of the item.
     */
        private String description;

    /**
     * A sub property of description. A short description of the item used to disambiguate from other, similar items. Information from other properties (in particular, name) may be necessary for the description to be useful for disambiguation.
     */
        private String disambiguatingDescription;

    /**
     * The identifier property represents any kind of identifier for any kind of Thing, such as ISBNs, GTIN codes, UUIDs etc. Schema.org provides dedicated properties for representing many of these, either as textual strings or as URL (URI) links. See background notes for more details.
     */
            private PropertyValue identifier;

    /**
     * An image of the item. This can be a URL or a fully described ImageObject.
     */
            private ImageObject image;

    /**
     * Indicates a page (or other CreativeWork) for which this thing is the main entity being described. See background notes for details.
     * Inverse property: mainEntity
     */
            private CreativeWork mainEntityOfPage;

    /**
     * The name of the item.
     */
        private String name;

    /**
     * Indicates a potential Action, which describes an idealized action in which this thing would play an 'object' role.
     */
            private Action potentialAction;

    /**
     * URL of a reference Web page that unambiguously indicates the item's identity. E.g. the URL of the item's Wikipedia page, Wikidata entry, or official website.
     */
        private String sameAs;

    /**
     * A CreativeWork or Event about this Thing.
     * Inverse property: about
     */
            private CreativeWork subjectOf;

    /**
     * URL of the item.
     */
        private String url;

}