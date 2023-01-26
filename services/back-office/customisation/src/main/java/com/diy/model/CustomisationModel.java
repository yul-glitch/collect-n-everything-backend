package com.diy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomisationModel {
    Long customisationId;
    String colorBackground;
    String colorButton;
    String font;
    String catchPhrase;
    Long storeId;
}
