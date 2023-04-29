package com.codenfast.developersuniverse.common.entity.schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
// https://schema.org/LocationFeatureSpecification


/**Specifies a location feature by providing a structured value representing a feature of an accommodation as a property-value pair of varying degrees of formality.*/
public class LocationFeatureSpecification {
                        private String id;
            private Boolean passive = Boolean.FALSE;
            private LocalDateTime createTime = null;
            private LocalDateTime updateTime = null;


    /**
     * The hours during which this service or contact is available.
     */
            private OpeningHoursSpecification hoursAvailable;

    /**
     * The date when the item becomes valid.
     */
        private LocalDate validFrom;

    /**
     * The date after when the item is not valid. For example the end of an offer, salary period, or a period of opening hours.
     */
        private LocalDate validThrough;

    /**
     * The upper value of some characteristic or property.
     */
        private BigDecimal maxValue;

    /**
     * A technique or technology used in a Dataset (or DataDownload, DataCatalog), corresponding to the method used for measuring the corresponding variable(s) (described using variableMeasured). This is oriented towards scientific and scholarly dataset publication but may have broader applicability; it is not intended as a full representation of measurement, but rather as a high level summary for dataset discovery.
     * <p>
     * For example, if variableMeasured is: molecule concentration, measurementTechnique could be: "mass spectrometry" or "nmr spectroscopy" or "colorimetry" or "immunofluorescence".
     * <p>
     * If the variableMeasured is "depression rating", the measurementTechnique could be "Zung Scale" or "HAM-D" or "Beck Depression Inventory".
     * <p>
     * If there are several variableMeasured properties recorded for some given data object, use a PropertyValue for each variableMeasured and attach the corresponding measurementTechnique.
     */
        private String measurementTechnique;

    /**
     * The lower value of some characteristic or property.
     */
        private BigDecimal minValue;

    /**
     * A commonly used identifier for the characteristic represented by the property, e.g. a manufacturer or a standard code for a property. propertyID can be (1) a prefixed string, mainly meant to be used with standards for product properties; (2) a site-specific, non-prefixed string (e.g. the primary key of the property or the vendor-specific ID of the property), or (3) a URL indicating the type of the property, either pointing to an external vocabulary, or a Web resource that describes the property (e.g. a glossary entry). Standards bodies should promote a standard prefix for the identifiers of properties from their standards.
     */
        private String propertyID;

    /**
     * The unit of measurement given using the UN/CEFACT Common Code (3 characters) or a URL. Other codes than the UN/CEFACT Common Code may be used with a prefix followed by a colon.
     */
        private String unitCode;

    /**
     * A string or text indicating the unit of measurement. Useful if you cannot provide a standard unit code for unitCode.
     */
        private String unitText;

    /**
     * The value of the quantitative value or property value node.
     * <p>
     * For QuantitativeValue and MonetaryAmount, the recommended type for values is 'Number'.
     * For PropertyValue, it can be 'Text', 'Number', 'Boolean', or 'StructuredValue'.
     * Use values from 0123456789 (Unicode 'DIGIT ZERO' (U+0030) to 'DIGIT NINE' (U+0039)) rather than superficially similar Unicode symbols.
     * Use '.' (Unicode 'FULL STOP' (U+002E)) rather than ',' to indicate a decimal point. Avoid using these symbols as a readability separator.
     */
            private Boolean value;

    /**
     * A secondary value that provides additional information on the original value, e.g. a reference temperature or a type of measurement.
     */
            private DefinedTerm valueReference;

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