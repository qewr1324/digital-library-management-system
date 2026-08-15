package ir.nas.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Embeddable
public class Address
{
    @Column(name = "city", length = 20)
    private String city;

    @Column(name = "street", length = 20)
    private String street;

    @Column(name = "postal_code", length = 10, nullable = false, unique = true, check = {})
    private String postalCode;
}
