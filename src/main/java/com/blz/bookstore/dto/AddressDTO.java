package com.blz.bookstore.dto;
/**
 * @author Shubham
 * @version 1.0.0
 * @created 23-05-2022
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @NotNull(message = "Address must be mentioned.")
    private String address;
    @NotNull(message = "State must be selected.")
    private String state;
    @NotNull(message = "city should be mentioned.")
    private String city;
//    private String type;


}
