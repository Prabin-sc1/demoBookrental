package com.bookrental.bookrental.pojo.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRequestPojo {
    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
}
