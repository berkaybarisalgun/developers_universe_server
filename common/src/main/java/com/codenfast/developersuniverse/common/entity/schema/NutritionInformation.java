package com.codenfast.developersuniverse.common.entity.schema;

import java.time.LocalDateTime;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(of = "id")
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
// https://schema.org/NutritionInformation


/**Nutritional information about the recipe.*/
public class NutritionInformation {
private String id;
private Boolean passive = Boolean.FALSE;
private LocalDateTime createTime = null;
private LocalDateTime updateTime = null;



/**
*The number of calories.
*/
private Energy calories;

/**
*The number of grams of carbohydrates.
*/
private Mass carbohydrateContent;

/**
*The number of milligrams of cholesterol.
*/
private Mass cholesterolContent;

/**
*The number of grams of fat.
*/
private Mass fatContent;

/**
*The number of grams of fiber.
*/
private Mass fiberContent;

/**
*The number of grams of protein.
*/
private Mass proteinContent;

/**
*The number of grams of saturated fat.
*/
private Mass saturatedFatContent;

/**
*The serving size, in terms of the number of volume or mass.
*/
private String servingSize;

/**
*The number of milligrams of sodium.
*/
private Mass sodiumContent;

/**
*The number of grams of sugar.
*/
private Mass sugarContent;

/**
*The number of grams of trans fat.
*/
private Mass transFatContent;

/**
*The number of grams of unsaturated fat.
*/
private Mass unsaturatedFatContent;

/**
*An additional type for the item, typically used for adding more specific types from external vocabularies in microdata syntax. This is a relationship between something and a class that the thing is in. In RDFa syntax, it is better to use the native RDFa syntax - the 'typeof' attribute - for multiple types. Schema.org tools may have only weaker understanding of extra types, in particular those defined externally.
*/
private String additionalType;

/**
*An alias for the item.
*/
private String alternateName;

/**
*A description of the item.
*/
private String description;

/**
*A sub property of description. A short description of the item used to disambiguate from other, similar items. Information from other properties (in particular, name) may be necessary for the description to be useful for disambiguation.
*/
private String disambiguatingDescription;

/**
*The identifier property represents any kind of identifier for any kind of Thing, such as ISBNs, GTIN codes, UUIDs etc. Schema.org provides dedicated properties for representing many of these, either as textual strings or as URL (URI) links. See background notes for more details.
*/
private PropertyValue identifier;

/**
*An image of the item. This can be a URL or a fully described ImageObject.
*/
private ImageObject image;

/**
*Indicates a page (or other CreativeWork) for which this thing is the main entity being described. See background notes for details.
Inverse property: mainEntity
*/
private CreativeWork mainEntityOfPage;

/**
*The name of the item.
*/
private String name;

/**
*Indicates a potential Action, which describes an idealized action in which this thing would play an 'object' role.
*/
private Action potentialAction;

/**
*URL of a reference Web page that unambiguously indicates the item's identity. E.g. the URL of the item's Wikipedia page, Wikidata entry, or official website.
*/
private String sameAs;

/**
*A CreativeWork or Event about this Thing.
Inverse property: about
*/
private CreativeWork subjectOf;

/**
*URL of the item.
*/
private String url;

}