package com.bookrental.bookrental.pojo.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
public class CategoryRequestPojo {
    private Integer id;
    @NotBlank
    @Size(min = 3, max = 100, message = "min 3 and max 100 characters only")
    private String name;
    @NotBlank
    @Size(max = 1000, message = "max 1000 characters only")
    private String description;
}
