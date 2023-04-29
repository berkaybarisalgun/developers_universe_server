package com.codenfast.developersuniverse.common.entity.schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
// https://schema.org/PayAction


/**An agent pays a price to a participant.*/
public class PayAction {
                        private String id;
            private Boolean passive = Boolean.FALSE;
            private LocalDateTime createTime = null;
            private LocalDateTime updateTime = null;


    /**
     * A sub property of participant. The participant who is at the receiving end of the action.
     */
            private Audience recipient;

    /**
     * The offer price of a product, or of a price component when attached to PriceSpecification and its subtypes.
     * <p>
     * Usage guidelines:
     * <p>
     * Use the priceCurrency property (with standard formats: ISO 4217 currency format, e.g. "USD"; Ticker symbol for cryptocurrencies, e.g. "BTC"; well known names for Local Exchange Trading Systems (LETS) and other currency types, e.g. "Ithaca HOUR") instead of including ambiguous symbols such as '$' in the value.
     * Use '.' (Unicode 'FULL STOP' (U+002E)) rather than ',' to indicate a decimal point. Avoid using these symbols as a readability separator.
     * Note that both RDFa and Microdata syntax allow the use of a "content=" attribute for publishing simple machine-readable values alongside more human-friendly formatting.
     * Use values from 0123456789 (Unicode 'DIGIT ZERO' (U+0030) to 'DIGIT NINE' (U+0039)) rather than superficially similar Unicode symbols.
     */
        private BigDecimal price;

    /**
     * The currency of the price, or a price component when attached to PriceSpecification and its subtypes.
     * <p>
     * Use standard formats: ISO 4217 currency format, e.g. "USD"; Ticker symbol for cryptocurrencies, e.g. "BTC"; well known names for Local Exchange Trading Systems (LETS) and other currency types, e.g. "Ithaca HOUR".
     */
        private String priceCurrency;

    /**
     * One or more detailed price specifications, indicating the unit price and delivery or payment charges.
     */
            private PriceSpecification priceSpecification;

    /**
     * Indicates the current disposition of the Action.
     */
            private ActionStatusType actionStatus;

    /**
     * The direct performer or driver of the action (animate or inanimate). E.g. John wrote a book.
     */
            private Organization agent;

    /**
     * The endTime of something. For a reserved event or service (e.g. FoodEstablishmentReservation), the time that it is expected to end. For actions that span a period of time, when the action was performed. E.g. John wrote a book from January to December. For media, including audio and video, it's the time offset of the end of a clip within a larger file.
     * <p>
     * Note that Event uses startDate/endDate instead of startTime/endTime, even when describing dates with times. This situation may be clarified in future revisions.
     */
        private LocalDateTime endTime;

    /**
     * For failed actions, more information on the cause of the failure.
     */
            private Thing error;

    /**
     * The object that helped the agent perform the action. E.g. John wrote a book with a pen.
     */
            private Thing instrument;

    /**
     * The location of, for example, where an event is happening, where an organization is located, or where an action takes place.
     */
            private Place location;

    /**
     * The object upon which the action is carried out, whose state is kept intact or changed. Also known as the semantic roles patient, affected or undergoer (which change their state) or theme (which doesn't). E.g. John read a book.
     */
            private Thing object;

    /**
     * Other co-agents that participated in the action indirectly. E.g. John wrote a book with Steve.
     */
            private Organization participant;

    /**
     * The service provider, service operator, or service performer; the goods producer. Another party (a seller) may offer those services or goods on behalf of the provider. A provider may also serve as the seller. Supersedes carrier.
     */
    private java.util.List<Organization> carrier;

    /**
     * The result produced in the action. E.g. John wrote a book.
     */
            private Thing result;

    /**
     * The startTime of something. For a reserved event or service (e.g. FoodEstablishmentReservation), the time that it is expected to start. For actions that span a period of time, when the action was performed. E.g. John wrote a book from January to December. For media, including audio and video, it's the time offset of the start of a clip within a larger file.
     * <p>
     * Note that Event uses startDate/endDate instead of startTime/endTime, even when describing dates with times. This situation may be clarified in future revisions.
     */
        private LocalDateTime startTime;

    /**
     * Indicates a target EntryPoint, or url, for an Action.
     */
            private EntryPoint target;

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