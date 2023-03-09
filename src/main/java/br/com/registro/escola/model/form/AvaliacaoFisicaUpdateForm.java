package br.com.registro.escola.model.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;

@Getter
@Setter
public class AvaliacaoFisicaUpdateForm {

    @DecimalMin(value = "5", message = "'${validatedValue}' precisa ser no mínimo {value}.")
    private double peso;

    @DecimalMin(value = "0.5", message = "'${validatedValue}' precisa ser no mínimo {value}.")
    private double altura;

}
