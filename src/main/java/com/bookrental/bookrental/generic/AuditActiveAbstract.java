package com.bookrental.bookrental.generic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties
public abstract class AuditActiveAbstract extends AuditablaBase {
    @Column(name = "is_active")
    @JsonProperty("isActive")
    private Boolean isActive = true;

    @JsonIgnore
    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

